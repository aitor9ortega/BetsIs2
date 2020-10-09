package domain;

import java.io.Serializable;

public class OddEventContainer implements Serializable{
	private Odd odd;
	private Event event;
	private Question q;
	public OddEventContainer() {
		super();
	}
	public OddEventContainer(Odd odd, Event event, Question q) {
		super();
		this.odd = odd;
		this.event = event;
		this.q=q;
	}
	public Odd getOdd() {
		return odd;
	}
	public void setOdd(Odd odd) {
		this.odd = odd;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	

}
