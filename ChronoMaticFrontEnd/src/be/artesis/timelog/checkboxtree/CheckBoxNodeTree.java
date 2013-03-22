package be.artesis.timelog.checkboxtree;

/*
Definitive Guide to Swing for Java 2, Second Edition
By John Zukowski     
ISBN: 1-893115-78-X
Publisher: APress
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

public class CheckBoxNodeTree {
	public static void main(String args[]) {
		JFrame frame = new JFrame("CheckBox Tree");

		CheckBoxNode accessibilityOptions[] = { new CheckBoxNode("Move system caret with focus/selection changes", false), new CheckBoxNode("Always expand alt text for images", true) };
		CheckBoxNode browsingOptions[] = { new CheckBoxNode("Notify when downloads complete", true), new CheckBoxNode("Disable script debugging", true), new CheckBoxNode("Use AutoComplete", true), new CheckBoxNode("Browse in a new process", false) };
		Vector accessVector = new NamedVector("Accessibility", accessibilityOptions);
		Vector browseVector = new NamedVector("Browsing", browsingOptions);
		Object rootNodes[] = { accessVector, browseVector };
		Vector rootVector = new NamedVector("Root", rootNodes);
		JTree tree = new JTree(rootVector);

		CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();
		tree.setCellRenderer(renderer);

		tree.setCellEditor(new CheckBoxNodeEditor(tree));
		tree.setEditable(true);

		JScrollPane scrollPane = new JScrollPane(tree);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setSize(300, 150);
		frame.setVisible(true);
	}
}

	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row) {

		Component editor = renderer.getTreeCellRendererComponent(tree, value, true, expanded, leaf, row, true);

		// editor always selected / focused
		ItemListener itemListener = new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				if (stopCellEditing()) {
					fireEditingStopped();
				}
			}
		};
		if (editor instanceof JCheckBox) {
			((JCheckBox) editor).addItemListener(itemListener);
		}

		return editor;
	}
}

public class CheckBoxNode {
	String text;

	boolean selected;

	public CheckBoxNode(String text, boolean selected) {
		this.text = text;
		this.selected = selected;
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
		return getClass().getName() + "[" + text + "/" + selected + "]";
	}
}