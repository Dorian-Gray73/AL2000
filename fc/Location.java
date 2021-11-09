package fc;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import fc.Test.LocationDao;
import fc.Test.LocationDaoImp;

public class Location {
	private int idLocation;
    private LocalDateTime debut;
    private LocalDateTime fin;
    private double tarif;
    private Client client;
    private Support support;

    


	public Location(LocalDateTime debut, double tarif, Client client, Support support) {
		super();
		this.debut = debut;
		this.fin = null;
		this.tarif = tarif;
		this.client = client;
		this.support = support;
	}

	/**
     * @param tarif
     */
    public void setTarif(double tarif) {
        this.tarif = tarif;
    }
    
    public void setFin(LocalDateTime fin) {
    	this.fin = fin;
    }
    
    public void setIdLocation(int idLocation) {
		this.idLocation = idLocation;
	}
    
    /** 
     * @return double
     */
    public double genererFacture() {
        return 0;
    }

    /**
     * @return le prix de la location.
     * @throws LocationException Dans le cas ou l'on appel cette méthode alors que la location n'est pas terminée.
     */

    public double CalculerPrix() {
        if (fin != null) {
        	long duree = (debut.until(fin, ChronoUnit.DAYS));
        	if(duree <= 1) 
        		return tarif;
        	
            return tarif * duree;
        }
        return -1;
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
    public Support getSupport() {
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
    private LocalDateTime getFin() {
        return fin;
    }

    
    /** 
     * @return DaateTime
     */
    private LocalDateTime getDebut() {
        return debut;
    }

	public int sauvegarder() {
		LocationDao locationDao = new LocationDaoImp();
		return locationDao.ajouterLocation(this);
	}
	
	public static Location trouverLocation(Client client, CD film) {
		LocationDao locationDao = new LocationDaoImp();
		return locationDao.trouverLocation(client, film);
	}

	public void miseAJour() {
		LocationDao locationDao = new LocationDaoImp();
		locationDao.miseAJourLocation(idLocation, fin);
	}
	
	public static List<Location> consulterHistorique(Adherent adherent) {
		LocationDao locationDao = new LocationDaoImp();
		return locationDao.chercherLocations(adherent);
	}

}
