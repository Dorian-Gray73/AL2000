package ui;

import fc.CD;
import fc.FacadeNf;
import fc.Film;

import javax.swing.*;
import java.awt.Adjustable;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.util.*;

public class Principale extends JPanel{
    private JScrollPane scrollBar;

    private FacadeNf out = new FacadeNf();

    public Principale(JPanel parent){
        HashMap<Film, List<CD>> list_film = out.rechercherFilm();
        Set<Film> films = list_film.keySet();
        for (Film film : films) {
            JPanel presentation = new JPanel();//TODO boxLayout prend des param a verif
            presentation.setLayout(new BoxLayout(presentation, BoxLayout.PAGE_AXIS));
            JTextField titre = new JTextField("Titre : " + film.getTitre());
            titre.setEditable(false);
            presentation.add(titre);
            JTextField acteurs = new JTextField("Acteur principale : " + film.getActeurs().get(0));
            acteurs.setEditable(false);
            presentation.add(acteurs);
            JTextField producteur = new JTextField("Producteur : " + film.getProducteur());
            producteur.setEditable(false);
            presentation.add(producteur);
            JTextField realisateur = new JTextField("Réalisateur : " + film.getRealisateur());
            realisateur.setEditable(false);
            presentation.add(realisateur);

            JButton QRCode = new JButton("Code à lecture rapide");
            JButton blueray = new JButton("Blueray");

            List<CD> support = list_film.get(film);
            if (support == null){
                blueray.setEnabled(false);
            }
            presentation.add(QRCode);
            presentation.add(blueray);
            add(presentation);
        }
        JScrollPane scrollBar = new JScrollPane(this);
        scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        parent.add(scrollBar, BorderLayout.CENTER);

    }

}
