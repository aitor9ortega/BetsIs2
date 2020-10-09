package businessLogic;

import java.util.Vector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;

//import domain.Booking;
import domain.Question;
import domain.SortedMapItzuli;
import domain.User;
import domain.Admin;
import domain.Event;
import domain.MovementBet;
import domain.Odd;
import domain.Person;
import domain.Bet;
import domain.BetOddContainer;
import exceptions.BadPassword;
import exceptions.EventExist;
import exceptions.EventFinished;
import exceptions.InvalidRate;
import exceptions.MinAmountNotReached;
import exceptions.NoFounds;
import exceptions.OddExist;
import exceptions.QuestionAlreadyExist;
import exceptions.UserExist;
import exceptions.UsernameNoExist;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	/**
	 * This method returns the user or admin registered with the username and password given
	 * @param username of person
	 * @param password of the account of person
	 * @return the User with that info
	 * @throws BadPassword if that username has other password (not the given)
	 * @throws UsernameNoExist if the username given is not registered
	 */
	@WebMethod public Person login(String username, String password) throws BadPassword, UsernameNoExist;
	/**
	 * This method inserts a new user in the database
	 * @param u, the new user
	 * @throws UserExist if that user already exists
	 */
	@WebMethod public void register(String username, String password, String name) throws UserExist;
	/**
	 * This method inserts a new event in the database
	 * @param e, new event
	 * @throws EventExist if that event already exists
	 */
	@WebMethod public Event createEvent(String description,Date eventDate) throws EventExist;
	/**
	 * This method inserts a new odd in the database
	 * @param o, new odd
	 * @param q, the question of the odd
	 * @throws OddExist if that odd already exists
	 */
	@WebMethod public Odd createOdd(Question q, float fee, String result) throws OddExist,InvalidRate;
	@WebMethod public User addBet(User u,Collection<Odd> odds,float amount, float fee) throws MinAmountNotReached;
	@WebMethod public User changeMoney(User u,float money);
	@WebMethod public Collection<Bet> getBets(Odd o);
	@WebMethod public Question updateResult(Question q, Odd o);

	@WebMethod public void removeEvent(Event e);
	@WebMethod public void removeQuestion(Question q);
	@WebMethod public void removeOdd(Odd o);

	@WebMethod public User removeBet(User a, Bet b);
	@WebMethod public void replicate(User u, String username, Double amount) throws NoFounds,UsernameNoExist;
	@WebMethod public SortedMapItzuli bestUser();
	@WebMethod public Collection<BetOddContainer> getBetOddContainers(Collection<Bet> bets);
	@WebMethod public Question getQuestionOfOdd(Odd o);
}
