package ui;
import fc.*;
import java.util.*;
import java.util.List;

import javax.swing.*; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopMenu extends JPanel {
    private JComboBox<String> comboBox1;
    private JButton rechercher;
    private JTextField rechercheTxt;
    private FacadeNf out = new FacadeNf();

    public TopMenu(JPanel panelAff) {
        super();

        setLayout(new BorderLayout(5,5));

        comboBox1 = new JComboBox<>();
        this.comboBox1.addItem("titre");
        this.comboBox1.addItem("genre");
        this.comboBox1.addItem("nomActeur");
        this.comboBox1.addItem("prenomActeur");
        rechercher = new JButton("Rechercher");
        rechercheTxt = new JTextField();
        add(comboBox1,BorderLayout.EAST);
        add(rechercheTxt,BorderLayout.CENTER);
        add(rechercher,BorderLayout.WEST);

        rechercher.addActionListener(new RechAction(this,panelAff));
        
        rechercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String filmRech=rechercheTxt.getText();
                HashMap<Film, List<CD>> resultat = out.rechercherFilm(filmRech);
                recherche r=new recherche("recherche");
                r.setVisible(true);
            }
        });
    }
    
    private class RechAction implements ActionListener {
        private JPanel TopPanel;
        private JPanel panelAff;
        public RechAction(JPanel panel, JPanel panelAff) {
            super();
            this.TopPanel = panel;
            this.panelAff = panelAff;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("test1");
            TopPanel.add(new JButton("histo"));
            TopPanel.revalidate();
            //changement affichage central
            panelAff.add(new JLabel("Test"));
            panelAff.revalidate();
        }
    }
}
