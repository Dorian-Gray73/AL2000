package fc.Dao;


import java.util.ArrayList;
import java.util.List;

import fc.Adherent;
import fc.CarteAbonnement;
import fc.CarteBancaire;
import fc.Client;
/**
 * l'implementation de l'inteface ClientDao qui gère de la persistance des données client / adherent
	 */
public class ClientDaoImp implements ClientDao {
	private static ClientDaoImp clientDaoInstance = null;
	private List<Client> clients;
	private List<Adherent> adherents;
	
	private ClientDaoImp() {
		super();
		this.clients = new ArrayList<Client>();
		this.adherents = new ArrayList<Adherent>();
	}
	
	
	/** 
	 * @return ClientDaoImp ;
	 */
	public static ClientDaoImp getInstance() {
		if(clientDaoInstance == null) {
			clientDaoInstance = new ClientDaoImp();
		}
		return clientDaoInstance;
	}

	
	/** 
	 * @param carteAbo la carte abonnement dont on cherche l'adhérent
	 * @return Adherent : l'adhérent pour qui appartient la carte carteAbo
	 */
	@Override
	public Adherent rechercheAdherent(CarteAbonnement carteAbo) {
		for(Adherent adherent : adherents) {
			if(adherent.getTitulaire().equals(carteAbo) || adherent.getPossede().contains(carteAbo)) {
				return adherent;
			}
		}
		return null;
	}
	
	@Override
	public Client rechercheAdherent(String nom, String prenom) {
		adherents.add(new Adherent(new Client("", new CarteBancaire("", "", null)), nom, prenom, null, ""));
		for(Adherent adherent : adherents) {
			if(adherent.getNom().equalsIgnoreCase(nom) && adherent.getPrenom().equalsIgnoreCase(prenom)) {
				return adherent;
			}
		}
		return null;
	}

	
	/** 
	 * @param client le client qu'on veut ajouter.
	 * @return int retourne l'index du client ajouté dans la liste des clients
	 */
	@Override
	public int ajouterClient(Client client) {
		System.out.println("Ajout client"+ client);
		clients.add(client);
		return clients.indexOf(client);
	}

	/**
	 * La mise à jour d'un client n'est appelé qu'au moment de la souscription et devient donc un Adhérent.
	 * @param adherent supression de l'instance client qui est devenu l'adhérent adherent
	 */
	@Override
	public void miseAJourClient(Adherent adherent) {
		System.out.println("Adhérent à ajouter"+adherent);
		for(Client client : clients) {
			System.out.println("client " + client);
			if(client.getCarteBancaire().equals(adherent.getCarteBancaire())) {
				clients.remove(client);
			}
		}
		adherents.add(adherent);
	}

	
	/** 
	 * @param cb carte bancaire du client recherché
	 * @return Client le client a qui partient la carte
	 */
	@Override
	public Client rechercheClient(CarteBancaire cb) {
		Client result = null;
		for (Client client : clients) {
			if (client.getCarteBancaire().equals(cb)) {
				result = client;
				break;
			}
		}
		return result;
	}

}
