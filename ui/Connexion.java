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
        add(connexioButton = new JButton("Connexion"));
        //connexioButton.addActionListener(l);
    }
    
}
