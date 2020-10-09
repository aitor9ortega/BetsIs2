package exceptions;

public class EventNumber extends Exception{
	private static final long serialVersionUID = 1L;
	
	public EventNumber() {
		super();
	}
	public EventNumber(String s) {
		super(s);
	}
}
