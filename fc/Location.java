package fc;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import fc.Dao.LocationDao;
import fc.Dao.LocationDaoImp;
/**
 * Classe representant une location <br>
 * une location est represente par un numero de location, une date de debut, une date de fin, un tarif, 
 * le client correspondant, le support de la location, et le dao permettant d effecteuer la connexion a la base de donnee pour emprunter.<br>
 */
public class Location {
	private int idLocation;
    private LocalDateTime debut;
    private LocalDateTime fin;
    private double tarif;
    private Client client;
    private Support support;
    private static LocationDao locationDao = LocationDaoImp.getInstance();
    

    /**
     * Constructeur de la methode Location
     * @param debut de la location
     * @param tarif de la location
     * @param client de la location
     * @param support de la location
     */
	public Location(LocalDateTime debut, double tarif, Client client, Support support) {
		super();
		this.debut = debut;
		this.fin = null;
		this.tarif = tarif;
		this.client = client;
		this.support = support;
	}
    

    
    /** 
     * Methode permettant de retourner l identifiant de la location
     * @return int correspondant a l identifiant de la location
     */
    public int getIdLocation(){
        return idLocation;
    }

	/**
     * methode qui permet de définir le tarif
     * @param tarif défini le tarif de la location
     */
    public void setTarif(double tarif) {
        this.tarif = tarif;
    }
    
    
    /** 
     * methode qui permet de définpublicir la date de fin de la location
     * @param fin défini la fin de la location
     */
    public void setFin(LocalDateTime fin) {
    	this.fin = fin;
    }
    
    
    /** 
     * methode qui permet de définir l'id de location
     * @param idLocation nouvelle identifiant de location
     */
    public void setIdLocation(int idLocation) {
		this.idLocation = idLocation;
	}
    
    /** 
     * methode qui permet de générer un fichier txt contenant la facture dans votre repertoire
     */
     public void genererFacture() {
        PrintWriter writer;
        try {
            writer = new PrintWriter("facture.txt", "UTF-8");
            writer.println("Facture générer pour : "+client.toString());
            writer.println("Il s'agit d'une location d'un "+ getSupport());
            writer.println("Cette location s'étend du " + getDebut() + "au " + getFin());
            writer.println("Le montant à payer est de " + getTarif());
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * methode qui va calculer le prix de la location. La methode ne retournera jamais -1 dans des conditions normales.<br>
     * Le -1 indique que le calcul du prix c est mal dérouler.
     * @return le prix de la location.
     */
    public double calculerPrix() { 
        if (fin != null) {
        	long duree = (int) (debut.until(fin, ChronoUnit.DAYS));
            return tarif * (duree + 1);
        }
        return -1;
    }

    
    /** 
     * Fonction qui redefini la fonction tostring.<br>
     * Permet d'avoir un affichage plus comprehensible 
     * @see Object pour voir la definition de la methode toString()
     * @return String correspondant a une representation de notre objet sous forme de chaine
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
     * @return Support de la location
     */
    public Support getSupport() {
        return support;
    }

    
    /** 
     * methode qui retourne le client ayant effectué la location
     * @return Client qui a effectue la location
     */
    public Client getClient() {
		return client;
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
     * @return DateTime soit la fin de la location
     */
    private LocalDateTime getFin() {
        return fin;
    }

    
    /** 
     * methode qui permet de retourner la date de début de la location
     * @return DaateTime soit le début de la location.
     */
    private LocalDateTime getDebut() {
        return debut;
    }

	
    /** 
     * Methode qui permet de sauvegarder les locations de l'adhérents
     * @see LocationDao voir la documentation de LocationDao
     * @return int  retourne l index de la location
     */
    public int sauvegarder() {
		return locationDao.ajouterLocation(this);
	}
	
	
    /** 
     * methode qui permet de trouver les locations d'un client
     * @see LocationDao voir la documentation de LocationDao
     * @param client client ayant realsier une location
     * @param film film loué par le client
     * @return Location correspondant à la transaction recherché
     */
    public static Location trouverLocation(Client client, CD film) {
		return locationDao.trouverLocation(client, film);
	}

    /**
     * methode qui sert a mettre a jour une location d un client
     * @see LocationDao voir la documentation de LocationDao
	*/ 
     public void miseAJour() {
		locationDao.miseAJourLocation(idLocation, fin);
	}
	
	
    /** 
     * Methode qui permettra a un adherent de consulter son historique
     * @param adherent adherent voulant consulter son historique
     * @return List liste des locations effectues
     */
    public static List<Location> consulterHistorique(Adherent adherent) {
		return locationDao.chercherLocations(adherent);
	}

	
    /** 
     * Methode qui permet de trouver une location dans la base de donne 
     * @param codeLocation de la location
     * @return Location si elle a été trouvé.
     */
    public static Location trouverLocation(int codeLocation) {
		return locationDao.trouverLocation(codeLocation);
	}

    
    /** 
     * Methode qui nous indique si une location est en cours pour un client
      * @param cl client donc on veut voir si il a une location
      * @param cd cd dont on veut vérifier la présence dans les location en cours du client.
      * @return boolean indiquant si la location de ce CD est en cours pour ce client.
      */
    public static boolean estEnCours(Client cl,CD cd){
        Location location=locationDao.trouverLocation(cl,cd);
        return location.getFin() == null;
    }
    
     
     /** 
      * Methode qui nous indique si une location est en cours pour un client
      * @param cl adhérent donc on veut voir si il a une location
      * @param cd cd dont on veut vérifier la présence dans les location en cours de l'adhérent.
      * @return boolean indiquant si la location de ce CD est en cours pour cet adhérent.
      */
     public static boolean estEnCours(Adherent cl,CD cd){
        Location location=locationDao.trouverLocation(cl,cd);
        return location!=null;
    }

}
