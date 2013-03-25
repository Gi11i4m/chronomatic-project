package be.artesis.timelog.checkboxtree;

import java.util.Vector;

@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
public class NamedVector extends Vector {
	String text;
	
	public NamedVector(String text) {
		this.text = text;
	}

	public NamedVector(String text, Object elements[]) {
		this.text = text;
		for (int i = 0, n = elements.length; i < n; i++) {
			add(elements[i]);
		}
	}

	public String toString() {
		return text;
	}
}