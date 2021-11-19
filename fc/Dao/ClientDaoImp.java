package fc.Dao;


import java.util.ArrayList;
import java.util.List;

import fc.Adherent;
import fc.CarteAbonnement;
import fc.CarteBancaire;
import fc.Client;

public class ClientDaoImp implements ClientDao {
	private static ClientDaoImp clientDaoInstance = null;
	private List<Client> clients;
	private List<Adherent> adherents;
	
	private ClientDaoImp() {
		super();
		this.clients = new ArrayList<Client>();
		this.adherents = new ArrayList<Adherent>();
	}
	
	public static ClientDaoImp getInstance() {
		if(clientDaoInstance == null) {
			clientDaoInstance = new ClientDaoImp();
		}
		return clientDaoInstance;
	}

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
	public int ajouterClient(Client client) {
		System.out.println("Ajout client"+ client);
		clients.add(client);
		return clients.indexOf(client);
	}

	/**
	 * La mise à jour d'un client n'est appelé qu'au moment de la souscription et devient donc un Adhérent.
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

	@Override
	public Client rechercheClient(CarteBancaire cb) {
		for(Client client : clients) {
			if(client.getCarteBancaire().equals(cb)) {
				return client;
			}
		}
		return null;
	}

}
