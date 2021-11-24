package ui;

import fc.CD;
import fc.FacadeNf;
import fc.Film;

import javax.swing.*;
import java.awt.Adjustable;
import java.awt.Dimension;
import java.util.*;

public class Principale extends JPanel{
    private JScrollPane scrollBar;

    private FacadeNf out = new FacadeNf();

    public Principale(){
        super();
        JPanel ecran = new JPanel();
        HashMap<Film, List<CD>> list_film = out.rechercherFilm();
        Set<Film> films = list_film.keySet();
        for (Film film : films) {
            JPanel presentation = new JPanel(new BoxLayout(this, BoxLayout.LINE_AXIS));//TODO boxLayout prend des param a verif
            JTextField titre = new JTextField("Titre : " + film.getTitre());
            presentation.add(titre);
            JTextField acteurs = new JTextField("Acteur principale : " + film.getActeurs().get(0));
            presentation.add(acteurs);
            JTextField producteur = new JTextField("Producteur : " + film.getProducteur());
            presentation.add(producteur);
            JTextField realisateur = new JTextField("Réalisateur : " + film.getRealisateur());
            presentation.add(realisateur);

            JButton QRCode = new JButton("Code à lecture rapide");
            JButton blueray = new JButton("Blueray");

            List<CD> support = list_film.get(film);
            if (support == null){
                blueray.setEnabled(false);
            }
            presentation.add(QRCode);
            presentation.add(blueray);
            ecran.add(presentation);
        }
        scrollBar = new JScrollPane(ecran);
        add(scrollBar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Principale().setVisible(true);
            }
        });
    }
}
