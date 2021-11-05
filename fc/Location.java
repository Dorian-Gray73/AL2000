package fc;

// import org.joda.time.DateTime;

public class Location {
    private DateTime debut;
    private DateTime fin;
    private double tarif;
    
    Support support;

    /**
     * @param tarif
     */
    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    
    /** 
     * @return double
     */
    public double genererFacture() {
        return 0;
    }

    /**
     * @return double
     */

    public double CalculerPrix() {
        if (fin != null) {
            return tarif * (fin.getDifferenceAsLong(debut));
        } else {
            support.calculDuree();
            return 0;
        }
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
            " debut = '" + getDebut() + "'" +
            ", fin = '" + getFin() + "'" +
            ", tarif = '" + getTarif() + "'" +
            ", support = '" + getSupport() + "'" +
            "}";
    }

    
    /** 
     * @return Support
     */
    Support getSupport() {
        return support;
    }

    
    /** 
     * @return double
     */
    private double getTarif() {
        return tarif;
    }

    
    /** 
     * @return DateTime
     */
    private DateTime getFin() {
        return fin;
    }

    
    /** 
     * @return DaateTime
     */
    private DateTime getDebut() {
        return debut;
    }

}
