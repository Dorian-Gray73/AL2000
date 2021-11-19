package fc;



import java.util.ArrayList;
import java.util.HashMap;

import fc.Dao.FilmDaoImp;

/**
 * La classe Film est une classe qui permet de representer les films 
 * dans notre programme.
 * Les films sont représentés par un titre,un producteur, une liste d acteurs principaux et d un resume
 */
public class Film {
    private String titre;
    private String genre;
    private String resume;
    private String nomRealisateur;
    private String prenomRealisateur;
    private String nomProducteur;
    private String prenomProducteur;
    private ArrayList<String> acteurs;
    private static FilmDaoImp BD = new FilmDaoImp();

    /**
     * Constructeur de la classe film
     * @param titre Le parametre t représente le titre du film
     * @param genre Le parametre genre represente le genre du film
     */
    public Film(String titre, String genre) {
        this.titre = titre;
        this.genre = genre;
        resume = null;
        nomRealisateur = null;
        prenomRealisateur = null;
        nomProducteur = null;
        prenomProducteur = null;
        acteurs = new ArrayList<String>();
    }


    /**
     * Fonction qui affiche les informations d'un film.
     */
    public void afficherInformations(){
        System.out.println("Le titre est " + titre + ".");
        System.out.println("Les acteurs principaux sont :");
        System.out.println(acteurs);
        System.out.println();
        System.out.println("Le résumé est : " + resume);
    }

    
    /** 
     * Fonction qui redefinit la fonction tostring.<br>
     * Permet d'avoir un affichage plus comprehensible
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{" +
                " titre = '" + titre + "'" +
                " genre = '" + genre + "'" +
                ", producteur = '" + nomProducteur + " " + prenomProducteur + "'" +
                ", réalisateur = '" + nomRealisateur + " " + prenomRealisateur + "'" +
                ", acteursPrincipaux = '");

        for (String acteur : acteurs) {
              str.append(acteur + " ");
        }

        str.deleteCharAt(str.length() - 1);
        str.append("'" +
                ", resume = '" + resume + "'" +
                "}");

        return str.toString();
    }


    public static ArrayList<Film> rechercherFilm(String titre) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("titre", titre);
        return BD.chercher(map);
    }

    /**
     * methode qui permet de recuperer le titre du film
     * @return String
     */
    public String getTitre() {
        return titre;
    }

	public String getGenre() {
		return genre;
	}

    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * methode qui permet de recuperer le resume du film
     * @return String
     */
    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    /**
     * methode qui permet de recuperer le producteur du film
     * @return String
     */
    public String getRealisateur() {
        return nomRealisateur + " " + prenomRealisateur;
    }

    public void setRealisateur(String nomRealisateur, String prenomRealisateur) {
        this.nomRealisateur = nomRealisateur;
        this.prenomRealisateur = prenomRealisateur;
    }

    /**
     * methode qui permet de recuperer le producteur du film
     * @return String
     */
    public String getProducteur() {
        return nomProducteur + " " + prenomProducteur;
    }

    public void setProducteur(String nomProducteur, String prenomProducteur) {
        this.nomProducteur = nomProducteur;
        this.prenomProducteur = prenomProducteur;
    }

    /**
     * methode qui permet de recuperer les acteurs principaux du film
     * @return String
     */
    public ArrayList<String> getActeurs() {
        return acteurs;
    }

    public void ajouterActeur(String acteur) {
        acteurs.add(acteur);
    }

	public void ajouterActeur(String nomActeur, String prenomActeur) {
        acteurs.add(nomActeur + " " + prenomActeur);
    }

    public boolean verifieGenre(ArrayList<String> restrictions) {
        return restrictions.contains(genre);
    }

    @Override
    public boolean equals(Object o) {
        Film film = (Film) o;
        return titre.equals(film.titre) && genre.equals(film.genre);
    }
}