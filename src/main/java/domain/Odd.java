package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import exceptions.InvalidRate;
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Odd implements Serializable {
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer oddId;
	@OneToOne
	@XmlIDREF
	private Question question;
	private float fee;
	private String resultBet;
	@OneToMany(cascade=CascadeType.PERSIST)
	private Collection<Bet> bets;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Collection<Integer> betsId;
	
	public Collection<Integer> getBetsId() {
		return betsId;
	}
	public void setBetsId(Collection<Integer> betsId) {
		this.betsId = betsId;
	}
	public Odd() {
		this.bets=new HashSet<Bet>();
		this.betsId  = new ArrayList<Integer>();
	}
	public Odd(float fee, String result,Question question) throws InvalidRate{
		if(fee < 1){
			throw new InvalidRate();
		}else {
			this.fee=fee;
		}
		this.resultBet=result;
		this.question=question;
		this.bets=new HashSet<Bet>();
		this.betsId  = new ArrayList<Integer>();
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public int getOddId() {
		return oddId;
	}

	public void setOddId(int oddId) {
		this.oddId = oddId;
	}

	public float getFee() {
		return fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
	}

	public String getResultBet() {
		return resultBet;
	}

	public void setResultBet(String resultBet) {
		this.resultBet = resultBet;
	}
	public void addBet(Bet b) {
		this.bets.add(b);
	}
	@Override
	public String toString() {
		return this.resultBet;
	}
	public Collection<Bet> getBets() {
		return bets;
	}
	
	public void addBetId(int id) {
		this.betsId.add(id);
	}
	public void setBets(Collection<Bet> bets) {
		this.bets = bets;
	}
	
	
}
