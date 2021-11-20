package fc;
/**
 * Classe représentant un Support <br>
 * Un support sera défini par un film.<br>
 * @see Film classe extremement lier a support. Nous vous conseillons donc de ne pas hesite a consulter la classe Film
 * Cette classe dispose des methodes afficherInformations et calculDuree 
 */
public class Support {
    private Film film;
    /**
     * Constructeur de la classe Support
     * @param film designe le film qui est soit un QRCode soit un cd
     * 
     */
    public Support(Film film) {
        this.film = film;
    }

    /**
     * Fonction qui permet d'afficher les informations d'un film
     * @see Film afin d obtenir des informations sur la methode afficherInformations()
     */
    public void afficherInformations() {
        film.afficherInformations();
    }

     /**
     * methode qui permet de calculer la durée d'emprunt 
     * cette methode sera redefini pour QRCode car sa durée de location est 
     * fixe.
     * @return int représentant la durée en heure
     */

    public int calculerDuree() {
        return -1;
    } 


    
    /** 
     * Fonction qui redefini la fonction tostring.<br>
     * Permet d'avoir un affichage plus comprehensible 
     * @see Object pour voir la definition de toString()
     * @return String une représentation du support
     */
    @Override
    public String toString() {
        return "{" +
            " film = '" + getFilm() + "'" +
            "}";
    }

    
    /** 
     * Fonction qui retourne le film 
     * @return Film du support
     */
    public Film getFilm() {
        return film;
    }

    
    /** 
     * Fonction qui sera redefini dans la classe CD
     * @see CD ou la methode sera defini
     * @param dommage parametre qui représente l'état du support
     */
    public void setEndommage(boolean dommage){
        return;
    }

}
