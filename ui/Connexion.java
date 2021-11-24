package ui;

import javax.swing.*; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Connexion extends JPanel {
JLabel nomLabel;
JTextField nomField;
JLabel prenomLabel;
JTextField prenomField;
JButton connexioButton;
JPanel btmMenu;
    public Connexion(JPanel panel) {
        super();
        btmMenu = panel;
        setLayout(new GridLayout(5,1));
        add(nomLabel = new JLabel("Nom"));
        add(nomField = new JTextField());
        add(prenomLabel = new JLabel("Prenom"));
        add(prenomField = new JTextField());
        add(connexioButton = new JButton("log in"));
        connexioButton.addActionListener(new LoginAction(btmMenu));
    }
    private class LoginAction implements ActionListener {
        private JPanel btmPanel;
        private JButton histoBtn;
        private JButton filtreBtn;
        public LoginAction(JPanel panel) {
            super();
            this.btmPanel = panel;
            histoBtn = new JButton("historique");
            filtreBtn = new JButton("Ajouter Filtre");
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO Connexion
            btmPanel.add(histoBtn);
            btmPanel.add(filtreBtn);
            btmPanel.revalidate();
        }
    }
    
}
