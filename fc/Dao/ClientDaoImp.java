package fc.Dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import fc.Adherent;
import fc.CarteAbonnement;
import fc.CarteBancaire;
import fc.Client;

public class ClientDaoImp implements ClientDao {
	private List<Client> clients;
	private List<Adherent> adherents;
	
	public ClientDaoImp() {
		super();
		this.clients = new ArrayList<Client>();
		this.adherents = new ArrayList<Adherent>();
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
		clients.add(client);
		return clients.indexOf(client);
	}

	/**
	 * La mise à jour d'un client n'est appelé qu'au moment de la souscription et devient donc un Adhérent.
	 */
	@Override
	public void miseAJourClient(Adherent adherent) {
		for(Client client : clients) {
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
