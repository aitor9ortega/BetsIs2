package junitMock;
/**
 * DataAccessCreateQuestionTest: Some JUnit example for DataAccess
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import exceptions.QuestionAlreadyExist;
import test.businessLogic.TestFacadeImplementation;

class DataAccessCreateQuestionTest {
	private DataAccess sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));;
	private TestFacadeImplementation testBL = new TestFacadeImplementation();

	private String queryText = "A question";
	private Float betMinimum = 2.0f;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Event ev;



	@Test
	@DisplayName("Test 1: question already exist")
	public void test1() throws QuestionAlreadyExist, ParseException{
		
		Date d = sdf.parse("05/10/2022");
		ev = testBL.addEvent(queryText, d);
		
		Question q = sut.createQuestion(ev, "Quien ganará el partido?", betMinimum);
	
		
	
		String question = q.getQuestion();
		assertThrows(QuestionAlreadyExist.class, () -> sut.createQuestion(ev, question, betMinimum));
		   
		

	}
	@Test
	@DisplayName("The event has NOT one question with a queryText")
	void createQuestionDATest2() throws ParseException, QuestionAlreadyExist {

		try {
			Date oneDate = sdf.parse("05/10/2022");

			ev = testBL.addEvent(queryText, oneDate);

			Question q = sut.createQuestion(ev, queryText, betMinimum);

			assertNotNull(q);
			assertEquals(queryText, q.getQuestion());
			assertEquals(betMinimum, q.getBetMinimum(), 0);

	
		} finally {
			boolean b = testBL.removeEvent(ev);
			assertTrue(b);
		}
	}
}
