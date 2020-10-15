package junitMock;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import domain.Person;
import domain.User;
import exceptions.BadPassword;
import exceptions.QuestionAlreadyExist;
import exceptions.UserExist;
import exceptions.UsernameNoExist;
import test.businessLogic.TestFacadeImplementation;

class DataAccessLoginTest {
	
	// sut- System Under Test
	private static DataAccess sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));;
	private static TestFacadeImplementation testBL = new TestFacadeImplementation();


//ahbdsfscvb

	@BeforeAll
	public static void initialize() {
	System.out.println("Inicializo y compruebo ...");
	}
	
	
	
	@Test
	@DisplayName("Test 1: El usuario no existe")
	void testLogin() {
		assertThrows(UsernameNoExist.class, () -> sut.login("Juan", "21Ju"));
	}
	
	@Test
	@DisplayName("Test 2: Contraseña incorrecta")
	void testLogin1() {
		Person p=testBL.register("Aitor", "Aitor98");
		try {
			assertThrows(BadPassword.class, () -> sut.login("Aitor", "98Aitor"));
		}catch(Exception e){
			fail("Something went wrong");
		}finally {
			boolean b=testBL.removePerson(p);
			assertTrue(b);	
		}
	}
	
	@Test
	@DisplayName("Test 3: El usuario existe y se logea")
	void testLogin2() {
		Person p=testBL.register("Aitor", "Aitor98");
		try {
			Person obtained =sut.login("Aitor", "Aitor98");
			assertEquals(p.getUsername(),obtained.getUsername());
			assertEquals(p.getPassword(),obtained.getPassword());
			
		}catch(Exception e){
			fail("Something went wrong");
		}finally {
			boolean b=testBL.removePerson(p);
			assertTrue(b);	
		}
	}
}
