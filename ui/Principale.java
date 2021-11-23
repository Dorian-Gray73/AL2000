package ui;

import fc.CD;
import fc.FacadeNf;
import fc.Film;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class Principale extends JFrame{
    private JPanel panel1;
    private JButton créationDeCompteButton;
    private JButton connexionButton;
    private JComboBox comboBox1;
    private JButton rechercher;
    private JTextField rechercheTxt;
    private JButton rendreButton;
    private JPanel panel2;
    private JPanel panel3;
    private FacadeNf out = new FacadeNf();
    Principale(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
        rechercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String filmRech=rechercheTxt.getText();
                HashMap<Film, List<CD>> resultat = out.rechercherFilm(filmRech);
                recherche r=new recherche("recherche");
                r.setVisible(true);
            }
        });
        créationDeCompteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        connexionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args){
        JFrame frame=new Principale("AL2000");
        frame.setVisible(true);
    }
}
