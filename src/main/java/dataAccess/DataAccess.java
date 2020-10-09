package dataAccess;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Admin;
import domain.ArrayListItzuli;
import domain.Bet;
import domain.BetOddContainer;
import domain.Event;
import domain.Movement;
import domain.MovementBet;
import domain.Odd;
import domain.OddEventContainer;
import domain.Person;
import domain.Question;
import domain.ReplicateUser;
import domain.User;
import exceptions.BadPassword;
import exceptions.EventExist;
import exceptions.InvalidRate;
import exceptions.NoMoney;
import exceptions.OddExist;
import exceptions.QuestionAlreadyExist;
import exceptions.UserExist;
import exceptions.UsernameNoExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

	public DataAccess(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);

	}

	public DataAccess()  {	
		 new DataAccess(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();

		//User u = new User("hello","aitor","azkoiti");
		//db.persist(u);

		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month,28));
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
					
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			}
			q1.addOdd((float)1.5,"Athletic");
			q1.addOdd((float)1.2,"Atletico");
			q2.addOdd((float)1.5,"Athletic");
			q2.addOdd((float)1.2,"Atletico");
			
			Person ad= new Admin("admin","admin");
			db.persist(ad);
			

			
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);
	
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);			
			
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
			Event ev = db.find(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			//db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			event.addQuestion(q);
			return q;
		
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}
	/**
	 * This method retrieves a user registered with the info given
	 * @param username of the user
	 * @param password of the user
	 * @return the user
	 * @throws BadPassword if the user registered with that username has other password
	 * @throws UsernameNoExist if there is no users registered with that username
	 */
	public Person login(String username, String password) throws BadPassword, UsernameNoExist {
		
		Person dbUser = db.find(Person.class, username);
			if (dbUser == null)
				throw new UsernameNoExist("There are no users with this username!");
			
			if (!dbUser.getPassword().equals(password)) {
				throw new BadPassword("Wrong Password!");
			}
		
		return dbUser;
	}

	/**
	 * This method inserts a new user
	 * @param u, the new user
	 * @throws UserExist if the is a user already registered with that username
	 */
	public void register(String username, String password, String name) throws UserExist {
		db.getTransaction().begin();
		User u=new User(username,password,name);
		try {
			db.persist(u);
			db.getTransaction().commit();
		}catch(Exception e) {
			throw new UserExist("This user already exists!");
		}
		
	}
	/**
	 * This method adds a new odd for a question in the database
	 * @param q, the question
	 * @param o, the new odd
	 * @throws OddExist if that odd already exists
	 */
	public Odd createOdd(Question q,float fee, String result) throws OddExist,InvalidRate {
		Question qu = db.find(Question.class, q.getQuestionNumber());
		
		db.getTransaction().begin();
		Odd o=qu.addOdd(fee, result);
		if(o!=null) {
			db.persist(qu); 
			db.getTransaction().commit();
		}else {
			throw new OddExist("This odd already exists!");
		}	
		q.addOdd(fee, result);
		return o;
	}
	/**
	 * This method adds a new event
	 * @param e, new event
	 * @throws EventExist if that event already exists
	 */
	public Event createEvent(String description,Date eventDate) throws EventExist {
		db.getTransaction().begin();
		Event e=new Event(description,eventDate);
		try {
			db.persist(e);
			db.getTransaction().commit();
		}catch(Exception u) {
			throw new EventExist("This event already exists!");
		}
		return e;
	}
	
