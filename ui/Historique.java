package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import fc.FacadeNf;
import fc.Film;
import fc.Location;
import fc.QRCode;



public class Historique extends JPanel {
	private JList<Location> locationsList;
	private JTextPane locationInfo;
	
	public Historique(JPanel panelAff, FacadeNf out) {
		super();
		setLayout(new BorderLayout(10, 5));
		
		locationsList = new JList<Location>();
		
		DefaultListModel<Location> location = new DefaultListModel<Location>();
		location.addElement(new Location(LocalDateTime.now(), 4.0, null, new QRCode(new Film("mourir", "espion"))));
		location.addElement(new Location(LocalDateTime.now().plusHours(10), 4.0, null, new QRCode(new Film("logan", "action"))));
		listToModel(out, location);
		locationsList.setModel(location);
		locationsList.setCellRenderer(new HistoCellRenderer());

		locationInfo = new JTextPane();
		locationInfo.setText("Aperçu de la location sélectionnée.");
		locationInfo.setEditable(false);
		
		new ListInteraction(locationsList, locationInfo);
		
		JButton retourButton = new JButton("Retour");
		retourButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panelAff.removeAll();
				panelAff.add(new Principale(panelAff,out));
				panelAff.revalidate();
				panelAff.repaint();
			}
		});
		
		add(new JLabel("Voici vos location vous pouvez en sélectionner une."), BorderLayout.NORTH);
		add(locationsList, BorderLayout.WEST);
		add(locationInfo, BorderLayout.EAST);
		add(retourButton, BorderLayout.SOUTH);
		
	}

	private void listToModel(FacadeNf out, DefaultListModel<Location> location) {
		if(out.consulterHistorique() != null)
			out.consulterHistorique().stream().forEach(loc -> location.addElement(loc));
	}
}
