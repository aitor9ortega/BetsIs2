package junitMock;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
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




	@BeforeAll
	public static void initialize() {
	System.out.println("Inicializo y compruebo ...");
	}
	
	
	
	@Test
	@DisplayName("Test 1: El usuario no existe")
	void testLogin() {
		assertThrows(UsernameNoExist.class, () -> sut.login("Aitor", "24"));
	}
	
//	@Test
//	@DisplayName("Test 2: Contraseña incorrecta")
//	void testLogin1() {
//		try {
//			sut.register("AitorR", "Aitor8", "Aitor");
//			assertThrows(BadPassword.class, () -> sut.login("AitorR", "24"));
//		} catch (UserExist e) {
//			fail("Problem on register");
//		}finally {
//			
//		}
//	
//	}

}
