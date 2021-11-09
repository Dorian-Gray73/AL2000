package fc;

import java.util.Date;

/**
 * Classe représentant une carte bancaire.<br>
 * Une carte bancaire sera notamment identifié par son numéro de carte, son
 * cryptogramme et enfin sa date d'expiration.<br>
 * Elle dispose de la méthode débiterCarte.<br>
 */
public class CarteBancaire {
    String noCB;
    String cryptogramme;
    Date dateExpiration;

    /**
     * Constructeur de la classe CarteBancaire.
     * 
     * @param noCb           numéro de la carte bancaire
     * @param cryptogramme   numéro du cryptogramme de la carte bancaire
     * @param dateExpiration date d'expiration de la carte bancaire
     */
    public CarteBancaire(String noCb, String cryptogramme, Date dateExpiration) {
        this.noCB = noCb;
        this.cryptogramme = cryptogramme;
        this.dateExpiration = dateExpiration;
    }

    /**
     * Méthode qui changera le solde d'une carte de crédit.
     * 
     * @param solde Montant qui sera débiter de la carte bancaire.
     * @return void
     */

    public boolean debiterCarte(double solde) {
        //TODO coté banque, donc persistence de donnée ou validation automatique.
    	return true;
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
            " noCB = '" + getNoCB() + "'" +
            ", cryptogramme = '" + getCryptogramme() + "'" +
            ", dateExpiration = '" + getDateExpiration() + "'" +
            "}";
    }

    
    /** 
     * Fonction qui retourne la date d'expiration de la carte bancaire/
     * @return Date
     */
    private Date getDateExpiration() {
        return dateExpiration;
    }

    
    /**
     * Fonction qui returne le cryptogramme visuel de la carte bancaire 
     * @return String qui represente le cryptogramme visuel de la carte bancaire
     */
    private String getCryptogramme() {
        return cryptogramme;
    }

    
    /** 
     * Fonction qui returne le numero de la carte bancaire
     * @return String qui représente le numéro de carte bancaire
     */
    private String getNoCB() {
        return noCB;
    }

}
