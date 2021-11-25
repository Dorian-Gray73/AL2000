package ui;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import fc.Location;

public class HistoCellRenderer extends JLabel implements ListCellRenderer<Location> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Location> list, Location value, int index,
			boolean isSelected, boolean cellHasFocus) {
        setText(value.getSupport().getFilm().getTitre());
        
        Color foreground;
        
        if(isSelected) {
            foreground = Color.RED;
        } else {
            foreground = Color.BLACK;
        }
        
        setForeground(foreground);
        
        return this;
	}

}