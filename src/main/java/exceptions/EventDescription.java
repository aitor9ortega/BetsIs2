package exceptions;

public class EventDescription extends Exception{
	private static final long serialVersionUID = 1L;
	
	public EventDescription() {
		super();
	}
	public EventDescription(String s) {
		super(s);
	}
}
