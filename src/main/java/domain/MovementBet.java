package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class MovementBet extends Movement implements Serializable {
	@XmlIDREF
	private Bet bet;
	public MovementBet() {
		super();
	}
	public MovementBet(float amount, Date d, String type, User us, Bet bet) {
		super(amount, d, type, us);
		this.bet = bet;
	}

	public Bet getBet() {
		return bet;
	}

	public void setBet(Bet bet) {
		this.bet = bet;
	}
	
	

}
