package exceptions;

public class NotFinished extends Exception{
	private static final long serialVersionUID = 1L;
	
	public NotFinished() {
		super();
	}
	public NotFinished(String s) {
		super(s);
	}
}
