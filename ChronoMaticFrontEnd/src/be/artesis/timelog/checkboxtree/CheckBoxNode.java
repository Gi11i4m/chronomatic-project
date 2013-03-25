package be.artesis.timelog.checkboxtree;

import be.artesis.timelog.view.Project;
import be.artesis.timelog.view.Taak;

public class CheckBoxNode {
	Taak taak;

	boolean selected;

	public CheckBoxNode(Taak taak, boolean selected) {
		this.taak = taak;
		this.selected = selected;
	}

	public CheckBoxNode() {		
	}

	public CheckBoxNode(Project p, boolean selected2) {
		// TODO Auto-generated constructor stub
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean newValue) {
		selected = newValue;
	}

	public String getText() {
		return taak.getNaam();
	}

	public void setText(Taak newTask) {
		taak = newTask;
	}

	public String toString() {
		return getClass().getName() + "Taak : " + taak.getNaam() + "/" + selected;
	}
}