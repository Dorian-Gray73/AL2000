package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fc.AbonnementNonReconnusException;
import fc.FacadeNf;

public class Connexion extends JPanel {
	JLabel nomLabel;
	JTextField nomField;
	JLabel prenomLabel;
	JTextField prenomField;
	JButton filtresBtn;
	BtmMenu btmMenu;

	public Connexion(JPanel panelAff, BtmMenu btmMenu, FacadeNf out) {
		super();
		this.btmMenu = btmMenu;
		setLayout(new GridLayout(5, 1));
		add(nomLabel = new JLabel("Nom"));
		add(nomField = new JTextField());
		add(prenomLabel = new JLabel("Prenom"));
		add(prenomField = new JTextField());
		add(filtresBtn = new JButton("log in"));

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

		add(retourButton);

		filtresBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					out.connexion(nomField.getText(), prenomField.getText());
					btmMenu.add(btmMenu.getHistoBtn());
					btmMenu.add(btmMenu.getFiltreBtn());
					btmMenu.revalidate();
					JOptionPane.showMessageDialog(null, "Vous êtes connecté.", "Connecté",
							JOptionPane.INFORMATION_MESSAGE);

					btmMenu.add(new JLabel(nomField.getText() + " " + prenomField.getText() + " : vous êtes connecté."), 0);
					
					btmMenu.getConnexionButton().setEnabled(false);
					btmMenu.getDeconnexionButton().setEnabled(true);
					btmMenu.revalidate();
					
					panelAff.removeAll();
					panelAff.revalidate();
					panelAff.add(new Principale(panelAff,out));

				} catch (AbonnementNonReconnusException e1) {
					JOptionPane.showMessageDialog(null, "La connexion a échoué", "Utilisateur inconnu",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
	}
}
