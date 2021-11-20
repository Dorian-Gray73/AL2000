package fc.Dao;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fc.Adherent;
import fc.CD;
import fc.Client;
import fc.Location;
/**
 *  LocationDaoImp la classe qui implemente l'inteface LocationDao et qui gère la persitance de donnée des locations.
	 */
public class LocationDaoImp implements LocationDao {
	private static LocationDaoImp locationDaoInstance = null;
	private List<Location> locations;

	private LocationDaoImp() {
		this.locations = new ArrayList<Location>();
	}
	
	
	/** 
	 * @return LocationDaoImp
	 */
	public  static LocationDaoImp getInstance() {
		if(locationDaoInstance == null) {
			locationDaoInstance = new LocationDaoImp();
		}
		return locationDaoInstance;
	}
	
	
	/** 
	 * @param location la location a ajouter
	 * @return int
	 */
	@Override
	public int ajouterLocation(Location location) {
		locations.add(location);
		return locations.indexOf(location);
	}

	
	/** 
	 * @param idLocation l'id de la location a MAJ
	 * @param fin Date de la fin de la location
	 * @return boolean
	 */
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

	
	/** 
	 * @param client  le client dont on veut trouver la location
	 * @param film le film qu'on cherche
	 * @return la location du film en paramètre pour le client en paramètre.
	 */
	@Override
	public Location trouverLocation(Client client, CD film) {
		Iterator<Location> it = locations.iterator();
		while (it.hasNext()) {
			Location tmp = it.next();
			if (tmp.getClient().equals(client) && tmp.getSupport().equals(film))
				return tmp;
		}
		return null;
	}


	/**
	 * @param client  le client dont on veut trouver la location
	 * @param film le film qu'on cherche
	 * @return la location du film en paramètre pour le client en paramètre.
	 */
	public Location trouverLocation(Adherent client, CD film) {
		Iterator<Location> it = locations.iterator();
		while (it.hasNext()) {
			Location tmp = it.next();
			if (tmp.getClient().equals(client) && tmp.getSupport().getFilm().equals(film.getFilm()))
				return tmp;
		}
		return null;
	}

	
	/** 
	 * @param adherent  l'adhérent dont on veut trouver la location
	 * @return List liste de location de l'adhérent
	 */
	@Override
	public List<Location> chercherLocations(Adherent adherent) {
		List<Location> res = new ArrayList<>();
		Iterator<Location> it = locations.iterator();
		while (it.hasNext()) {
			Location tmp = it.next();
			if (tmp.getClient().equals(adherent))
				res.add(tmp);
		}

		return res.isEmpty() ? null : res;
	}

	
	/** 
	 * @param codeLocation code de la location
	 * @return Location
	 */
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
