package ui;

import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fc.Location;

public class ListInteraction implements ListSelectionListener {
	private JList<Location> locationsList;
	private JTextPane locationInfo;
	
	public ListInteraction(JList<Location> locationsList, JTextPane locationInfo) {
		super();
		this.locationsList = locationsList;
		this.locationInfo = locationInfo;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		locationInfo.setText(locationsList.getSelectedValue().toString());
	}
	
}
