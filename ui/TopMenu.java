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
            JTextArea zonArea = new JTextArea();
            zonArea.setEditable(false);
            String filmRech=rechercheTxt.getText();
            HashMap<Film, List<CD>> resultat = out.rechercherFilm(filmRech);
            for (Map.Entry<Film, java.util.List<CD>> map : resultat.entrySet()) {
                Film filmRes = map.getKey();
                String res = filmRes.toString();
                java.util.List<CD> biblio = map.getValue();
                if (biblio == null) {
                    res = (res + " \n Il n'y a pas de dvd disponible pour ce film");
                    zonArea.setText(res);
                } else {
                    res = res + "il y a " + map.getValue() + " cd disponible";
                    res = ("\n" + res);
                    zonArea.setText(res);
                }
            }
            panelAff.add(zonArea,BorderLayout.CENTER);
            panelAff.revalidate();
        }
    }
}
