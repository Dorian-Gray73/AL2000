package fc.Dao;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import fc.Adherent;
import fc.CD;
import fc.Client;
import fc.Location;

public class LocationDaoImp implements LocationDao {
	//List<Location> locations=new List<Location>();
	
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
	//TODO definir equals dans client
	public Location trouverLocation(Client client, CD film) {
		Iterator<Location> it=locations.iterator();
		while(it.hasNext()){
			Location tmp=it.next();
		//	if( tmp.getClient().equals(client) &&  tmp.getSupport() == client)
		}
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
