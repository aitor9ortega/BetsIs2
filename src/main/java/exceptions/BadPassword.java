package exceptions;

public class BadPassword extends Exception{
	private static final long serialVersionUID = 1L;
	
	public BadPassword() {
		super();
	}
	public BadPassword(String s) {
		super(s);
	}
}
