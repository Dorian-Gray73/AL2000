package fc;

public class Support {
/**
 * 
 * 
 * 
 */
    private Film film;

    Support(Film film) {
        this.film = film;
    }

    void afficherInformations() {
        film.afficherInformations();
    }

    /**
     * @return int
     */
    public int calculDuree() {
        return -1;
    }

}
