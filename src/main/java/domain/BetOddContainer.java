package domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;

public class BetOddContainer implements Serializable{
	private Bet bet;
	private Collection<OddEventContainer> odds;
	public BetOddContainer() {
		super();
	}
	public BetOddContainer(Bet bet, Collection<OddEventContainer> odds) {
		super();
		this.bet = bet;
		this.odds = odds;
	}
	public Bet getBet() {
		return bet;
	}
	public void setBet(Bet bet) {
		this.bet = bet;
	}
	public Collection<OddEventContainer> getOdds() {
		return odds;
	}
	public void setOdds(Collection<OddEventContainer> odds) {
		this.odds = odds;
	}
	public String toString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder s = new StringBuilder();
		
		for (OddEventContainer o : odds) {
			s.append(" | ");
			s.append(o.getEvent().toString());
			s.append(" : ");
			s.append(o.getOdd().getResultBet());
		}
		
		return bet.getSituation() +": "+ "Apostatutako dirua: " +bet.getBetMoney()+ " " + s.toString() + " "+ bet.getFee() + simpleDateFormat.format(bet.getDate());
	}
}
