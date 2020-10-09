package exceptions;

public class NegativeNumber extends Exception{
	private static final long serialVersionUID = 1L;
	
	public NegativeNumber() {
		super();
	}
	public NegativeNumber(String s) {
		super(s);
	}
}
