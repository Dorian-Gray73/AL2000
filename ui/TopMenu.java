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

        rechercher.addActionListener(new RechAction(panelAff));
        
        rechercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
            }
        });
    }
    





    private class RechAction implements ActionListener {
        private JPanel panelAff;
        public RechAction(JPanel panelAff) {
            super();
            this.panelAff = panelAff;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            String filmRech = rechercheTxt.getText();
            
            HashMap<String, String> filtres = new HashMap<>();
            filtres.put(comboBox1.getSelectedItem().toString(), filmRech);

            HashMap<Film, List<CD>> resultat = out.rechercherFilm(filtres);
            Set<Film> films = resultat.keySet();
            JPanel sousPanel = new JPanel(new GridLayout(films.size() * 2, 1));
            for (Film film : films){
                JTextArea zonArea = new JTextArea();
                zonArea.setEditable(false);
                String res = film.toString();
                List<CD> support = resultat.get(film);
                if (support == null) {
                    res = (res + " \n Il n'y a pas de dvd disponible pour ce film");
                    zonArea.setText(res);
                } else {
                    res = res + "il y a " + support + " cd disponible";
                    res = ("\n" + res);
                    zonArea.setText(res);
                }
                sousPanel.add(zonArea);
                sousPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
            }
            // for (Map.Entry<Film, java.util.List<CD>> map : resultat.entrySet()) {
            //     Film filmRes = map.getKey();
            //     String res = filmRes.toString();
            //     java.util.List<CD> biblio = map.getValue();
            //     if (biblio == null) {
            //         res = (res + " \n Il n'y a pas de dvd disponible pour ce film");
            //         zonArea.setText(res);
            //     } else {
            //         res = res + "il y a " + map.getValue() + " cd disponible";
            //         res = ("\n" + res);
            //         zonArea.setText(res);
            //     }
            // }
            
            panelAff.removeAll();        
            panelAff.add(sousPanel, BorderLayout.CENTER);
            panelAff.revalidate();
            panelAff.repaint();
        }
    }
}
