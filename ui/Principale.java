package ui;

import fc.CD;
import fc.FacadeNf;
import fc.Film;
import fc.QRCode;

import javax.swing.*;
import java.awt.BorderLayout;
import java.util.*;

public class Principale extends JPanel{

    // Si jamais on essaie de retirer la scrollbar de partout
    // on aura besoin de ces paramètres.
    private JScrollPane scrollBar;
    //private JPanel parent;

    private  FacadeNf out;

    public Principale(JPanel parent) {
        this(parent, (FacadeNf)null);
    }

	public Principale(JPanel parent, FacadeNf out){
        //this.parent = parent;
        if (out != null)
            this.out = out;
        HashMap<Film, List<CD>> list_film = out.rechercherFilm();
        Set<Film> films = list_film.keySet();
        for (Film film : films) {
            JPanel presentation = new JPanel();
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

            JButton qrcode = new JButton("Code à lecture rapide");
            JButton blueray = new JButton("Blueray");

            List<CD> support = list_film.get(film);
            if (support == null){
                blueray.setEnabled(false);
            } else {
                blueray.addActionListener(new ActionEmprunt(support.get(0), out));
            }
            qrcode.addActionListener(new ActionEmprunt(new QRCode(film), out));
            presentation.add(qrcode);
            presentation.add(blueray);
            add(presentation);
        }
        scrollBar = new JScrollPane(this);
        scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        parent.add(scrollBar, BorderLayout.CENTER);

    }

    /* @Override
    public void removeAll(){
        super.removeAll();
        if (parent != null && scrollBar != null){
            parent.remove(scrollBar);
            parent.add(this);
            add(new JTextField("Petit test pour voir ce qu'il en est."));
            System.out.println("Ouais je mets des test de partout");
        }
    } */

}
