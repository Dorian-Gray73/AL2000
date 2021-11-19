package fc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import fc.Dao.ClientDao;
import fc.Dao.ClientDaoImp;
import fc.Dao.FilmDaoImp;

/**
 * Classe représentant un client anonyme.<br>
 * Un client sera notamment identifié par son adresse de facturation.<br>
 * Il dispose de méthodes d'emprunt, de rendu et de recherche de film.<br>
 * Il peut souscrire à un abonnement, payer une location et vérifier s'il a une
 * location en cours sur un film.
 */
public class Client {
	private String adresseFacturation;
	private CarteBancaire carteBancaire;
	private static FilmDaoImp BD = new FilmDaoImp();

	/**
	 * Constructeur de la classe client.
	 * 
	 * @param adresseFacturation L'adresse de facturation du client
	 * @param carteBancaire      La carte bancaire du client
	 */
	public Client(String adresseFacturation, CarteBancaire carteBancaire) {
		this.adresseFacturation = adresseFacturation;
		this.carteBancaire = carteBancaire;
	}

	/**
	 * Constructeur d'un client copiant les attributs d'un autre client.
	 * 
	 * @param c Le client dont les attributs sont copiés
	 */
	protected Client(Client c) {
		this.adresseFacturation = c.adresseFacturation;
		this.carteBancaire = c.carteBancaire;
	}

	/**
	 * Méthode d'emprunt d'un film.<br>
	 * Elle a pour effet de créer une nouvelle location pour le client et le film.
	 * 
	 * @param film Le film que le client souhaite emprunter et pour lequel une
	 *             éventuelle nouvelle location sera créée.
	 * @return le code de la location ou -1 si la location a échouée.
	 */
	public int emprunter(Support film) {
		LocalDateTime dateEmprunt = LocalDateTime.now();
		Location location = new Location(dateEmprunt, tarif(), this, film);
		
		if (film.calculerDuree() != -1) { // Alors il s'agit d'un QRCode car au moment de l'emprunt une seule durée peut être définie
			location.setFin(dateEmprunt.plusHours(film.calculerDuree()));
			
			Double prix = location.CalculerPrix();
			if (prix == -1) // Erreur au moment du calcul du prix
				return -1;
			
			int code = location.sauvegarder();
			if(code != -1 && paiement(prix)) {
				//On ne paie que si la sauvegarde dans la base fonctionne
				return code;
			}
			return -1;
		}
		
		return location.sauvegarder();
	}

	protected double tarif() {
		return 5.0;
	}

	/**
	 * Méthode de rendu d'un film.<br>
	 * Elle vérifie si une location est en cours pour le film et générera une
	 * facture que le client paiera.<br>
	 * Le client peut aussi indiquer si le film est endommagé ou non.
	 * 
	 * @param film      Le film qui est rendu
	 * @param endommage Renvoie le booléen indiquant si le film est endommagé ou non
	 */
	public boolean rendre(CD film, Boolean endommage) {
		/*
		 * if (!estEnCours(film)) return false;
		 * 
		 * Location l = getLocation(film);
		 */
		Location l = getLocationEnCours(film);
		if (l == null)
			return false;

		l.setFin(LocalDateTime.now());

		Double prix = l.CalculerPrix();

		if (prix == -1) // Erreur au moment du calcul du prix
			return false;

		l.getSupport().setEndommage(endommage);
		l.miseAJour();
		
		
		if(endommage) {
			System.out.println("Vous serez remboursé après constatation par un technicien.");
		}
		return paiement(prix);
	}

	/**
	 * @param film
	 * @return Location
	 */
	private Location getLocationEnCours(CD film) {
		return Location.trouverLocation(this, film);
	}

	/**
	 * Méthode de recherche de films.<br>
	 * Une requête sera notamment effectuée à la base de données en traitant les
	 * filtres.
	 *
	 * @return Renvoie la liste des films obtenue par la recherche
	 */
	public ArrayList<Film> rechercherFilm(String titre) {
		// FilmDaoImp BD = new FilmDaoImp();
		HashMap<String, String> filtres = new HashMap<String, String>();
		filtres.put("titre", titre);
		return BD.chercher(filtres);
	}

	/**
	 * Méthode de souscription à un nouvel abonnement.<br>
	 * Un nouvel adhérent sera créé avec son nom, prénom, date de naissance, adresse
	 * de facturation, carte bancaire.<br>
	 * Une carte d'abonnement sera aussi créée pour le nouvel adhérent créé.
	 * 
	 * @return Renvoie le nouvel adhérent créé
	 */
	public Adherent souscrire(String nom, String prenom, LocalDate dateNaissance, String courriel) {
		Adherent tmp = new Adherent(this, nom, prenom, dateNaissance, courriel);
		majClientAdherent(tmp);
		return tmp;
	}

	/**
	 * Méthode de paiement d'une facture.<br>
	 * @param prix Le prix de la facture à payer
	 * @return Renvoie un booléen indiquant si le paiement a réussi ou non
	 */
	public Boolean paiement(double prix) {
		return carteBancaire.debiterCarte(prix);
	}

	/**
	 * Méthode de recherche d'une location en cours sur le film.
	 *
	 * @return Renvoie un booléen indiquant si une location est en cours pour le
	 *         film et le client ou non.
	 */
	public Boolean estEnCours(CD cd) {
		return Location.estEnCours(this,cd);
	}


	public boolean egale(Client c){
		return c.carteBancaire.equals(this.carteBancaire);
	}
	
	/**
	 * Connection BDD pour sauvegarder le client s'il n'existe pas déjà
	 */
	public void sauvegarder() {
		ClientDao clientDao = new ClientDaoImp();
		clientDao.ajouterClient(this);
	}
	
	private void majClientAdherent(Adherent adherent) {
		ClientDao clientDao = new ClientDaoImp();
		clientDao.miseAJourClient(adherent);
	}

	/**
	 * @return String
	 */
	@Override
	public String toString() {
		return "{" + " adresseFacturation = '" + getAdresseFacturation() + "'" + ", carteBancaire = '"
				+ getCarteBancaire() + "'" + "}";
	}

	/**
	 * @return CarteBancaire
	 */
	public CarteBancaire getCarteBancaire() {
		return carteBancaire;
	}

	/**
	 * @return String
	 */
	public String getAdresseFacturation() {
		return adresseFacturation;
	}
	
}
