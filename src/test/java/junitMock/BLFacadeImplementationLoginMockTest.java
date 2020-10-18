package junitMock;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Person;
import exceptions.BadPassword;
import exceptions.UsernameNoExist;



class BLFacadeImplementationLoginMockTest {

	private BLFacadeImplementation sut;
	@Mock
	private DataAccess dao;
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		sut = new BLFacadeImplementation(dao);
	}
	
//	@Test
//	void testLogin() {
//		fail("Not yet implemented");
//	}
	
	@Test
	@DisplayName("Test 0:User name Not Exist on DataBase ")
	public void test1() {
	
		try {
			Mockito.doThrow(new UsernameNoExist()).when(dao).login("Cristian", "Cris54");
		} catch (BadPassword | UsernameNoExist e) {
			fail("Algo ha ido mal en el metodo");
		}
		assertThrows(UsernameNoExist.class,()-> sut.login("Cristian", "Cris54"));
	}
	
	@Test
	@DisplayName("Test 2:Wrong Password ")
	public void test2() {
	
		try {
			Mockito.doThrow(new BadPassword()).when(dao).login("Aitor", "98Aitor");
		} catch (BadPassword | UsernameNoExist e) {
			fail("Algo ha ido mal en el metodo");
		}
		assertThrows(BadPassword.class,()-> sut.login("Aitor", "98Aitor"));
			

	}
	
	@Test
	@DisplayName("Test 3:Devuelve al usuario correctamente ")
	public void test3() {
		Person p = new Person("Aitor", "Aitor98");
		Person obtained = null;
		try {
			Mockito.doReturn(p).when(dao).login("Aitor", "Aitor98");
			obtained=sut.login("Aitor", "Aitor98");
		} catch (BadPassword | UsernameNoExist e) {
			fail("Algo ha ido mal en el metodo");
		}
	
		assertEquals(p.getUsername(),obtained.getUsername() );
		assertEquals(p.getPassword(),obtained.getPassword() );

	}

}
