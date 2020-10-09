package domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.SortedMap;
public class SortedMapItzuli implements Serializable {
	SortedMap<Float,ArrayListItzuli> map;
	
	public SortedMap<Float, ArrayListItzuli> getMap() {
		return map;
	}
	public void setMap(SortedMap<Float, ArrayListItzuli> map) {
		this.map = map;
	}
	
	public SortedMapItzuli() {
		
	}
	public SortedMapItzuli(SortedMap<Float, ArrayListItzuli> map) {
		this.map=map;
	}
}
