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
			JButton retourButton = new JButton("Retour");
		
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TO
			JPanel dmgPanel = new JPanel(new GridLayout(3,1));
			JPanel numPanel = new JPanel(new GridLayout(3,1));
			JTextField locField = new JTextField();
			JLabel dmgLabel = new JLabel();
			JLabel numLabel = new JLabel();
			JButton valButton = new JButton("Validation");
			JButton retourButton = new JButton("retour");
			JCheckBox dmgdBox = new JCheckBox();
			
			

			retourButton.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					panelAff.removeAll();
					panelAff.add(new Principale(panelAff,out));
					panelAff.revalidate();
					panelAff.repaint();
				}
			});


			dmgLabel.setText("Cocher si le cd a ete endommage");
			numLabel.setText("Entrer votre numero de location");

			dmgPanel.add(dmgLabel);
			dmgPanel.add(dmgdBox);
			dmgPanel.add(retourButton);

			numPanel.add(numLabel);
			numPanel.add(locField);
			numPanel.add(valButton);

			panelAff.removeAll();
			panelAff.add(numPanel,BorderLayout.WEST);
			panelAff.add(dmgPanel,BorderLayout.EAST);

			panelAff.revalidate();
			panelAff.repaint();

			valButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					try {
						String numStr = locField.getText();
						int num = Integer.parseInt(numStr);
						boolean damage = dmgdBox.isSelected();

						out.rendre(num, out.getCDEnCours(), damage); 

						JOptionPane.showMessageDialog(null, " Votre rendu a ete effectué", "Rendu",
							JOptionPane.INFORMATION_MESSAGE);

							
					} 
					catch(ErreurRenduException e2){
						JOptionPane.showMessageDialog(null, "Le rendu a échoué", "Il n'y a pas de location a ce numero",
							JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
					}
					catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Le rendu a échoué", "Il y a un soucis au niveau de vos insertion",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
					}
				}
			});


		}

	}

	// private class EmprunterAction implements ActionListener {
	// 	private JPanel btmPanel;
	// 	private JPanel panelAff;

	// 	public EmprunterAction(JPanel panel, JPanel panelAff) {
	// 		super();
	// 		btmPanel = panel;
	// 		this.panelAff = panelAff;
	// 	}

	// 	@Override
	// 	public void actionPerformed(ActionEvent e) {
	// 		// TODO Emprunt

	// 	}
	// }

	// private class CreerAction implements ActionListener {
	// 	private JPanel btmPanel;
	// 	private JPanel panelAff;

	// 	public CreerAction(JPanel panel, JPanel panelAff) {
	// 		super();
	// 		btmPanel = panel;
	// 		this.panelAff = panelAff;
	// 	}

	// 	@Override
	// 	public void actionPerformed(ActionEvent e) {
	// 		// TODO creation d un compte
	// 		// Lance la fenetre souscrire

	// 	}
	// }
}
