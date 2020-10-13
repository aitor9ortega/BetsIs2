package test.businessLogic;
/**
 * Auxiliary FacadeImplementation class for testing DataAccess
 */

import java.util.Date;

import configuration.ConfigXML;
import domain.Event;
import domain.Person;
import test.dataAccess.TestDataAccess;

public class TestFacadeImplementation {
	TestDataAccess dbManagerTest;
 	
    
	   public TestFacadeImplementation()  {
			
			System.out.println("Creating TestFacadeImplementation instance");
			ConfigXML c=ConfigXML.getInstance();
			dbManagerTest=new TestDataAccess(); 
			dbManagerTest.close();
		}
		
		 
		public boolean removeEvent(Event ev) {
			dbManagerTest.open();
			boolean b=dbManagerTest.removeEvent(ev);
			dbManagerTest.close();
			return b;

		}
		
		
		public Event addEvent(String desc, Date d) {
			dbManagerTest.open();
			Event o=dbManagerTest.addEvent(desc,d);
			dbManagerTest.close();
			return o;

		}

//______________________________AITOR__________________________________________________________
		public Person register(String username, String pass) {
			dbManagerTest.open();
			Person p=dbManagerTest.register(username,pass);
			dbManagerTest.close();
			return p;
		}

		public boolean removePerson(Person p) {
			dbManagerTest.open();
			boolean b=dbManagerTest.removePerson(p);
			dbManagerTest.close();
			return b;	
		}
//______________________________AITOR__________________________________________________________


}
