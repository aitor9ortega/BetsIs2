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



class BLFacadeImplementationLoginTest {

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
			Mockito.doThrow(new UsernameNoExist()).when(dao).login("Juan", "Jaun123");
		} catch (BadPassword | UsernameNoExist e) {
			fail("Algo ha ido mal en el metodo");
		}
		assertThrows(UsernameNoExist.class,()-> sut.login("Juan", "Jaun123"));
			

	}
	
	@Test
	@DisplayName("Test 2:Wrong Password ")
	public void test2() {
	
		try {
			Mockito.doThrow(new BadPassword()).when(dao).login("Juan", "Jaun123");
		} catch (BadPassword | UsernameNoExist e) {
			fail("Algo ha ido mal en el metodo");
		}
		assertThrows(BadPassword.class,()-> sut.login("Juan", "Jaun123"));
			

	}
	
	@Test
	@DisplayName("Test 3:Everithing is OK ")
	public void test3() {
		Person p = new Person("Juan", "Jaun123");
		Person expected = null;
		try {
			Mockito.doReturn(p).when(dao).login("Juan", "Jaun123");
			expected=sut.login("Juan", "Jaun123");
		} catch (BadPassword | UsernameNoExist e) {
			fail("Algo ha ido mal en el metodo");
		}
	
		assertEquals(expected.getUsername(),p.getUsername() );
		assertEquals(expected.getPassword(),p.getPassword() );

	}

}
