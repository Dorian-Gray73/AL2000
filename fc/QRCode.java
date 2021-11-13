package fc;

/**
 * Classe représentant un QRCode.<br>
 * Un QRCode est représenté par un film 
 * @see Support 
 * Un QRCode dispose de la méthodes calculDuree
 *
 * 
 */
public class QRCode extends Support {
    /**
     * Constructeur de la classe QRCode
     * @param film Le parametre film
     */

    public QRCode(Film film) {
        super(film);
    }

    /**
     * @Override Methode herité de la classe support. Permet de calculer la durée
     *           d'activité d'un QR code.<br>
     * @see Support.calculDuree
     * @return int représentant jusqu'a quand est valable un QR code
     */

    public int calculerDuree() {
        return 12;
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
        " QRcode = '" + super.toString() + "'" +
            "}";
    }

}
