package be.artesis.timelog.checkboxtree;

import java.util.Vector;

import be.artesis.timelog.view.Project;

@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
public class NamedVector extends Vector {
	Project project;
	
	public NamedVector(Project project) {
		this.project = project;
	}

	public NamedVector(Project project, Object elements[]) {
		this.project = project;
		for (int i = 0, n = elements.length; i < n; i++) {
			add(elements[i]);
		}
	}

	public String toString() {
		return "Project : " + project.getNaam();
	}
}