package fc;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import fc.Test.ClientDaoImp;
/**
 * Petite descr
 */
public class FacadeNf {
	private HashMap<String, List<CD>> cdDispo;
	private Client client;
	private CarteAbonnement carteAbo;
	
	public FacadeNf(HashMap<String, List<CD>> cdDispo) {
		super();
		this.cdDispo = cdDispo;
		this.client = null;
		this.carteAbo = null;
	}
	
	//TODO setRestriction
	
	/** 
	 * @param carteAbo
	 * @throws AbonnementNonReconnusException
	 */
	public void connexion(CarteAbonnement carteAbo) throws AbonnementNonReconnusException {
		//TODO gérer un client non connecté
		client = new ClientDaoImp().rechercheAdherent(carteAbo);
		if(client == null) {
			throw new AbonnementNonReconnusException("Votre carte d'abonnement n'a pas été reconnus, vous n'êtes pas connecté.");
		}
		this.carteAbo = carteAbo;
	}

	
	/** 
	 * @param films
	 * @return HashMap<Film, List<CD>>
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
	 * disponibles quel que soit le format puis combine les en prenant en compte le
	 * format pour toujours connaitre le nombre de CD disponible pour chaque film.
	 * 
	 * @return Une HashMap<Film, List<CD>> qui combine le film servant de clé auquel est associé une
	 *         liste de CD ou null si le film n'est disponible que sous format
	 *         QRCode.
	 */
	public HashMap<Film, List<CD>> rechercherFilm() {
		return combinerSupports(client.rechercherFilm(null));
	}

	
	/** 
	 * @param titre
	 * @return HashMap<Film, List<CD>>
	 */
	public HashMap<Film, List<CD>> rechercherFilm(String titre) {
		return combinerSupports(client.rechercherFilm(titre));
	}

	/**
	 * Un client enprumte un CD, il est retiré de la liste des cd disponible.
	 * @param film Le film emprunté sur un support de type CD
	 * @throws ErreurEmpruntException s'il y a eu une erreur durant l'emprunt.
	 */
	public void emprunt(CD film) throws ErreurEmpruntException {
		if (client.emprunter(film)) {
			cdDispo.get(film.getFilm().getTitre()).remove(film);
		} else {
			throw new ErreurEmpruntException("Votre emprunt a échoué vous ne serez pas débité.");
		}

	}

	/**
	 * Un client enprumte un film sous un format QRCode.
	 * @param film  Le film emprunté sur un support de type QRCode
	 * @throws ErreurEmpruntException s'il y a eu une erreur durant l'emprunt.
	 */
	public void emprunt(QRCode film) throws ErreurEmpruntException {
		if (!client.emprunter(film)) {
			throw new ErreurEmpruntException("Votre emprunt a échoué vous ne serez pas débité.");
		}

	}

	/**
	 * Seul les films empruntés sur un support physique peuvent être rendu. Il est précisé s'ils sont endommagé ou non.
	 * @param film
	 * @param endommage
	 * @throws ErreurRenduException
	 */
	public void rendre(CD film, boolean endommage) throws ErreurRenduException {
		//TODO prendre en compte estencour de client
		if(!client.rendre(film, endommage)) {
			throw new ErreurRenduException("Un problème est survenu lors du rendu du film : "+film.getFilm().getTitre());
		} else if(!endommage) {
			cdDispo.get(film.getFilm().getTitre()).add(film);
		}
	}

	
	
	
	/** 
	 * 
	 * @param nom
	 * @param prenom
	 * @param dateNaissance
	 * @param courriel
	 */
	public void souscrire(String nom, String prenom, LocalDate dateNaissance, String courriel) {
		Adherent adherent = client.souscrire(nom, prenom, dateNaissance, courriel);
		carteAbo = adherent.titulaire;
		client = adherent;
	}

	
	/** 
	 * @see Adhérent.créditerCarte
	 * @param montant
	 */
	public void crediterCarte(Double montant) {
		if (carteAbo != null) {
			((Adherent) client).créditerCarte(carteAbo, montant);
		}
	}

}
