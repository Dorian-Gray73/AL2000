package ui;

import fc.Film;
import java.util.Iterator;

import javax.swing.*;

public class Consultation extends JPanel {
    private JLabel titreLabel;
    private JLabel genreLabel;
    private JLabel realisateurLabel;
    private JLabel producteurLabel;
    private JTextArea acteursTextArea;
    private JTextArea resumeTextArea;

    public Consultation(Film film) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        titreLabel = new JLabel(film.getTitre());
        genreLabel = new JLabel(film.getGenre());
        realisateurLabel = new JLabel(film.getRealisateur());
        producteurLabel = new JLabel(film.getProducteur());

        acteursTextArea = new JTextArea();
        acteursTextArea.setEditable(false);
        Iterator<String> it = film.getActeurs().iterator();
        StringBuilder acteursStringBuilder = new StringBuilder("");
        while(it.hasNext()) {
            String acteur = it.next();
            acteursStringBuilder.append(acteur + "\n");
        }
        acteursTextArea.setText(acteursStringBuilder.toString());

        resumeTextArea = new JTextArea(film.getResume());
        resumeTextArea.setEditable(false);

        add(titreLabel, LEFT_ALIGNMENT);
        add(genreLabel, LEFT_ALIGNMENT);
        add(realisateurLabel, LEFT_ALIGNMENT);
        add(producteurLabel, LEFT_ALIGNMENT);
        add(acteursTextArea, LEFT_ALIGNMENT);
        add(resumeTextArea, LEFT_ALIGNMENT);
    }
}
