package exceptions;

public class SameRate extends Exception{
	private static final long serialVersionUID = 1L;
	
	public SameRate() {
		super();
	}
	public SameRate(String s) {
		super(s);
	}
}
