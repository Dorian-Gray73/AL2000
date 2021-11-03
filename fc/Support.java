package fc;
/**
 * Classe représentant un Support <br>
 * Un support sera défini par un film.<br>
 * Cette classe dispose des methodes afficherInformations et calculDuree 
 */
public class Support {
    private Film film;
    /**
     * Constructeur de la classe Support
     * @param Film designe le film qui est soit un QRCode soit un dvd
     * 
     */

    Support(Film film) {
        this.film = film;
    }

    /**
     * Fonction qui permet d'afficher les informations d'un film
     * @see Film.afficherInformations
     * @return void
     */
    void afficherInformations() {
        film.afficherInformations();
    }

    /**
     * methode qui permet de calculer la durée d'emprunt 
     * cette methode sera redefini pour QRCode car sa durée de location est 
     * fixe.
     * @return int
     */
    public int calculDuree() {
        //TODO
        return -1;
    }

}
