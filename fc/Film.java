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
     * @return String presentant tout les champs du film
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
     * Méthode pour rechercher un film
     * @param filtres qu'on applique à la recherche de film
     * @return ArrayList<Film> des films correspondant à la recherche.
     */
    public static ArrayList<Film> rechercherFilm(HashMap<String, String> filtres) {
        return BD.chercher(filtres);
    }

    /**
     * Methode qui permet de recuperer le titre du film
     * @return String le titre du film
     */
    public String getTitre() {
        return titre;
    }

	
    /** 
     * Methode qui permet de retourner le genre d un film
     * @return String le genre du film
     */
    public String getGenre() {
		return genre;
	}

    
    /** 
     * Methode qui permet de definir un gere d un film
     * @param genre qui met à jour le genre du film
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Methode qui permet de recuperer le resume du film
     * @return String le résumé du film
     */
    public String getResume() {
        return resume;
    }

    
    /** 
     * Methode qui permet de definir le resume d un film
     * @param resume pour mettre à jour le genre du film
     */
    public void setResume(String resume) {
        this.resume = resume;
    }

    /**
     * methode qui permet de recuperer le producteur du film
     * @return String correspondant au nom et prenom du realisateur
     */
    public String getRealisateur() {
        return nomRealisateur + " " + prenomRealisateur;
    }

    
    /** 
     * methode qui permet de définir le realisateur d un film
     * @param nomRealisateur nom du realisateur a ajouté
     * @param prenomRealisateur prenom du realisateur
     */
    public void setRealisateur(String nomRealisateur, String prenomRealisateur) {
        this.nomRealisateur = nomRealisateur;
        this.prenomRealisateur = prenomRealisateur;
    }

    /**
     * methode qui permet de recuperer le producteur du film
     * @return String correspondant au nom et prenom du producteur
     */
    public String getProducteur() {
        return nomProducteur + " " + prenomProducteur;
    }

    
    /** 
     * Methode qui permet de definir un producteur
     * @param nomProducteur nom du producteur
     * @param prenomProducteur prenom du producteur 
     */
    public void setProducteur(String nomProducteur, String prenomProducteur) {
        this.nomProducteur = nomProducteur;
        this.prenomProducteur = prenomProducteur;
    }

    /**
     * methode qui permet de recuperer les acteurs principaux du film
     * @return ArrayList<String> liste des acteurs d un film
     */
    public ArrayList<String> getActeurs() {
        return acteurs;
    }

    
    /** 
     * Methode qui permet de rajouter un acteur à un film
     * @param acteur chaine de caractere correspondant a l acteur a ajoute
     */
    public void ajouterActeur(String acteur) {
        acteurs.add(acteur);
    }

	
    /** 
     * Methode qui permet de rajouter un acteur à un film
     * @param nomActeur nom de l acteur
     * @param prenomActeur prenom de l acteur
     */
    public void ajouterActeur(String nomActeur, String prenomActeur) {
        acteurs.add(nomActeur + " " + prenomActeur);
    }

    
    /** 
     * Methode qui permet le filtrage par genre de film
     * @param restrictions liste des restrictions de l adherent
     * @return boolean true si le genre est le meme (cas l ou on ne doit pas proposer la location de ce film a cet adherent)
     */
    public boolean verifieGenre(ArrayList<String> restrictions) {
        return restrictions.contains(genre);
    }

    
    /** 
     * Methode qui permet de comparer deux film
     * @param o object a tester pour voir s il correspond a un film
     * @return boolean true si l objet est le meme false sinon
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