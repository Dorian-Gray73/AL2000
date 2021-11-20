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
                " titre = '" + titre + "'" + "\n" +
                " genre = '" + genre + "'" + "\n" +
                ", producteur = '" + nomProducteur + " " + prenomProducteur + "'" + "\n" +
                ", réalisateur = '" + nomRealisateur + " " + prenomRealisateur + "'" + "\n" +
                ", acteursPrincipaux = '");

        for (String acteur : acteurs) {
              str.append(acteur + " ");
        }
        str.deleteCharAt(str.length() - 1);
        
        str.append("'" + "\n"+
                ", resume = '" + resume + "'" +
                "}");

        return str.toString();
    }


    
    /** 
     * @param filtres
     * @return ArrayList<Film>
     */
    public static ArrayList<Film> rechercherFilm(HashMap<String, String> filtres) {
        return BD.chercher(filtres);
    }

    /**
     * methode qui permet de recuperer le titre du film
     * @return String
     */
    public String getTitre() {
        return titre;
    }

	
    /** 
     * methode qui permet de retourner le genre d un film
     * @return String
     */
    public String getGenre() {
		return genre;
	}

    
    /** 
     * methode qui permet de definir un gere d un film
     * @param genre
     */
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

    
    /** 
     * Methode qui permet de definir le resume d un film
     * @param resume
     */
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

    
    /** 
     * methode qui permet de définir le realisateur d un film
     * @param nomRealisateur
     * @param prenomRealisateur
     */
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

    
    /** 
     * Methode qui permet de definir un producteur
     * @param nomProducteur
     * @param prenomProducteur
     */
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

    
    /** 
     * Methode qui permet de rajouter un acteur à un film
     * @param acteur
     */
    public void ajouterActeur(String acteur) {
        acteurs.add(acteur);
    }

	
    /** 
     * Methode qui permet de rajouter un acteur à un film
     * @param nomActeur
     * @param prenomActeur
     */
    public void ajouterActeur(String nomActeur, String prenomActeur) {
        acteurs.add(nomActeur + " " + prenomActeur);
    }

    
    /** 
     * Methode qui permet le filtrage par genre de film
     * @param restrictions
     * @return boolean
     */
    public boolean verifieGenre(ArrayList<String> restrictions) {
        return restrictions.contains(genre);
    }

    
    /** 
     * Methode qui permet de comparer deux film
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        Film film = (Film) o;
        return titre.equals(film.titre) && genre.equals(film.genre);
    }
}