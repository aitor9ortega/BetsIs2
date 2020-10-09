package exceptions;

public class MinAmountNotReached extends Exception{
	private static final long serialVersionUID = 1L;
	
	public MinAmountNotReached() {
		super();
	}
	public MinAmountNotReached(String s) {
		super(s);
	}
}