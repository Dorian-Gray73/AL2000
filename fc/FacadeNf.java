package fc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import fc.Dao.ClientDaoImp;
/**
 * Cette classe sert de façade qui va offrir une api permettant de manipuler le coeur fonctionnel de notre programme.
 */
public class FacadeNf {
	private HashMap<String, List<CD>> cdDispo;
	private Client client;
	private CarteAbonnement carteAbo;
	private ClientDaoImp clientDaoInstance = ClientDaoImp.getInstance();
	/**
	 * constructeur vide
	 */
	public FacadeNf(){
		cdDispo = new HashMap<>();
		client = null;
		carteAbo = null;
	}
	/**
	 * constructeur prenant en parametre la hash map correspondant a comment est rempli notre automate de distribution
	 * @param cdDispo correspondant a la liste des cd disponibles
	 */
	public FacadeNf(HashMap<String, List<CD>> cdDispo) {
		super();
		this.cdDispo = cdDispo;
		this.client = null;
		this.carteAbo = null;
	}
	
	/** 
	 * Methode permettant a un client de ce connecter pour devenir un adherent au yeux du systeme
	 * @param carteAbo correspondant a la carte de notre adherent
	 * @throws AbonnementNonReconnusException ;
	 */
	public void connexion(CarteAbonnement carteAbo) throws AbonnementNonReconnusException {
		client = clientDaoInstance.rechercheAdherent(carteAbo);
		if(client == null) {
			throw new AbonnementNonReconnusException("Votre carte d'abonnement n'a pas été reconnus, vous n'êtes pas connecté.");
		}
		this.carteAbo = carteAbo;
	}

	
	/** 
	 * Ici nous partons du principe que la seule chose que l'on veut connaitre à tous moment, est le nombre de
	 * CD disponible par film, la version QRCode étatn toujours disponible. Afin de le gérer, nous avons opté
	 * pour une hashmap contenant l'ensemble des films disponibles et dont chacun des films en question représente
	 * une clé à laquelle nous avons associé la liste des CD disponible ou null si aucun CD n'est disponible.
	 * Ainsi il est facile de savoir à tous moment si nous pouvons proposer la location d'un CD à l'utilisateur ou non.
	 * @param films l'ensemble des films disponible à la location.
	 * @return HashMap correspondant a l association du film et de ses cd correspondant
	 */
	private HashMap<Film, List<CD>> combinerSupports(List<Film> films) {
		HashMap<Film, List<CD>> supports = new HashMap<>();
		for (Film film : films) {
			if (cdDispo.containsKey(film.getTitre())) {
				supports.put(film, cdDispo.get(film.getTitre()));
			} else {
				supports.put(film, null);
			}
		}
		return supports;
	}

	/**
	 * Cette fonction recherche par un appel à la base de donnée tous les films
	 * disponibles quel que soit le format, les filtres avec les restrictions fournies par la carte puis combine les en prenant en compte le
	 * format pour toujours connaitre le nombre de CD disponible pour chaque film.
	 * 
	 * @return Une HashMap qui combine le film servant de clé auquel est associé une
	 *         liste de CD ou null si le film n'est disponible que sous format
	 *         QRCode.
	 */
	public HashMap<Film, List<CD>> rechercherFilm() {
		return combinerSupports(filtreRestrictions(Film.rechercherFilm(new HashMap<String, String>())));
	}

	/**
	 * Si un adhérent est connecté, on vérifie sur sa carte s'il y a des restrictions sur l'affichage des films
	 * et on filtre la liste en conséquence.
	 * @param films qui est une liste de film a filtrer
	 * @return List Une liste de film filtré ou la liste original si aucun adhérent n'est connecté.
	 */
	private List<Film> filtreRestrictions(ArrayList<Film> films) {
		if(carteAbo != null) {
			return films.stream()
					.filter(film -> {
						boolean isRestricted = false;
						for(String restriction : carteAbo.getRestriction()) 
							isRestricted = film.getGenre().toLowerCase().equals(restriction.toLowerCase());
						
					return !isRestricted;
					}).collect(Collectors.toList());
		}
		
		return films;
	}

	/** 
	 * @param titre du film que l'on recherche
	 * @return HashMap Une HashMap qui combine le film servant de clé auquel est associé une
	 *         liste de CD ou null si le film n'est disponible que sous format
	 *         QRCode.
	 */
	public HashMap<Film, List<CD>> rechercherFilm(String titre) {
		HashMap<String, String> filtres = new HashMap<>();
		filtres.put("titre", titre);
		return combinerSupports(filtreRestrictions(Film.rechercherFilm(filtres)));
	}


	/**
	 * Vérification qu'un client est bien connecté s'il ne s'agit pas d'un adhérent
	 * Un client enprumte un film, s'il s'agit d'un CD il est retiré de la liste des cd disponible.
	 * @param film Le film emprunté sur un support de type CD
	 * @return Le code lié à la location(ici ce sera l'id de la location dans la base) qui sert pour un simple client pour pouvoir rendre correctement son cd
	 * @throws ErreurEmpruntException ;
	 */
	public int emprunt(Support film) throws ErreurEmpruntException {
		return emprunt(null, null, film);
	}

