package be.artesis.timelog.gui;

import be.artesis.timelog.view.Project;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;



class ProjectCellRenderer extends JLabel implements ListCellRenderer {

    private Project p;
    private static DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
    // private static LineBorder border = new LineBorder(Color.BLACK, 1);

     public ProjectCellRenderer() {
         setOpaque(true);
     }
    @Override
     public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
     {
        JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        p = (Project)value;

        try {
            if (p.equals(UserControl.getCurrentProject())) {
                // renderer.setFont(new Font("Tahoma", Font.BOLD, 11));
                renderer.setForeground(Color.GREEN);
            }
        } catch (GUIException ex) {
        }

        return renderer;
     }

 }