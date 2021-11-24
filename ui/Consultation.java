package ui;

import fc.Film;

import java.awt.*;
import java.util.Iterator;

import javax.swing.*;

public class Consultation extends JPanel {
    private JTextField titreTextField;
    private JTextField genreTextField;
    private JTextField realisateurTextField;
    private JTextField producteurTextField;
    private JTextArea acteursTextArea;
    private JTextArea resumeTextArea;

    private JButton qrcodeButton;
    private JButton cdButton;

    public Consultation(Film film) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);
        
        titreTextField = new JTextField(film.getTitre());
        titreTextField.setEditable(false);

        genreTextField = new JTextField(film.getGenre());
        genreTextField.setEditable(false);

        realisateurTextField = new JTextField(film.getRealisateur());
        realisateurTextField.setEditable(false);

        producteurTextField = new JTextField(film.getProducteur());
        producteurTextField.setEditable(false);

        acteursTextArea = new JTextArea();
        acteursTextArea.setEditable(false);

        JPanel panelQR = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        qrcodeButton = new JButton("Code à lecture rapide");
        panelQR.add(qrcodeButton);

        JPanel panelCD = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        cdButton = new JButton("Blueray");
        panelCD.add(cdButton);

        Iterator<String> it = film.getActeurs().iterator();
        StringBuilder acteursStringBuilder = new StringBuilder("");
        while(it.hasNext()) {
            String acteur = it.next();
            acteursStringBuilder.append(acteur + "\n");
        }
        acteursTextArea.setText(acteursStringBuilder.toString());

        resumeTextArea = new JTextArea(film.getResume());
        resumeTextArea.setEditable(false);

        add(titreTextField);
        add(genreTextField);
        add(realisateurTextField);
        add(producteurTextField);
        add(acteursTextArea);
        add(resumeTextArea);
        add(panelQR);
        add(panelCD);
    }
}
