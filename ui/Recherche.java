package ui;

import fc.CD;
import fc.FacadeNf;
import fc.Film;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import  java.util.List;

public class Recherche extends JFrame {
    private JPanel panel1;
    private JPanel recherche;
    private JPanel affichageResultat;
    private JTextField rechercheTxt;
    private JButton ButtonRecherche;
    private JButton retourButton;
    private JTextPane textPane;
    private FacadeNf out = new FacadeNf();
    String cherche;
    public Recherche(String title) {
        super(title);
        //this.recherche=recherche;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        ButtonRecherche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filmRech = rechercheTxt.getText();
                HashMap<Film, List<CD>> resultat = out.rechercherFilm(filmRech);
                for (Map.Entry<Film, java.util.List<CD>> map : resultat.entrySet()) {
                    Film filmRes = map.getKey();
                    String res = filmRes.toString();
                    java.util.List<CD> biblio = map.getValue();
                    if (biblio == null) {
                        textPane.setText(res + " \n Il n'y a pas de dvd disponible pour ce film");
                    } else {
                        res = res + "il y a " + map.getValue() + " cd disponible";
                        textPane.setText("\n" + res);
                    }
                }
            }
        });
    }
    public void resultat(String s){

    }
    public static void main(String[] args){
        JFrame recherche=new Recherche("recherche");
        recherche.setVisible(true);
    }


}
