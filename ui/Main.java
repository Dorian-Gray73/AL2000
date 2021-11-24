
package ui;
import fc.CD;
import fc.FacadeNf;
import fc.Film;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Main extends JFrame{
    private JPanel panelPrincipal;
    private JPanel panelAff;
    private FacadeNf out;

    public Main(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setMinimumSize(new Dimension(400,400));
        panelPrincipal = new JPanel();
        
        //Init façade avec 2 CD pour Mourir peut attendre
        out = new FacadeNf(new HashMap<String, List<CD>>() {{
			put("mourir peut attendre", new ArrayList<CD>(){{
				add(new CD(Film.rechercherFilm(new HashMap<String, String>(){{
					put("titre", "mourir peut attendre");
				}}).get(0), false));
				add(new CD(Film.rechercherFilm(new HashMap<String, String>(){{
					put("titre", "mourir peut attendre");
				}}).get(0), false));
			}});
		}});;
        
        /*
        Film film = new Film("titre", "genre");
        film.setResume("resume");
        film.setProducteur("nomProducteur", "prenomProducteur");
        film.setRealisateur("nomRealisateur", "prenomRealisateur");
        film.ajouterActeur("acteur");
        */
    
        panelAff = new JPanel(); // Ici
        panelAff.setLayout(new BorderLayout());
        this.setContentPane(panelPrincipal);
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(new TopMenu(panelAff),BorderLayout.NORTH);
        panelPrincipal.add(panelAff,BorderLayout.CENTER);
        panelPrincipal.add(new BtmMenu(panelAff, out),BorderLayout.SOUTH);
        this.pack();
              
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main("AL2000").setVisible(true);
            }
        });
    }
}

