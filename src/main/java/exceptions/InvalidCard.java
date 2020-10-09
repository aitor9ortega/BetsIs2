package exceptions;

public class InvalidCard extends Exception{
	private static final long serialVersionUID = 1L;
	
	public InvalidCard() {
		super();
	}
	public InvalidCard(String s) {
		super(s);
	}
}
