package fc;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import fc.Test.LocationDao;
import fc.Test.LocationDaoImp;

public class Location {
    //TODO JAVADOC
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
    
    
    /** 
     * @param fin
     */
    public void setFin(LocalDateTime fin) {
    	this.fin = fin;
    }
    
    
    /** 
     * @param idLocation
     */
    public void setIdLocation(int idLocation) {
		this.idLocation = idLocation;
	}
    
    /** 
     * @return String
     */
    public String genererFacture() {
        //TODO A FAIRE retourne une chaine de caractère correspondant à la facture
        return "";
    }

    /**
     * @return le prix de la location.
     * @throws LocationException Dans le cas ou l'on appel cette méthode alors que la location n'est pas terminée.
     */
    public double CalculerPrix() {
        //TODO attention -1 
        if (fin != null) {
        	long duree = (int) (debut.until(fin, ChronoUnit.DAYS));
            return tarif * (duree + 1);
        }
        return -1;
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
            " debut = '" + getDebut() + "'" +
            ", fin = '" + getFin() + "'" +
            ", tarif = '" + getTarif() + "'" +
            ", support = '" + getSupport() + "'" +
            "}";
    }

    
    /** 
     * methode qui permet de retourner le support 
     * @return Support
     */
    public Support getSupport() {
        return support;
    }

    
    /** 
     * methode qui permet de retourner le tarif de la location
     * @return double représentant le montant de la location
     */
    private double getTarif() {
        return tarif;
    }

    
    /** 
     * methode qui permet de retourner la date de fin de la location
     * @return DateTime
     */
    private LocalDateTime getFin() {
        return fin;
    }

    
    /** 
     * methode qui permet de retourner la date de début de la location
     * @return DaateTime
     */
    private LocalDateTime getDebut() {
        return debut;
    }

	
    /** 
     * Methode qui permet de sauvegarder les locations de l'adhérents
     * @see LocationDao.ajouterLocation
     * @return int
     */
    public int sauvegarder() {
		LocationDao locationDao = new LocationDaoImp();
		return locationDao.ajouterLocation(this);
	}
	
	
    /** 
     * @see LocationDao.trouverLocation
     * @param client
     * @param film
     * @return Location
     */
    public static Location trouverLocation(Client client, CD film) {
		LocationDao locationDao = new LocationDaoImp();
		return locationDao.trouverLocation(client, film);
	}

    /**
     * 
     *@see LocationDao.miseAJourLocation
     * @return void
	*/ 
     public void miseAJour() {
		LocationDao locationDao = new LocationDaoImp();
		locationDao.miseAJourLocation(idLocation, fin);
	}
	
	
    /** 
     * @param adherent
     * @return List<Location>
     */
    public static List<Location> consulterHistorique(Adherent adherent) {
		LocationDao locationDao = new LocationDaoImp();
		return locationDao.chercherLocations(adherent);
	}

}
