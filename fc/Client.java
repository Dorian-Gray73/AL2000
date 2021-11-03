package fc;

/**
 * Classe représentant un client anonyme.<br>
 * Un client sera notamment identifié par son adresse de facturation.<br>
 * Il dispose de méthodes d'emprunt, de rendu et de recherche de film.<br>
 * Il peut souscrire à un abonnement, payer une location et vérifier s'il a une
 * location en cours sur un film.
 */
public class Client {
    private String adresseFacturation;
    private CarteBancaire carteBancaire;

    /**
     * Constructeur de la classe client.
     * 
     * @param adresseFacturation L'adresse de facturation du client
     * @param carteBancaire      La carte bancaire du client
     */
    public Client(String adresseFacturation, CarteBancaire carteBancaire) {
        this.adresseFacturation = adresseFacturation;
        this.carteBancaire = carteBancaire;
    }

    /**
     * Constructeur d'un client copiant les attributs d'un autre client.
     * 
     * @param c Le client dont les attributs sont copiés
     */
    public Client(Client c) {
        this.adresseFacturation = c.adresseFacturation;
        this.carteBancaire = c.carteBancaire;
    }

    /**
     * Méthode d'emprunt d'un film.<br>
     * Elle a pour effet de créer une nouvelle location pour le client et le film.
     * 
     * @param film Le film que le client souhaite emprunter et pour lequel une
     *             éventuelle nouvelle location sera créée.
     * @return Un booléen indiquant si l'emprunt a réussi ou non.
     */
    public Boolean emprunter(Film film) {
        return false;
    }

    /**
     * Méthode de rendu d'un film.<br>
     * Elle vérifie si une location est en cours pour le film et générera une
     * facture que le client paiera.<br>
     * Le client peut aussi indiquer si le film est endommagé ou non.
     * 
     * @param film      Le film qui est rendu
     * @param endommage Renvoie le booléen indiquant si le film est endommagé ou non
     */
    public void rendre(Film film, Boolean endommage) {

    }

    /**
     * Méthode de recherche de films.<br>
     * Une requête sera notamment effectuée à la base de données en traitant les
     * filtres.
     * 
     * @param filtres Les filtres de la recherche passés en paramètre
     * @return Renvoie la liste des films obtenue par la recherche
     */
    public Film[] rechercherFilm(String[] filtres) {
        return null;
    }

    /**
     * Méthode de souscription à un nouvel abonnement.<br>
     * Un nouvel adhérent sera créé avec son nom, prénom, date de naissance, adresse
     * de facturation, carte bancaire.<br>
     * Une carte d'abonnement sera aussi créée pour le nouvel adhérent créé.
     * 
     * @return Renvoie le nouvel adhérent créé
     */
    public Adherent souscrire() {
        return null;
    }

    /**
     * Méthode de paiement d'une facture.<br>
     * 
     * @param prix Le prix de la facture à payer
     * @return Renvoie un booléen indiquant si le paiement a réussi ou non
     */
    public Boolean payer(float prix) {
        return false;
    }

    /**
     * Méthode de recherche d'une location en cours sur le film.
     * 
     * @param film Le film pour lequel on recherche si une loaction est en cours.
     * @return Renvoie un booléen indiquant si une location est en cours pour le
     *         film et le client ou non.
     */
    public Boolean estEnCours(Film film) {
        return false;
    }

    @Override
    public String toString() {
        return "{" +
            " adresseFacturation = '" + getAdresseFacturation() + "'" +
            ", carteBancaire = '" + getCarteBancaire() + "'" +
            "}";
    }

    private CarteBancaire getCarteBancaire() {
        return carteBancaire;
    }

    private String getAdresseFacturation() {
        return adresseFacturation;
    }
}
