
package ui;
import fc.CD;
import fc.FacadeNf;
import fc.Film;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Main extends JFrame{
    private JPanel panelPrincipal;
    private JPanel panelAff;
    private FacadeNf out;
    //private Toolkit outil = getToolkit();
    public Main(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // super.setMinimumSize(outil.getScreenSize());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
    
        //        panelAff.setLayout(new BorderLayout());
        this.setContentPane(panelPrincipal);
        panelPrincipal.setLayout(new BorderLayout());
        panelAff = new Principale(panelPrincipal,out);
        panelPrincipal.add(new TopMenu(panelAff),BorderLayout.NORTH);
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

