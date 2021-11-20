package fc.Dao;

import java.time.LocalDateTime;
import java.util.List;

import fc.Adherent;
import fc.CD;
import fc.Client;
import fc.Location;
/**
	 * l'interface de gestion de la persistance des locations
	 */
public interface LocationDao {
	/**
	 * @param location la location a ajouter
	 * @return int
	 */
	public int ajouterLocation(Location location);
	/**
	 * @param idLocation l'id de la location a MAJ
	 * @param fin Date de la fin de la location
	 * @return boolean
	 */
	public boolean miseAJourLocation(int idLocation, LocalDateTime fin);
	/**
	 * @param client  le client dont on veut trouver la location
	 * @param film le film qu'on cherche
	 * @return la location du film en paramètre pour le client en paramètre.
	 */
	public Location trouverLocation(Client client, CD film);
	/**
	 * @param codeLocation code de la location
	 * @return Location
	 */
	public Location trouverLocation(int codeLocation);
	/**
	 * @param adherent  l'adhérent dont on veut trouver la location
	 * @return List liste de location de l'adhérent
	 */
	public List<Location> chercherLocations(Adherent adherent);
}
