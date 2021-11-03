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
    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public void genererFacture() {

    }

    /**
     * @return Float
     */

    public Float CalculerPrix() {
        if (fin != null) {
            return tarif * (fin.getDifferenceAsLong(debut));
        } else {
            support.calculDuree();
            return (float) 0;
        }
    }

    @Override
    public String toString() {
        return "{" +
            " debut = '" + getDebut() + "'" +
            ", fin = '" + getFin() + "'" +
            ", tarif = '" + getTarif() + "'" +
            ", support = '" + getSupport() + "'" +
            "}";
    }

    private Support getSupport() {
        return support;
    }

    private double getTarif() {
        return tarif;
    }

    private DateTime getFin() {
        return fin;
    }

    private DaateTime getDebut() {
        return debut;
    }

}
