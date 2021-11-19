package fc;

import java.time.LocalDate;

/**
 * Classe représentant une carte bancaire.<br>
 * Une carte bancaire sera notamment identifié par son numéro de carte, son
 * cryptogramme et enfin sa date d'expiration.<br>
 * Elle dispose de la méthode débiterCarte.<br>
 */
public class CarteBancaire {
    String noCB;
    String cryptogramme;
    LocalDate dateExpiration;

    /**
     * Constructeur de la classe CarteBancaire.
     * 
     * @param noCb           numéro de la carte bancaire
     * @param cryptogramme   numéro du cryptogramme de la carte bancaire
     * @param dateExpiration date d'expiration de la carte bancaire
     */
    public CarteBancaire(String noCb, String cryptogramme, LocalDate dateExpiration) {
        this.noCB = noCb;
        this.cryptogramme = cryptogramme;
        this.dateExpiration = dateExpiration;
    }

    /**
     * Méthode qui changera le solde d'une carte de crédit.
     * Comme cette méthode est du coté de la banque alors nous supposons que ce sera valider a chaque fois.
     * @param solde Montant qui sera débiter de la carte bancaire.
     * @return void
     */
    public boolean debiterCarte(double solde) {  
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
     * Methode qui permet de comparer deux cartes bancaire
     * @param carte
     * @return boolean
     */
    public boolean equals(CarteBancaire carte){
       return this.noCB == carte.cryptogramme && carte.cryptogramme == this.cryptogramme;
    }    
    /** 
     * Fonction qui retourne la date d'expiration de la carte bancaire/
     * @return Date
     */
    private LocalDate getDateExpiration() {
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
