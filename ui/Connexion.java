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

		filtresBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					out.connexion(nomField.getText(), prenomField.getText());
					btmMenu.add(btmMenu.getHistoBtn());
					btmMenu.add(btmMenu.getFiltreBtn());
					btmMenu.revalidate();
					JOptionPane.showMessageDialog(null, " : Vous êtes connecté.", "Connecté",
							JOptionPane.INFORMATION_MESSAGE);

					btmMenu.add(new JLabel(nomField.getText() + "vous êtes connecté."), 0);
					panelAff.removeAll();
					panelAff.add(new Principale(panelAff));

				} catch (AbonnementNonReconnusException e1) {
					JOptionPane.showMessageDialog(null, "Vous n'avez pas été connecté", "Utilisateur inconnu",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
	}
}
