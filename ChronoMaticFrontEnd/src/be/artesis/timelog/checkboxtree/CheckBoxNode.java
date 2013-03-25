package be.artesis.timelog.checkboxtree;


public class CheckBoxNode {
	String text;
	boolean selected;	

	public CheckBoxNode(String text, boolean selected) {
		this.text = text;
		this.selected = selected;
	}
	
	public CheckBoxNode() {		
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean newValue) {
		selected = newValue;
	}

	public String getText() {
		return text;
	}

	public void setText(String newValue) {
		text = newValue;
	}
	
	public String toString() {	
		return text;
	}
}