package ui;

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import fc.Location;


public class Historique extends JPanel {
	private JList<Location> locationsList;
	private JTextPane locationInfo;
	
	public Historique() {
		super();
		setLayout(new BorderLayout(10, 5));
		
		locationsList = new JList<Location>();
		locationInfo = new JTextPane();
		
		new ListInteraction(locationsList, locationInfo);
		
		add(locationsList, BorderLayout.WEST);
		add(locationInfo, BorderLayout.EAST);
		
	}
}
