package exceptions;

public class NoMoney extends Exception{
	private static final long serialVersionUID = 1L;
	
	public NoMoney() {
		super();
	}
	public NoMoney(String s) {
		super(s);
	}
}
