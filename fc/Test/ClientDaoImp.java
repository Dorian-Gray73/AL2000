package fc.Test;

import java.sql.Connection;

import fc.Adherent;
import fc.CarteAbonnement;
import fc.Client;

public class ClientDaoImp implements ClientDao {
	Connection conn = null;
	static final String CONN_URL = "jdbc:sqlite:BASE.db";

	@Override
	public Adherent rechercheAdherent(CarteAbonnement carteAbo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int ajouterClient(Client client) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void miseAJourClient(Adherent adherent) {
		// TODO Auto-generated method stub
		
	}

}
