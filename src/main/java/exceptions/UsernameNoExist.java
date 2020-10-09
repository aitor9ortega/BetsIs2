package exceptions;

public class UsernameNoExist extends Exception{
	private static final long serialVersionUID = 1L;
	
	public UsernameNoExist() {
		super();
	}
	public UsernameNoExist(String s) {
		super(s);
	}
}
