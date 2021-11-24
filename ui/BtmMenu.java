package ui;

import javax.swing.*; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BtmMenu extends JPanel {
    private JButton connexionButton;
    private JButton rendreButton;
    private JButton empruntButton;
    private JButton creationDeCompteButton;

    public BtmMenu(JPanel panelAff) {
        super();

        setLayout(new FlowLayout());

        connexionButton = new JButton("Connexion");
        rendreButton = new JButton("Rendre");
        empruntButton = new JButton("Emprunter");
        creationDeCompteButton = new JButton("Créer");

        add(rendreButton);
        add(empruntButton);
        add(connexionButton);

         connexionButton.addActionListener(new ConnexionAction(this,panelAff));

         creationDeCompteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });   
    }
    
    private class ConnexionAction implements ActionListener {
        private JPanel btmPanel;
        private JPanel panelAff;
        public ConnexionAction(JPanel panel, JPanel panelAff) {
            super();
            this.btmPanel = panel;
            this.panelAff = panelAff;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("test");
            btmPanel.add(new JButton("histo"));
            btmPanel.revalidate();
            //changement affichage central
            panelAff.add(new JLabel("Test"));
            panelAff.revalidate();
        }
    }
}
