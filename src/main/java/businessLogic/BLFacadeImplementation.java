package businessLogic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.SortedMapItzuli;
import domain.User;
import domain.Admin;
import domain.ArrayListItzuli;
import domain.Bet;
import domain.BetOddContainer;
import domain.Event;
import domain.MovementBet;
import domain.Odd;
import domain.Person;
import exceptions.BadPassword;
import exceptions.EventExist;
import exceptions.EventFinished;
import exceptions.InvalidRate;
import exceptions.MinAmountNotReached;
import exceptions.NoFounds;
import exceptions.NoMoney;
import exceptions.OddExist;
import exceptions.QuestionAlreadyExist;
import exceptions.UserExist;
import exceptions.UsernameNoExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;
	
	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
			}
		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager=da;		
	}
	

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
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
	   dbManager.open(false);
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();
		
		return qry;
   };
   /**
	 * This method inserts a new event in the database
	 * @param e, new event
	 * @throws EventExist if that event already exists
	 */
   public Event createEvent(String description,Date eventDate) throws EventExist{
	   dbManager.open(false);
	   Event ev=null;
	   try {
		   ev=dbManager.createEvent(description,eventDate);
	   }catch(EventExist u) {
		   throw new EventExist(u.getMessage());
	   }
	   dbManager.close();
	   return ev;
   }
   /**
	 * This method inserts a new odd in the database
	 * @param o, new odd
	 * @param q, the question of the odd
	 * @throws OddExist if that odd already exists
	 */
   public Odd createOdd(Question q,float fee, String result) throws OddExist, InvalidRate{
	   dbManager.open(false);
	   Odd odd=null;
	   try {
		   odd=dbManager.createOdd(q,fee,result);
	   }catch(OddExist u) {
		   throw new OddExist(u.getMessage());
	   }catch(InvalidRate i) {
		   throw new InvalidRate(i.getMessage());
	   }
	   dbManager.close();
	   return odd;
   }
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
    	dbManager.open(false);
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	
	

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}

    /**
	 * This method returns the user registered with the username and password given
	 * @param username of user
	 * @param password of the account of user
	 * @return the User with that info
	 * @throws BadPassword if that username has other password (not the given)
	 * @throws UsernameNoExist if the username given is not registered
	 */
	public Person login(String username, String password) throws BadPassword, UsernameNoExist {
		// TODO Auto-generated method stub
		dbManager.open(false);
		System.out.println(username);
		Person p=null;
		try {
			p=dbManager.login(username, password);
		}catch(BadPassword e) {
			dbManager.close();
			throw new BadPassword(e.getMessage());
		}
		catch(UsernameNoExist e) {
			dbManager.close();
			throw new UsernameNoExist(e.getMessage());
		}
		
		dbManager.close();
		return p;
	}
	/**
	 * This method inserts a new user in the database
	 * @param u, the new user
	 * @throws UserExist if that user already exists
	 */
	public void register(String username, String password, String name) throws UserExist {
		// TODO Auto-generated method stub
		dbManager.open(false);
		try {
			dbManager.register(username,password,name);
		}catch(UserExist e) {
			dbManager.close();
			throw new UserExist(e.getMessage());
		}
		dbManager.close();
	}

	public User addBet(User u,Collection<Odd> o,float amount, float fee) throws MinAmountNotReached {
		boolean nahiko=true;
		for(Odd od:o) {
			Question q=this.getQuestionOfOdd(od);
			if(q.getBetMinimum()>amount) {
				nahiko=false;
				break;
			}
			
		}
		if(!nahiko) {
			throw new MinAmountNotReached();
		}
		dbManager.open(false);
		User us=dbManager.addBet(u,o,amount,fee);
		dbManager.close();
		return us;
	}
	public User changeMoney(User u,float money) {
		dbManager.open(false);
		User us= dbManager.changeMoney(u,money);
		dbManager.close();
		return us;
	}
	public Collection<Bet> getBets(Odd o) {
		dbManager.open(false);
		Collection<Bet> a= dbManager.getBets(o);
		dbManager.close();
		return a;
	}
	public Question updateResult(Question q, Odd o) {
		dbManager.open(false);
		Question qu=dbManager.updateResult(q,o);
		dbManager.close();
		return qu;
	}
	public void removeEvent(Event e) {
		dbManager.open(false);
		dbManager.removeEvent(e);
		dbManager.close();
	}
	public void removeQuestion(Question q) {
		dbManager.open(false);
		dbManager.removeQuestion(q);
		dbManager.close();
	}
	public void removeOdd(Odd o) {
		dbManager.open(false);
		dbManager.removeOdd(o);
		dbManager.close();
	}

	public User removeBet(User a, Bet b) {
		dbManager.open(false);
		User u=dbManager.removeBet(a,b,"Apustua kantzelatu");
		dbManager.close();
		return u;
	}
	public void replicate(User u, String username, Double amount) throws NoFounds,UsernameNoExist{
		dbManager.open(false);
		if(amount>u.getMoney()) {
			throw new NoFounds();
		}
		dbManager.replicate(u,username,amount);
		dbManager.close();
	}
	public SortedMapItzuli bestUser() {
		dbManager.open(false);
		SortedMap<Float,ArrayListItzuli> map = dbManager.bestUser();
		dbManager.close();
		SortedMapItzuli a=new SortedMapItzuli(map);
		return a;
	}
	public Collection<BetOddContainer> getBetOddContainers(Collection<Bet> bets){
		dbManager.open(false);
		Collection<BetOddContainer> containers= dbManager.getBetOddContainers(bets);
		dbManager.close();
		return containers;
	}
	public Question getQuestionOfOdd(Odd o) {
		dbManager.open(false);
		Question q=dbManager.getQuestionOfOdd(o);
		dbManager.close();
		return q;
	}
}

