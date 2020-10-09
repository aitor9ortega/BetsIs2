package domain;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import exceptions.InvalidRate;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Question implements Serializable {
	@XmlID
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer questionNumber;
	private String question; 
	private float betMinimum;
	private Odd result;  
	@XmlIDREF
	private Event event;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Collection<Odd> odds;

	public Question(){
		super();
		odds=new HashSet<Odd>();
	}
	
	public Question(Integer queryNumber, String query, float betMinimum, Event event) {
		super();
		this.questionNumber = queryNumber;
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
		odds=new HashSet<Odd>();
	}
	
	public Question(String query, float betMinimum,  Event event) {
		super();
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
		odds=new HashSet<Odd>();
	}

	/**
	 * Get the  number of the question
	 * 
	 * @return the question number
	 */
	public Integer getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * Set the bet number to a question
	 * 
	 * @param questionNumber to be setted
	 */
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}


	/**
	 * Get the question description of the bet
	 * 
	 * @return the bet question
	 */

	public String getQuestion() {
		return question;
	}


	/**
	 * Set the question description of the bet
	 * 
	 * @param question to be setted
	 */	
	public void setQuestion(String question) {
		this.question = question;
	}



	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @return the minimum bet ammount
	 */
	
	public float getBetMinimum() {
		return betMinimum;
	}


	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @param  betMinimum minimum bet ammount to be setted
	 */

	public void setBetMinimum(float betMinimum) {
		this.betMinimum = betMinimum;
	}



	/**
	 * Get the result of the  query
	 * 
	 * @return the the query result
	 */
	public Odd getResult() {
		return result;
	}



	/**
	 * Get the result of the  query
	 * 
	 * @param result of the query to be setted
	 */
	
	public void setResult(Odd result) {
		this.result = result;
	}



	/**
	 * Get the event associated to the bet
	 * 
	 * @return the associated event
	 */
	public Event getEvent() {
		return event;
	}



	/**
	 * Set the event associated to the bet
	 * 
	 * @param event to associate to the bet
	 */
	public void setEvent(Event event) {
		this.event = event;
	}


	public Odd addOdd(float fee,String result) throws InvalidRate {
		Odd o=new Odd(fee,result,this);	
		if(!this.odds.add(o)) {
			return null;
		}
		return o;
	}

	public Collection<Odd> getOdds(){
		return this.odds;
	}
	public String toString(){
		return question;
	}




	
}