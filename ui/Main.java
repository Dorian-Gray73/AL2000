package ui;
import fc.CD;
import fc.FacadeNf;
import fc.Film;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;


public class Main extends JFrame{
    private JPanel panelPrincipal;
    private JButton creationDeCompteButton;
    private JComboBox<String> comboBox1;
    private JButton rechercher;
    private JTextField rechercheTxt;  
    private JPanel panelMenu;
    private JPanel panelAff;
    private FacadeNf out;

    public Main(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setMinimumSize(new Dimension(400,400));
        panelPrincipal = new JPanel();
        creationDeCompteButton = new JButton("Connexion");
        comboBox1 = new JComboBox<>();
        this.comboBox1.addItem("titre");
        this.comboBox1.addItem("genre");
        this.comboBox1.addItem("nomActeur");
        this.comboBox1.addItem("prenomActeur");
        rechercher = new JButton();
        rechercheTxt = new JTextField();
        panelMenu = new JPanel();
        panelAff = new JPanel();
        out = new FacadeNf();
        this.setContentPane(panelPrincipal);
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(panelMenu,BorderLayout.NORTH);
        panelPrincipal.add(panelAff,BorderLayout.CENTER);
        panelPrincipal.add(new BtmMenu(panelAff),BorderLayout.SOUTH);
        panelMenu.setLayout(new BorderLayout(5,5));
        panelMenu.add(rechercheTxt,BorderLayout.CENTER);
        panelMenu.add(rechercher,BorderLayout.WEST);
        panelMenu.add(comboBox1,BorderLayout.EAST);
        

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

        creationDeCompteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main("AL2000").setVisible(true);
            }
        });
    }
}

