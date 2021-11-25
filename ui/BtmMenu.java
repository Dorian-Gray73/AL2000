package ui;

import javax.swing.*;

import fc.FacadeNf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BtmMenu extends JPanel {
	private JButton connexionButton;
	private JButton rendreButton;
	private JButton empruntButton;
	private JButton creationDeCompteButton;
	private JButton histoBtn;
	private JButton filtreBtn;

	public BtmMenu(JPanel panelAff, FacadeNf out) {
		super();

		setLayout(new FlowLayout());

		connexionButton = new JButton("Connexion");
		rendreButton = new JButton("Rendre");
		empruntButton = new JButton("Emprunter");
		creationDeCompteButton = new JButton("Créer");

		//Bouton conditionnel à la connexion
		histoBtn = new JButton("historique");
		histoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelAff.removeAll();
				panelAff.add(new Historique(out));
				panelAff.revalidate();
			}
		});
		
		filtreBtn = new JButton("Ajouter Filtre");
		
		add(rendreButton);
		add(empruntButton);
		add(connexionButton);
		add(creationDeCompteButton);

		rendreButton.addActionListener(new RendreAction(this, panelAff));
		connexionButton.addActionListener(new ConnexionAction(this, panelAff, out));
		empruntButton.addActionListener(new EmprunterAction(this, panelAff));

		creationDeCompteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelAff.removeAll();
				panelAff.add(new Souscription());
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
			// TODO Rendre
			JPanel rendrePanel = new JPanel();
			JTextField LocField = new JTextField();
			JCheckBox endBox = new JCheckBox();

		}

	}

	private class EmprunterAction implements ActionListener {
		private JPanel btmPanel;
		private JPanel panelAff;

		public EmprunterAction(JPanel panel, JPanel panelAff) {
			super();
			btmPanel = panel;
			this.panelAff = panelAff;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Emprunt

		}
	}

	private class CreerAction implements ActionListener {
		private JPanel btmPanel;
		private JPanel panelAff;

		public CreerAction(JPanel panel, JPanel panelAff) {
			super();
			btmPanel = panel;
			this.panelAff = panelAff;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO creation d un compte
			// Lance la fenetre souscrire

		}
	}
}
