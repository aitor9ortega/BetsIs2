package exceptions;

public class BadDomain extends Exception{
	private static final long serialVersionUID = 1L;
	
	public BadDomain() {
		super();
	}
	public BadDomain(String s) {
		super(s);
	}
}
