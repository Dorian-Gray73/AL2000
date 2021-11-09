package fc;

import fc.Test.FilmDao;
import fc.Test.FilmDaoImp;

/**
 * La classe Film est une classe qui permet de representer les films 
 * dans notre programme.
 * Les films sont représentés par un titre,un producteur, une liste d acteurs principaux et d un resume
 */
public class Film{
    private String titre;
    private String producteur;
    private String acteursPrincipaux;
    private String resume;
    // private FilmDao DB=new FilmDaoImp();

    /**
     * Constructeur de la classe film
     * @param titre Le parametre t représente le titre du film
     *          
     * @param producteur Le parametre p represente le producteur du film

     * @param resume Le parametre r represente le resume du film

     * @param acteursPrincipaux Le parametre a représente un tableau de String contenant les acteurs principaux
     */
    public Film(String titre, String producteur, String resume, String acteursPrincipaux){
        this.titre = titre;
        this.producteur = producteur;
        this.acteursPrincipaux = acteursPrincipaux;
        this.resume = resume;
    }


    /**
     * Fonction qui affiche les informations d'un film.
     */
    public void afficherInformations(){
        System.out.println("le titre est " + titre + ".");
        System.out.println("Le producteur est " + producteur + ".");
        System.out.println("Les acteurs principaux sont :");
        System.out.println(acteursPrincipaux);       
        System.out.println();
        System.out.println("le résumé est : " + resume);
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
            " titre = '" + getTitre() + "'" +
            ", producteur = '" + getProducteur() + "'" +
            ", acteursPrincipaux = '" + getActeursPrincipaux() + "'" +
            ", resume = '" + getResume() + "'" +
            "}";
    }


    
    /** 
     * @return String
     */
    private String getResume() {
        return resume;
    }


    
    /** 
     * @return String
     */
    private String getActeursPrincipaux() {
        return acteursPrincipaux;
    }


    
    /** 
     * @return String
     */
    private String getProducteur() {
        return producteur;
    }


    
    /** 
     * @return String
     */
    public String getTitre() {
        return titre;
    }

}