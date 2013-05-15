package be.artesis.timelog.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import be.artesis.timelog.view.Opdrachtgever;
import be.artesis.timelog.view.Project;

public class ClientCellRenderder extends JLabel implements ListCellRenderer {

	private Opdrachtgever o;
	private static DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

	// private static LineBorder border = new LineBorder(Color.BLACK, 1);

	public ClientCellRenderder() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (value.getClass().equals(String.class)) {
			Font f = list.getFont();
			renderer.setFont(new Font(f.getName(), f.getStyle() | Font.ITALIC, f.getSize()));
		} else {
			o = (Opdrachtgever) value;
			try {
				if (o.getID() == UserInterface.getCurrentProject().getOpdrachtgeverId()) {
					renderer.setForeground(new Color(25, 97, 252));
				}
			} catch (GUIException ex) {
				// Dit moet leeg zijn
			}
		}
		return renderer;
	}

}
