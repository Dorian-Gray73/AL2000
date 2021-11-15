package fc;
/**
 * Classe représentant un Support <br>
 * Un support sera défini par un film.<br>
 * @see Film
 * Cette classe dispose des methodes afficherInformations et calculDuree 
 */
public class Support {
    private Film film;
    /**
     * Constructeur de la classe Support
     * @param film designe le film qui est soit un QRCode soit un dvd
     * 
     */

    public Support(Film film) {
        this.film = film;
    }

    /**
     * Fonction qui permet d'afficher les informations d'un film
     * @see Film.afficherInformations
     * @return void
     */
    public void afficherInformations() {
        film.afficherInformations();
    }

    /**
     * methode qui permet de calculer la durée d'emprunt 
     * cette methode sera redefini pour QRCode car sa durée de location est 
     * fixe.
     * @return int représentant la durée en jour
     */
    public double calculerDuree() {
        //TODO En attente de la gestion des date dans Location.
        return -1;
    }

    
    /** 
     * Fonction qui redefini la fonction tostring.<br>
     * Permet d'avoir un affichage plus comprehensible 
     * @see Object.toString
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
            " film = '" + getFilm() + "'" +
            "}";
    }

    
    /** 
     * Fonction qui retourne le film 
     * @return Film 
     */
    public Film getFilm() {
        return film;
    }

    
    /** 
     * Fonction qui sera redefini dans la classe CD
     * @see CD.setEndommage
     * @param dommage parametre qui représente l'état du support
     */
    public void setEndommage(boolean dommage){
        return;
    }

}
