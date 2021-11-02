package fc;

import java.util.Date;
/**
    Classe représentant une carte bancaire.<br>
    Une carte bancaire sera notamment identifié par son numéro de carte, son cryptogramme et enfin sa date d'expiration.<br>
    Elle dispose de la méthode débiterCarte.<br>
*/
public class CarteBancaire {
    String noCB;
    String cryptogramme;
    Date dateExpiration;

    /**
        Constructeur de la classe CarteBancaire.
        @param noCb numéro de la carte bancaire
        @param cryptogramme numéro du cryptogramme de la carte bancaire
        @param dateExpiration date d'expiration de la carte bancaire
    */
    CarteBancaire(String noCb,String cryptogramme, Date dateExpiration){
        this.noCB = noCb;
        this.cryptogramme = cryptogramme;
        this.dateExpiration = dateExpiration;
    }

    /** 
     * Méthode qui changera le solde d'une carte de crédit.
     * @param solde solde qui sera débiter de la carte bancaire.
     */

    void debiterCarte(float solde){

    }
    
}
