package ui;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

import fc.Location;

public class HistoCellRenderer implements ListCellRenderer<Object> {

    public HistoCellRenderer() {
        //setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        Location location = (Location)value;
        //setText(location.getSupport().getFilm().getTitre());
        return this;
    }

}