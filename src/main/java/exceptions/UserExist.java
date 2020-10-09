package exceptions;

public class UserExist extends Exception{
	private static final long serialVersionUID = 1L;
	
	public UserExist() {
		super();
	}
	public UserExist(String s) {
		super(s);
	}
}
