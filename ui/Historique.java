package ui;

import java.awt.BorderLayout;
import java.time.LocalDateTime;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import fc.FacadeNf;
import fc.Location;


public class Historique extends JPanel {
	private JList<Location> locationsList;
	private JTextPane locationInfo;
	
	public Historique(FacadeNf out) {
		super();
		setLayout(new BorderLayout(10, 5));
		
		locationsList = new JList<Location>();
		
		DefaultListModel<Location> location = new DefaultListModel<Location>();
		location.addElement(new Location(LocalDateTime.now(), 4.0, null, null));
		listToModel(out, location);
		locationsList.setModel(location);
		
		
		locationInfo = new JTextPane();
		locationInfo.setEditable(false);
		
		new ListInteraction(locationsList, locationInfo);
		
		add(locationsList, BorderLayout.WEST);
		add(locationInfo, BorderLayout.EAST);
		
	}

	private void listToModel(FacadeNf out, DefaultListModel<Location> location) {
		if(out.consulterHistorique() != null)
			out.consulterHistorique().stream().forEach(loc -> location.addElement(loc));
	}
}
