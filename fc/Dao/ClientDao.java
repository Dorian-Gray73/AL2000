package fc.Dao;

import fc.Adherent;
import fc.CarteAbonnement;
import fc.CarteBancaire;
import fc.Client;
/**
 * interface de gestion de la persistance des clients / adhérents
 */
public interface ClientDao {
	/**
	 * @param cb carte bancaire du client recherché
	 * @return Client le client a qui partient la carte
	 */
	public Client rechercheClient(CarteBancaire cb);
	/**
	 * @param carteAbo la carte abonnement dont on cherche l'adhérent
	 * @return Adherent : l'adhérent pour qui appartient la carte carteAbo
	 */
	public Adherent rechercheAdherent(CarteAbonnement carteAbo);
	/**
	 * @param client le client qu'on veut ajouter.
	 * @return int retourne l'index du client ajouté dans la liste des clients
	 */
	public int ajouterClient(Client client);
	/**
	 * La mise à jour d'un client n'est appelé qu'au moment de la souscription et devient donc un Adhérent.
	 * @param adherent supression de l'instance client qui est devenu l'adhérent adherent
	 */
	public void miseAJourClient(Adherent adherent);
}
