package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class ArrayListItzuli implements Serializable{
	private ArrayList<User> list;

	public ArrayListItzuli() {
		super();
	}
	public ArrayListItzuli(ArrayList<User> list) {
		super();
		this.list = list;
	}

	public ArrayList<User> getList() {
		return list;
	}

	public void setList(ArrayList<User> list) {
		this.list = list;
	}
	
}
