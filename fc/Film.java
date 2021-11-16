package fc;



import fc.Test.FilmDaoImp;

import java.util.ArrayList;
import java.util.Iterator;

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
    private ArrayList<String> genre;
    private static FilmDaoImp BD=new FilmDaoImp();

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
        genre=new ArrayList<>();
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
     * Fonction qui redefini la fonction tostring.<br>
     * Permet d'avoir un affichage plus comprehensible 
     * @see
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


    public static ArrayList<Film> rechercherFilm(String titre) {
        return titre != null ? BD.chercher(titre) : BD.chercher();
    }
    /** 
     * methode qui permet de recuperer le resume du film
     * @return String 
     */
    private String getResume() {
        return resume;
    }


    
    /** 
     * methode qui permet de recuperer les acteurs principaux du film
     * @return String
     */
    private String getActeursPrincipaux() {
        return acteursPrincipaux;
    }


    
    /** 
     * methode qui permet de recuperer le producteur du film
     * @return String
     */
    private String getProducteur() {
        return producteur;
    }


    public boolean verfieGenre(ArrayList<String> genre){
        Iterator<String> it=genre.iterator();
        Iterator<String> it2=this.genre.iterator();
        while(it.hasNext())
        {
            String tmp1=it.next();
            while(it2.hasNext()){
                if(tmp1.compareTo(it2.next())==0){
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * methode qui permet de recuperer le titre du film
     * @return String
     */
    public String getTitre() {
        return titre;
    }

    public void setGenre(String[] genre) {
        this.genre=new ArrayList<>();
        for (int i=0;i<genre.length;i++) {
            this.genre.add(genre[i]);
        }
    }
}