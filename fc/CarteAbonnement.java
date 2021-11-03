package fc;

public class CarteAbonnement {
    private double solde;
    private String[] restriction;
    private CarteAbonnement carteMere;
    private boolean bloque;
    private int nbLocation;
    
    public CarteAbonnement(CarteAbonnement mere) {
        this.solde = 10.0;
        this.restriction = null;
        this.bloque = false;
        this.nbLocation = 0;
        this.carteMere = mere;
    }

    public CarteAbonnement(){
        this(null);
    }

    /**
     * Methode qui permet de débiter une carte <br>
     * 
     * @param prix
     * @return boolean
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
            return ! getBlocage();
        }
    }

    /**
     * @return boolean
     */
    private boolean SoldeInsuffisant() {
        return solde < 0;
    }

    /**
     * @param somme
     * @return boolean
     */
    public boolean crediterCarte(double somme) {
        this.solde += somme;
        return true;
    }

    /**
     * @param restriction
     */
    public void setRestriction(String[] restriction) {
        this.restriction = restriction;
    }

    /**
     * @return restriction
     */
    public String[] getRestriction() {
        return restriction;
    }

    /**
     * @param boolean
     */
    public void setBlocage(Boolean bloque) {
        this.bloque = bloque;
    }

    /**
     * @return boolean
     */
    public boolean getBlocage() {
        return bloque;
    }

    /**
     * @return int
     */
    public int getnbLocation() {
        return nbLocation;
    }

    /**
     * @param nbLocation
     */
    public void setnbLocation(int nbLocation) {
        this.nbLocation = nbLocation;
    }

    /**
     * @return boolean
     */
    public Boolean estCarteMere() {
        return (this.carteMere == this);
    }

    @Override
    public String toString() {
        return "{" +
        " solde = '" + getSolde() + "'" +
        ", restriction = '" + getRestriction() + "'" +
        ", cartemère = '" + getCarteMere() + "'" +
        ", bloque = '" + isBloque() + "'" +
        ", nbLocation = '" + getNbLocation() + "'" +
        "}";
    }

    private CarteAbonnement getCarteMere() {
        return carteMere;
    }

    private int getNbLocation() {
        return nbLocation;
    }

    private boolean isBloque() {
        return bloque;
    }

    private double getSolde() {
        return solde;
    }
}
