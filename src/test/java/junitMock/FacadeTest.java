package junitMock;

/**
 * FacadeTest: Some JUnit example for Facade
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import test.businessLogic.TestFacadeImplementation;

class FacadeTest {
	static BLFacadeImplementation sut;
	static TestFacadeImplementation testBL;

	private String queryText = "A question";
	private Float betMinimum = 2.0f;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Event ev;

	@BeforeAll
	public static void ini() {
		// you can parametrize the DataAccess used by BLFacadeImplementation
		DataAccess da = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
		sut = new BLFacadeImplementation(da);
		testBL = new TestFacadeImplementation();
	}

	@Test
	@DisplayName("Test1: question already exist")
	public void test1() throws ParseException, QuestionAlreadyExist, EventFinished {

		Date d = sdf.parse("05/10/2022");
		ev = testBL.addEvent(queryText, d);

		Question q = sut.createQuestion(ev, "Quien ganará el partido?", betMinimum);

		String question = q.getQuestion();
		assertThrows(QuestionAlreadyExist.class, () -> sut.createQuestion(ev, question, betMinimum));

	}

	@Test
	@DisplayName("Test3: event finished")
	public void test3() throws ParseException, EventFinished, QuestionAlreadyExist {
		Date d = sdf.parse("05/10/2019");
		ev = testBL.addEvent(queryText, d);

	
		assertThrows(EventFinished.class, () -> sut.createQuestion(ev, queryText, betMinimum));

	}

	
	
	@Test
	@DisplayName("The event has NOT one question with a queryText")
	void createQuestionBLTest2() throws ParseException, EventFinished, QuestionAlreadyExist {

		try {
			Date oneDate = sdf.parse("05/10/2022");

			ev = testBL.addEvent(queryText, oneDate);

			Question q = sut.createQuestion(ev, queryText, betMinimum);

			assertNotNull(q);
			assertEquals(queryText, q.getQuestion());
			assertEquals(betMinimum, q.getBetMinimum(), 0);

		

		} finally {
			boolean b = testBL.removeEvent(ev);
			System.out.println("Finally " + b);
		}
	}

}
