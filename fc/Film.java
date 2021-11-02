package fc;
/**
 * La classe Film est une classe qui permet de representer les films 
 * dans notre programme.
 * Les films sont représentés par un titre,un producteur, une liste d acteurs principaux et d un resume
 * @author Groupe1-A
 * @version 1.0
 */
public class Film{
    String titre;
    String producteur;
    String[] acteursPrincipaux;
    String resume;

    /**
     * Constructeur de la classe film
     * @param t
     *          Le parametre t représente le titre du film
     * @param p
     *          Le parametre p represente le producteur du film
     * @param r
     *          Le parametre r represente le resume du film
     * @param a
     *          Le parametre a représente un tableau de String contenant les acteurs principaux
     * @return void
     */
    Film(String t,String p, String r,String[]a){
        titre = t;
        producteur = p;
        acteursPrincipaux = a;
        resume = r;
    }


    /**
     * fonction qui affiche les informations d'un film.
     * @param null
     * @return void
     */
    void afficherInformations(){
        System.out.println("le titre est "+titre+".");
        System.out.println("Le producteur est "+producteur+".");
        System.out.println("Les acteurs principaux sont :");
        int i =0;
        while (i<acteursPrincipaux.length) {
            System.out.print(acteursPrincipaux[i]+" ");
        }
        System.out.println();
        System.out.println("le résumé est : "+resume);
    }

}