	/**
	 * Vérification qu'un client est bien connecté s'il ne s'agit pas d'un adhérent
	 * Un client enprumte un film, s'il s'agit d'un CD il est retiré de la liste des cd disponible.
	 * @param cb représentant la carte bancaire du client
	 * @param adresseFacturation représentant l'adresse du client
	 * @param film Le film emprunté sur un support de type CD
	 * @return int Le code lié à la location(ici ce sera l'id de la location dans la base) qui sert pour un simple client pour pouvoir rendre correctement son cd
	 * @throws ErreurEmpruntException ;
	 */
	public int emprunt(CarteBancaire cb, String adresseFacturation, Support film) throws ErreurEmpruntException {
		if(carteAbo == null) 
			clientConnected(cb, adresseFacturation);
		
		int code = client.emprunter(film);
		if (code != -1) {
			if(cdDispo.get(film.getFilm().getTitre()) != null && cdDispo.get(film.getFilm().getTitre()).contains(film)) {
				cdDispo.get(film.getFilm().getTitre()).remove(film);
			}
		} else {
			throw new ErreurEmpruntException("Votre emprunt a échoué vous ne serez pas débité.");
		}
		
		return code;

	}


	/**
	 * Il faut vérifier que le client n'est pas null.
	 * On vérifie si le clietn n'a pas déjà été enregistré, si ce n'est pas le cas nous ajoutons le nouveau.
	 * @param cb representant la carte bancaire du client
	 * @param adresseFacturation representant l adresse du client
	 */
	private void clientConnected(CarteBancaire cb, String adresseFacturation) {
		if(client == null) {
			client = clientDaoInstance.rechercheClient(cb);
			if(client == null) {
				client = new Client(adresseFacturation, cb);
				clientDaoInstance.ajouterClient(client);
			}
		}
	}

	/**
	 * Seul les films empruntés sur un support physique peuvent être rendu. Il est précisé s'ils sont endommagé ou non.
	 * @param codeLocation du film que le client
	 * @param film que le client ou l adherent rend
	 * @param endommage true si le cd est endommage, false sinon
	 * @throws ErreurRenduException ;
	 */
	public void rendre(int codeLocation, CD film, boolean endommage) throws ErreurRenduException {
		if(client == null) {
			Location location = Location.trouverLocation(codeLocation);
			if (location == null)
				throw new ErreurRenduException("Cette location n'existe pas .");
			client = location.getClient();
		}
		
		if(client.estEnCours(film) && !client.rendre(film, endommage)) {
			throw new ErreurRenduException("Un problème est survenu lors du rendu du film : "+film.getFilm().getTitre());
		} else if(!endommage) {
			cdDispo.get(film.getFilm().getTitre()).add(film);
		}
	}


	/**
	 * Un client peut souscrire à un abonnement.
	 * @param cb la carte bancaire du client
	 * @param adresseFacturation l'adresse de facturation du client
	 * @param nom du client 
	 * @param prenom du client
	 * @param dateNaissance du client
	 * @param courriel du client
	 * @return carteAbonnement généré pour ce nouvel abonnement.
	 */
	public CarteAbonnement souscrire(CarteBancaire cb, String adresseFacturation, String nom, String prenom, LocalDate dateNaissance, String courriel) {
		clientConnected(cb, adresseFacturation);
		Adherent adherent = client.souscrire(nom, prenom, dateNaissance, courriel);
		carteAbo = adherent.getTitulaire();
		client = adherent;
		return carteAbo;
	}

	
	/** 
	 * @see Adherent pour voir la methode crediterCarte
	 * @param montant dont on veut créditer la carte.
	 */
	public void crediterCarte(Double montant) {
		if (carteAbo != null) {
			((Adherent) client).crediterCarte(carteAbo, montant);
		} else {
			System.out.println("Vous n'avez pas de compte adhérent ou vous n'êtes pas connecté.");
		}
	}
	
	/**
	 * Change les restrictions de la carte que l'adhérent à utilisé pour se connecter
	 * Dans un premier temps les restrictions ne sont que portées sur le thème des films à restreindre.
	 * @param restrictions un tableau contenant les thèmes bloqué à l'affichage.
	 */
	public void setRestriction(String[] restrictions) {
		this.setRestriction(carteAbo, restrictions);
	}
	

	/**
	 * Change les restrictions de la carte que l'adhérent à choisi
	 * Dans un premier temps les restrictions ne sont que portées sur le thème des films à restreindre.
	 * @param carte dont l'adhérent veut changer les restrictions.
	 * @param restrictions un tableau contenant les thèmes bloqué à l'affichage.
	 */
	public void setRestriction(CarteAbonnement carte, String[] restrictions) {
		if(carteAbo != null) {
			carte.setRestriction(restrictions);
		}
	}
	
	/**
	 * Accède à l'historique d'un adhérent
	 * @return List qui représente l'historique de l'adhérent.
	 */
	public List<Location> consulterHistorique() {
		if(carteAbo != null) {
			Adherent tmp = (Adherent)client;
			return tmp.consulterHistorique();
		}
		return null;
	}
	

	/**
	 * Obtient le solde d'une carte d'abonnement
	 * @return double qui représente le solde de la carte de l'abonné.
	 */
	public double getSolde() {
		return carteAbo.getSolde();
	}

}
