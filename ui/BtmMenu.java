package ui;

import javax.swing.*;

import fc.CD;
import fc.ErreurRenduException;
import fc.FacadeNf;
import fc.Film;
import fc.Dao.FilmDaoImp;
import fc.Dao.LocationDaoImp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BtmMenu extends JPanel {
	private JButton connexionButton;
	private JButton deconnexionButton;
	private JButton rendreButton;
	
	private JButton creationDeCompteButton;
	private JButton histoBtn;
	private JButton filtreBtn;
	private FacadeNf out;

	public BtmMenu(JPanel panelAff, FacadeNf out) {
		super();

		setLayout(new FlowLayout());
		this.out = out;
		connexionButton = new JButton("Connexion");
		deconnexionButton = new JButton("Deconnexion");
		rendreButton = new JButton("Rendre");
		creationDeCompteButton = new JButton("Créer");

		


		//Bouton conditionnel à la connexion
		histoBtn = new JButton("historique");
		histoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelAff.removeAll();
				panelAff.add(new Historique(panelAff, out));
				panelAff.revalidate();
				panelAff.repaint();
			}
		});
		
		filtreBtn = new JButton("Ajouter Filtre");
		
		add(rendreButton);
		
		add(connexionButton);

		deconnexionButton.setEnabled(false);
		add(deconnexionButton);
		add(creationDeCompteButton);

		rendreButton.addActionListener(new RendreAction(this, panelAff));
		connexionButton.addActionListener(new ConnexionAction(this, panelAff, out));
		deconnexionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(0);
				connexionButton.setEnabled(true);
				deconnexionButton.setEnabled(false);
			}
		});

		creationDeCompteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelAff.removeAll();
				panelAff.add(new Souscription(panelAff, out));
				panelAff.revalidate();
				panelAff.repaint();
			}
		});
	}

	public JButton getHistoBtn() {
		return histoBtn;
	}
	
	public JButton getFiltreBtn() {
		return filtreBtn;
	}
	
	public JButton getConnexionButton() {
		return connexionButton;
	}

	public JButton getDeconnexionButton() {
		return deconnexionButton;
	}
	
	private class ConnexionAction implements ActionListener {
		private JPanel btmPanel;
		private JPanel panelAff;
		private FacadeNf out;

		public ConnexionAction(JPanel panel, JPanel panelAff, FacadeNf out) {
			super();
			this.btmPanel = panel;
			this.panelAff = panelAff;
			this.out = out;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// changement affichage central
			panelAff.removeAll();
			
			panelAff.add(new Connexion(panelAff, (BtmMenu) btmPanel, out));
			panelAff.revalidate();
			panelAff.repaint();
		}
	}

	private class RendreAction implements ActionListener {
		private JPanel btmPanel;
		private JPanel panelAff;

		public RendreAction(JPanel panel, JPanel panelAff) {
			super();
			this.btmPanel = panel;
			this.panelAff = panelAff;
		}	
		@Override
		public void actionPerformed(ActionEvent e) {
			
			panelAff.removeAll();
			panelAff.add(new Rendre(panelAff, out));
			panelAff.revalidate();
			panelAff.repaint();

		}

	}

}
