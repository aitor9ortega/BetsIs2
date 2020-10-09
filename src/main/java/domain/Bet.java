package domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Bet implements Serializable {
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Collection<Odd> odds;
	@OneToOne
	@XmlIDREF
	private User user;
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer betId;
	private float fee;
	private float betMoney;
	private String situation;
	private Date date;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Collection<MovementBet> movementBets;
	
	public Bet() {
		this.movementBets=new HashSet<MovementBet>();
	}
	public Bet(Collection<Odd> odd,User user,float fee,float betMoney, String situation) {
		this.odds=odd;
		this.user=user;
		this.fee=fee;
		this.betMoney=betMoney;
		this.situation=situation;
		this.date = new Date();
		this.movementBets=new HashSet<MovementBet>();
	}
	
	public int getBetId() {
		return betId;
	}

	public void setBetId(int betId) {
		this.betId = betId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Collection<MovementBet> getMovementBets() {
		return movementBets;
	}

	public void setMovementBets(Collection<MovementBet> movementBets) {
		this.movementBets = movementBets;
	}

	public Collection<Odd> getOdds() {
		return odds;
	}
	public void setOdds(Collection<Odd> odd) {
		this.odds = odd;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public float getFee() {
		return fee;
	}
	public void setFee(float fee) {
		this.fee = fee;
	}
	public float getBetMoney() {
		return betMoney;
	}
	public void setBetMoney(float betMoney) {
		this.betMoney = betMoney;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public void addMovementBet(MovementBet m) {
		movementBets.add(m);
	}
	@Override
	public String toString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder s = new StringBuilder();
		
		for (Odd o : odds) {
			s.append(" | ");
			s.append(o.getQuestion().getEvent().toString());
			s.append(" : ");
			s.append(o.getResultBet());
		}
		
		return situation +": "+ "Apostatutako dirua: " + betMoney+ " " + s.toString() + " "+ fee + simpleDateFormat.format(date);
	}
	
}
