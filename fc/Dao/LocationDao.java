package fc.Dao;

import java.time.LocalDateTime;
import java.util.List;

import fc.Adherent;
import fc.CD;
import fc.Client;
import fc.Location;

public interface LocationDao {
	public int ajouterLocation(Location location);
	public boolean miseAJourLocation(int idLocation, LocalDateTime fin);
	public Location trouverLocation(Client client, CD film);
	public List<Location> chercherLocations(Adherent adherent);
}
