package be.artesis.timelog.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import be.artesis.timelog.view.Taak;

@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
class TaskCellRenderer extends JLabel implements ListCellRenderer {

	private Taak t;
	private static DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

	public TaskCellRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		Font f = list.getFont();
		if (value.getClass().equals(String.class)) {
			renderer.setFont(new Font(f.getName(), f.getStyle() | Font.ITALIC, f.getSize()));
		} else {
			t = (Taak) value;

			if (t.getCompleted()) {
				Map attributes = f.getAttributes();
				attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
				Font font = new Font(attributes);
				renderer.setFont(font);
			} else if (t.overTijd()) {
				renderer.setForeground(Color.red);
				//renderer.setIcon(warning);
			}
		}
		return renderer;
	}

}
