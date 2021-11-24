package ui;

import fc.CD;
import fc.FacadeNf;
import fc.Film;

import javax.swing.*;
import java.awt.Adjustable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Principale extends JPanel{
    private JScrollBar scrollBar;

    private FacadeNf out = new FacadeNf();
    Principale(){
        scrollBar = new JScrollBar(Adjustable.HORIZONTAL);
        add(scrollBar);
        HashMap<Film, List<CD>> list_film = out.rechercherFilm();
        Set<Film> films = list_film.keySet();
        for (Film film : films) {
            JPanel presentation = new JPanel();
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
            if (support.size() == 0){
                blueray.setEnabled(false);
            }
            presentation.add(QRCode);
            presentation.add(blueray);
        }
    }

    public static void main(String[] args){
        //JFrame frame=new Principale("AL2000");
        JPanel frame=new Principale();
        frame.setVisible(true);
    }
}
