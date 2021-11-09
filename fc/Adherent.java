package fc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Adherent extends Client {
    private String nom;
    private String prenom;
    private LocalDate dateNaiss;
    String courrielAdr;
    CarteAbonnement titulaire;
    ArrayList<CarteAbonnement> possede = null;

    // titulaire sera initialisé au moment de la creation de l'objet Adherent,
    // si c'est une carte mère elle va pointer vers elle-même dans titulaire.
    public Adherent(Client client, String nom, String prenom, LocalDate dateNaiss, String courriel) {
        this(client, nom, prenom, dateNaiss, courriel, null);
    }

    public Adherent(Client client, String nom, String prenom, LocalDate dateNaiss, String courriel, CarteAbonnement mere) {
        super(client);
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.courrielAdr = courriel;
        this.titulaire = new CarteAbonnement(mere);
        this.possede = new ArrayList<CarteAbonnement>();
        this.possede.add(this.titulaire);
    }

    /**
     * Fonction qui sera appeler si l'on veut emprunter un film
     * 
     * @param film est le film a emprunter
     * @return Boolean vrai si l'opération s'est faite sinon faux
     */
    public Boolean emprunter(Support film) {
        // TODO créer la location et l'ajouter à la persistence de donnée.
        return super.emprunter(film);
    }
    
    @Override
    protected double tarif() {
    	return 4.0;
    }

    /**
     * Fonction appelée pour rendre un CD
     * 
     * @param film      est le film a rendre
     * @param endommage vrai si le CD est emdommagé sinon faux
     * @throws LocationException 
     */
    public boolean rendre(CD film, Boolean endommage) {
        return super.rendre(film, endommage);
    }

    /**
     * permet de créditer une carte d'adhérent carte d'une somme somme
     * 
     * @param carte carte à créditer
     * @param somme est la somme à rajouter au solde de la carte en param
     */
    public void créditerCarte(CarteAbonnement carte, double somme) {
        carte.crediterCarte(somme);
    }

    /**
     * @return un String qui représente l'ensemble des location faite par l'adhérent
     */
    public List<Location> consulterHistorique() {
        // TODO, à recuperer de notre pérsistance de donnée.
        return Location.consulterHistorique(this);
    }

    /**
     * la fonction retourne le nom, prenom, date de naissance et adresse courriel de
     * l'adhérent
     * 
     * @return String
     */
    public String consulterInformationsProfil() {
        return this.toString();
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
            "nom = '" + nom + "'" +
            "\n prenom = '" + prenom + "'" +
            "\n dateNaiss = '" + dateNaiss + "'" +
            "\n courrielAdr = '" + courrielAdr + "'" +
            "\n titulaire = '" + titulaire + "'" +
            "\n possede = '" + possede + "'" +
            "\n}\n";
    }

    /**
     * payement débite un prix de la carte de l'adhérent
     * 
     * @param Prix
     */
    public Boolean paiement(double prix) {
        return (titulaire.debiterCarte(prix));
    }

    /**
     * pour changer les restrictions de la carte carte 
     * 
     * @param carte est la carte dont on va changer les restrictions
     * @param restrictions sont les nouvelles restrictions
     */
    public void changerRestrictions(CarteAbonnement carte, String[] restrictions) {
        carte.setRestriction(restrictions);
    }

    public Adherent Souscrire(String nom, String prenom, Date dateNaiss, String adr) {
        CarteAbonnement carteAbonnement = new CarteAbonnement(this.titulaire);
        if (possede == null) {
            possede = new ArrayList<CarteAbonnement>();
        }
        possede.add(carteAbonnement);
        return this/*new Adherent(this, nom, prenom, dateNaiss, adr, this.titulaire)*/;
    }

}