public void open(boolean initializeMode){
		
		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
	
	
	
	
	
	/**
	 * This method closes the database
	 */
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	public User addBet(User u,Collection<Odd> odds,float amount, float fee) {
		User us=db.find(User.class, u.getUsername());
		Collection<Odd> oddIds = new ArrayList<Odd>();
		for (Odd o : odds)
			oddIds.add(db.find(Odd.class, o.getOddId()));
		u.setMoney(u.getMoney()-amount);
		Bet b=u.addBet(oddIds,fee,amount,"Not finished");
		db.getTransaction().begin();
		for(ReplicateUser ru: us.getReplicators()) {
			if(ru.getAmount()>=amount  && ru.getReplicator().getMoney()>=amount) {
				Bet bet=ru.getReplicator().addBet(oddIds,fee,amount,"Not finished");
				MovementBet move=ru.getReplicator().addMovementBet(amount,new Date(),"Apustua Egin",bet);
				bet.addMovementBet(move);	
				ru.getReplicator().setMoney(ru.getReplicator().getMoney()-amount);
				ru.setAmount(ru.getAmount()-amount);
				for (Odd o: oddIds) {
					db.persist(bet);
					db.getTransaction().commit();
					db.getTransaction().begin();
					o.addBet(bet);
					o.addBetId(bet.getBetId());
					
				}
			}
		}
		
		Bet be=us.addBet(oddIds,fee,amount,"Not finished");
		
		MovementBet move=us.addMovementBet(amount,new Date(),"Apostua Egin",be);
		be.addMovementBet(move);
		us.setMoney(us.getMoney()-amount);
		for(Odd o : oddIds) o.addBet(be);
		db.persist(be);
		db.persist(move);
		db.getTransaction().commit();
		db.getTransaction().begin();
		for (Odd o: oddIds) o.addBetId(be.getBetId());
		db.getTransaction().commit();
		b.setBetId(be.getBetId());
		u.addMovementBet(amount,new Date(),"Apostua Egin",b);
		b.addMovementBet(move);
		for(ReplicateUser ru: u.getReplicators()) {
			if(ru.getReplicator().getMoney()>amount) {
				Bet bet=ru.getReplicator().addBet(oddIds,fee,amount,"Not finished");
				MovementBet moves=ru.getReplicator().addMovementBet(amount,new Date(), "Apostua Egin",bet);
				bet.addMovementBet(moves);				
				ru.getReplicator().setMoney(ru.getReplicator().getMoney()-amount);
				ru.setAmount(ru.getAmount()-amount);
			}
		}
		return u;
		
	}
	public User changeMoney(User u,float money) {
		User us=db.find(User.class, u.getUsername());
		String arrazoia;
		if(money>=0) {
			arrazoia= "Dirua Sartu";
		}else {
			arrazoia= "Dirua Atera";
		}
		
		u.setMoney(u.getMoney()+money);
		Movement move=u.addMovement(money,new Date(),arrazoia);
		db.getTransaction().begin();
		us.setMoney(us.getMoney()+money);
		Movement moves=us.addMovement(money,new Date(),arrazoia);
		db.persist(moves);
		db.getTransaction().commit();
		return us;
	}
	public Collection<Bet> getBets(Odd o) {
		Collection<Bet> betList = new ArrayList<Bet>();
		for(int id : o.getBetsId()) {
			TypedQuery<Bet> a = db.createQuery("SELECT b FROM Bet b WHERE b.betId = ?1",Bet.class);
			a.setParameter(1, id);
			betList.add(a.getSingleResult());
		}
		
		return betList;
	}
	public Question updateResult(Question q, Odd o) {
		Odd od=null;
		Collection<Bet> bets=null;
		Question que=db.find(Question.class, q.getQuestionNumber());
		if(o!=null) {
			od=db.find(Odd.class, o.getOddId());
			bets=this.getBets(o);
		}
		q.setResult(o);
		db.getTransaction().begin();
		que.setResult(od);
		for(Odd odd: q.getOdds()) {
			if(od!=null && odd.getOddId()==od.getOddId()) {
				for(Bet b: bets) {
					if(!b.getSituation().equals("Galdu")) {
						boolean bukatuta=true;
						for(Odd odda: b.getOdds()) {
							if(odda.getQuestion().getResult()==null) {
								bukatuta=false;
								break;
							}else if(!odda.getQuestion().getResult().getResultBet().equals(odda.getResultBet())){
								System.out.println("Errorea, ez du ondo publikatu.");
							}
						}
						if(bukatuta) {
							Bet be=db.find(Bet.class, b.getBetId());
							be.setSituation("Irabazi");
							MovementBet a=b.getUser().addMovementBet(b.getBetMoney()*b.getFee(),new Date(),"Apustua Irabazi",be);
							be.addMovementBet(a);
							be.getUser().setMoney(b.getUser().getMoney()+b.getBetMoney()*b.getFee()); 
						}
					}							
				}
			}else {
				bets=this.getBets(odd);
				for(Bet b: bets) {
					Bet be=db.find(Bet.class, b);
					be.setSituation("Galdu");
				}
			}
		}
		db.getTransaction().commit();
		return que;
	}
	public void removeEvent(Event e) {
		while (e.getQuestions().size()>0) {
			removeQuestion(e.getQuestions().get(0));
			e.getQuestions().remove(0);
		}
		Event ev=db.find(Event.class,e);
		db.getTransaction().begin();
		db.remove(ev);
		db.getTransaction().commit();
	}
	
	public void removeQuestion(Question q) {
		ArrayList<Odd> o = new ArrayList<Odd>(q.getOdds());
		while (o.size()>0) {
			removeOdd(o.get(0));
			o.remove(0);
		}
		Question qu=db.find(Question.class, q);
		db.getTransaction().begin();
		qu.getEvent().getQuestions().remove(qu);
		db.remove(qu);
		db.getTransaction().commit();
	}
	
	public void removeOdd( Odd o) {
		Odd odd=db.find(Odd.class, o);
		ArrayList<Bet> b= new ArrayList<Bet>(odd.getBets());
		while(b.size()>0) {
			User us=db.find(User.class, b.get(0).getUser());
			removeBet(us,b.get(0),"Gertaera kantzelatu");
			b.remove(0);
		}
		db.getTransaction().begin();
		odd.getQuestion().getOdds().remove(odd);
		db.remove(odd);
		db.getTransaction().commit();
	}


	public User removeBet(User a, Bet b, String s) {
		// TODO Auto-generated method stub
		if (b.getSituation().equals("Not finished")) {
			if (!s.equals("Gertaera kantzelatu")) {
				a.getBets().remove(b);
				a.setMoney(a.getMoney() + b.getBetMoney());
				a.addMovementBet(b.getBetMoney(), new Date(), s, null);
			}
			User us = db.find(User.class, a.getUsername());
			Bet be = db.find(Bet.class, b.getBetId());
			db.getTransaction().begin();
			for (MovementBet mb : be.getMovementBets()) {
				mb.setBet(null);
			}
			for (Odd o : be.getOdds()) {
				o.getBets().remove(be);
				o.getBetsId().remove(be.getBetId());
			}
			us.getBets().remove(be);
			us.addMovementBet(b.getBetMoney(), new Date(), s, null);

			us.setMoney(us.getMoney() + be.getBetMoney());
			db.remove(be);
			db.getTransaction().commit();
			return us;
		}
		db.getTransaction().begin();
		Bet be = db.find(Bet.class, b.getBetId());

		for (Odd o : be.getOdds()) {
			o.getBets().remove(be);
			o.getBetsId().remove(be.getBetId());
		}
		be.setOdds(null);
		db.getTransaction().commit();
		return a;

	}
	public void replicate(User u, String username, Double amount) throws UsernameNoExist{
		
		User replicated=db.find(User.class, username);
		if(replicated==null) {
			throw new UsernameNoExist();
		}
		db.getTransaction().begin();
		User replicator=db.find(User.class, u.getUsername());
		ReplicateUser ru=replicated.addReplicator(replicator,amount);
		if(ru!=null) {
			replicator.addReplicatingUser(ru);
			u.addReplicatingUser(ru);
		}
		db.getTransaction().commit();
	}
	public SortedMap<Float,ArrayListItzuli> bestUser() {
		SortedMap<Float,ArrayListItzuli> map = new TreeMap<Float,ArrayListItzuli>();
		float Irabaziak = 0;
		TypedQuery<User> usee = db.createQuery("SELECT u FROM User u",User.class);
		Collection<User> users = usee.getResultList();
		for(User use: users) {
			Irabaziak=0;
			Collection<Bet> bets = use.getBets();
				for(Bet b: bets) {
					if (b.getSituation().equals("Irabazi")) {
						Irabaziak += b.getBetMoney() * (b.getFee()-1);
					}else if(b.getSituation().equals("Galdu")) {
						Irabaziak -= b.getBetMoney();
					}
					
				}
				if (map.size() < 3) {
					if(!map.containsKey(Irabaziak)) {
						ArrayList<User> a = new ArrayList<User>();
						a.add(use);
						map.put(Irabaziak, new ArrayListItzuli(a));
					}else {
						map.get(Irabaziak).getList().add(use);
					}
				}else {
					if(map.firstKey() < Irabaziak) {
						map.remove(map.firstKey());
						ArrayList<User> c = new ArrayList<User>();
						c.add(use);
						map.put(Irabaziak, new ArrayListItzuli(c));
					}else if(map.firstKey() == Irabaziak) {
						map.get(map.firstKey()).getList().add(use);
					}
				}
			
		}
	
		return map;
	}
	public Collection<BetOddContainer> getBetOddContainers(Collection<Bet> bets){
		Collection<BetOddContainer> containers=new ArrayList<BetOddContainer>();
		for(Bet b: bets) {
			if(b.getSituation().equals("Not finished")) {
				
			
			Bet be=db.find(Bet.class, b.getBetId());
			
			Collection<OddEventContainer> containerOddEvent= new ArrayList<OddEventContainer>();
			for(Odd o:be.getOdds()) {
				containerOddEvent.add(new OddEventContainer(o,o.getQuestion().getEvent(),o.getQuestion()));
			}
			containers.add(new BetOddContainer(be,containerOddEvent));
			}
		}
		return containers;
	}
	public Question getQuestionOfOdd(Odd o) {
		Odd odd=db.find(Odd.class, o);
		return odd.getQuestion();
	}
}
