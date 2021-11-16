package fc.Dao;

import fc.Adherent;
import fc.CarteAbonnement;
import fc.CarteBancaire;
import fc.Client;

public interface ClientDao {
	public Client rechercheClient(CarteBancaire cb);
	public Adherent rechercheAdherent(CarteAbonnement carteAbo);
	public int ajouterClient(Client client);
	public void miseAJourClient(Adherent adherent);
}
