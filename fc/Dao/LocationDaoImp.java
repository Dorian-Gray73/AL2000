package fc.Dao;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fc.Adherent;
import fc.CD;
import fc.Client;
import fc.Location;

public class LocationDaoImp implements LocationDao {
	private static LocationDaoImp locationDaoInstance = null;
	private List<Location> locations;

	private LocationDaoImp() {
		this.locations = new ArrayList<Location>();
	}
	
	public  static LocationDaoImp getInstance() {
		if(locationDaoInstance == null) {
			locationDaoInstance = new LocationDaoImp();
		}
		return locationDaoInstance;
	}
	
	@Override
	public int ajouterLocation(Location location) {
		locations.add(location);
		return 0;
	}

	@Override
	public boolean miseAJourLocation(int idLocation, LocalDateTime fin) {
		Iterator<Location> it = locations.iterator();
		while (it.hasNext()) {
			Location tmp = it.next();
			if (tmp.getIdLocation() == idLocation)
				tmp.setFin(fin);
		}
		return false;
	}

	@Override
	public Location trouverLocation(Client client, CD film) {
		Iterator<Location> it = locations.iterator();
		while (it.hasNext()) {
			Location tmp = it.next();
			if (tmp.getClient().egale(client) && tmp.getSupport().getFilm().equals(film.getFilm()))
				return tmp;
		}
		return null;
	}

	public Location trouverLocation(Adherent client, CD film) {
		Iterator<Location> it = locations.iterator();
		while (it.hasNext()) {
			Location tmp = it.next();
			if (tmp.getClient().egale(client) && tmp.getSupport().getFilm().equals(film.getFilm()))
				return tmp;
		}
		return null;
	}

	@Override
	public List<Location> chercherLocations(Adherent adherent) {
		List<Location> res = new ArrayList<>();
		Iterator<Location> it = locations.iterator();
		while (it.hasNext()) {
			Location tmp = it.next();
			if (tmp.getClient().egale(adherent))
				res.add(tmp);
		}

		return res.isEmpty() ? null : res;
	}

	@Override
	public Location trouverLocation(int codeLocation) {
		Iterator<Location> it = locations.iterator();
		while (it.hasNext()) {
			Location tmp = it.next();
			if (tmp.getIdLocation() == codeLocation)
				return tmp;
		}

		return null;
	}
}
