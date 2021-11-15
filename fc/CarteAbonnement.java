package fc;

/**
 * Classe représentant une carte d'abonnement.<br>
 * Une carte d'abonnement est identifiée par un solde, un tableau de chaine presentant 
 * les restrictions présente sur la carte, la carte mère de la carte, 
 * un boolean qui indique si la carte est bloquée et un nombre de location
 * 
 */

public class CarteAbonnement {
    private double solde;
    private String[] restriction;
    private CarteAbonnement carteMere;
    private boolean bloque;
    private int nbLocation;
    
    /***
     * Constructeur de la carte d'abonnement<br>
     * Ce constructeur sera appelé dans le cas où la carte est une carte fille 
     * @param mere carte d'abonnement mere
     */
    public CarteAbonnement(CarteAbonnement mere) {
        this.solde = 10.0;
        this.restriction = null;
        this.bloque = false;
        this.nbLocation = 0;
        this.carteMere = mere;
    }
    /**
     * Constructeur de la carte d'abonnement<br>
     * Ce constructeur sera appelé s'il n'a pas de carte mère.<br>
     * La carte nouvellement créée sera donc sa propre carte mère.
     */
    public CarteAbonnement(){
        this(null);
    }

    /**
     * Methode qui permet de débiter une carte <br>
     * Cette méthode tient compte de si le client a deja loué plus de 20 films. <br>
     * S'il a loué plus de 20 films alors la location lui est offert
     * @param prix désigle le tarif de la location
     * @return boolean true si la location c est bien passé <br>
     * et false si la carte c'est bloqué
     */
    public boolean debiterCarte(double prix) {
        int nbLocation = getnbLocation();
        if (nbLocation == 20) {
            setnbLocation(0);
            return true;
        } else {
            this.solde -= prix;
            if (SoldeInsuffisant()) {
                setBlocage(true);
            }
            setnbLocation(nbLocation + 1);
            return !getBlocage();
        }
    }

    /**
     * fonction qui retourne si le solde sur une carte est positif
     * @return boolean qui représente si le solde est suffisant 
     */
    private boolean SoldeInsuffisant() {
        return solde < 0;
    }

    /**
     * methode qui permet de rajouter une somme au solde de la carte
     * @param somme montant qui est ajouter au solde de la carte
     * @return boolean vrai si la transaction ce réalise correctement.
     */
    public boolean crediterCarte(double somme) {
        this.solde += somme;
        if (!SoldeInsuffisant()) {
            setBlocage(false);
        }
        return true;
    }

    /**
     * fonction qui permet de définir les restrictions sur les cartes enfants ou 
     * sur soit meme s'il n'y a pas de carte enfant<br> 
     * @param restriction tableau de String contenant les restrictions
     */
    public void  setRestriction(String[] restriction) {
        this.restriction = restriction;
    }

    /**
     * Retourne les restrictions appliqué sur la carte 
     * @return String[]
     */
    public String[] getRestriction() {
        return restriction;
    }

    /**
     * Fonction qui permet de bloquer / debloquer une carte. 
     * Cela s'effectue en changeant la valeur du boolean bloque
     * @param boolean nouvelle etat de la carte
     */
    public void setBlocage(Boolean bloque) {
        this.bloque = bloque;
    }

    /**
     * Fonction retournant l'etat de la carte
     * @return boolean true si la carte est bloque
     */
    public boolean getBlocage() {
        return bloque;
    }

    /**
     * Fonction donnant le nombre de location en cours
     * @return int representant le nombre de location en cours
     */
    public int getnbLocation() {
        return nbLocation;
    }

    /**
     * Fonction qui définit le nombre de location
     * @param nbLocation nouveau nombre de location
     * @return void
     */
    public void setnbLocation(int nbLocation) {
        this.nbLocation = nbLocation;
    }

    /**
     * Fonction qui indique si une carte est une carte mère ou non
     * @return boolean qui est true si la carte est une carte mère
     */
    public Boolean estCarteMere() {
        return (this.carteMere == this);
    }

    /** 
     * Fonction qui retourne le solde de la carte
     * @return double solde de la carte
     */
    private double getSolde() {
        return solde;
    }
    
    /** 
     * Fonction qui retourne la carte mère de la carte interrogée
     * @return CarteAbonnement la carte mère de la carte interrogée
     */
    private CarteAbonnement getCarteMere() {
        return carteMere;
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
        " solde = '" + getSolde() + "'" +
        ", restriction = '" + getRestriction() + "'" +
        ", cartemère = '" + getCarteMere() + "'" +
        ", bloque = '" + getBlocage() + "'" +
        ", nbLocation = '" + getnbLocation() + "'" +
        "}";
    }
    
}
