package fc;

/**
 * Classe représentant un cd.<br>
 * Un cd sera identifié par son état (endommagé ou non) et par le film qu'il
 * represente<br>
 * 
 * @see Support . Un cd dispose des méthodes setEndommage et getEndommage
 */
public class CD extends Support {
    private boolean endommage;

    /**
     * Constructeur de la classe CD
     * 
     * @param film      parametre qui nous dit quel film est représenté en cd
     * @param endommage parametre qui représente l'etat d'un cd
     */

    public CD(Film film, boolean endommage) {
        super(film);
        this.endommage = endommage;
    }

    /**
     * Methode qui permet de changer l'etat du disque.
     * 
     * @param endommage nouvel etat du cd
     */
    public void setEndommage(boolean endommage) {
        this.endommage = endommage;
    }

    /**
     * Methode qui retourne l'etat du cd
     * 
     * @return boolean qui sera true si le cd est endommagé
     */
    public boolean getEndommage() {
        return endommage;
    }

    /** 
     * Fonction qui redefini la fonction tostring.<br>
     * Permet d'avoir un affichage plus comprehensible 
     * @see Object pour voir la definition de toString()
     * @return String representant notre objet
     */
    @Override
    public String toString() {
        return "{" + " endommage = '" + getEndommage() + "'" + "}";
    }
}
