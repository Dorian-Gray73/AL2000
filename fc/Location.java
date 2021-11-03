package fc;

import org.joda.time.DateTime;

public class Location {
    private DateTime debut;
    private DateTime fin;
    private double tarif;
    private Support support;

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
}
