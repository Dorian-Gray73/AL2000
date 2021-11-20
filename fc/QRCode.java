package fc;

/**
 * Classe représentant un QRCode.<br>
 * Un QRCode est représenté par un film 
 * @see Support classe mere de QRCode
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
     *  Methode herité de la classe support. Permet de calculer la durée d'activité d'un QR code.<br>  
     * @see Support pour voir la definition de calculerDuree
     * @return int en heure représentant jusqu'a quand est valable un QR code 
     */
    @Override
    public int calculerDuree() {
        return 12;
    }

    /** 
     * Fonction qui redefini la fonction tostring.<br>
     * Permet d'avoir un affichage plus comprehensible 
     * @see Object pour voir la definition de la methode toString()
     * @return String correspondant a une representation du QRCode sous forme de chaine
     */
    @Override
    public String toString() {
        return "{" +
        " QRcode = '" + super.toString() + "'" +
            "}";
    }

}
