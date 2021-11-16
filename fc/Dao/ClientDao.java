package fc.Test;

import fc.Adherent;
import fc.CarteAbonnement;
import fc.Client;

public interface ClientDao {
	public Adherent rechercheAdherent(CarteAbonnement carteAbo);
	public int ajouterClient(Client client);
	public void miseAJourClient(Adherent adherent);
}
