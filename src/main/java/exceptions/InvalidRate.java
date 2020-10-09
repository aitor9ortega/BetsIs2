package exceptions;

public class InvalidRate extends Exception{
	private static final long serialVersionUID = 1L;
	
	public InvalidRate() {
		super();
	}
	public InvalidRate(String s) {
		super(s);
	}
}
