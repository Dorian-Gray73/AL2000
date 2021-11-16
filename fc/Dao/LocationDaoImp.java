package fc.Dao;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import fc.Adherent;
import fc.CD;
import fc.Client;
import fc.Location;

public class LocationDaoImp implements LocationDao {
	Connection conn = null;
	static final String CONN_URL = "jdbc:sqlite:BASE.db";
	
	@Override
	public int ajouterLocation(Location location) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean miseAJourLocation(int idLocation, LocalDateTime fin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Location trouverLocation(Client client, CD film) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Location> chercherLocations(Adherent adherent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location trouverLocation(int codeLocation) {
		// TODO Auto-generated method stub
		return null;
	}

}
