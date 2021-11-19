package fc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui represente un adherent.<br>
 * Un adhérent est represente par un nom, un prenom, une date de naissance, une adresse mail,
 * une carte d abonnement mere (si la carte en possede pas alors elle ce pointe elle meme) et enfin un tableau qui contient toute les cartes filles.
 * Un adherent possede les methodes emprunter, rendre, crediterCarte, consulterHistorique, consulterInformation, paiement, changerRestriction et souscrire
 */

public class Adherent extends Client {
    private String nom;
    private String prenom;
    private LocalDate dateNaiss;
    String courrielAdr;
    CarteAbonnement titulaire;
    ArrayList<CarteAbonnement> possede = null;

    /**
     * Constructeur de la classe Adherent dans le cas ou il y a pas de carte d'abonnement mere
     * @param client 
     * @see Client
     * @param nom chaine de caractere representant le nom de famille de l adherent
     * @param prenom chaine de caractere representant le prenom de l adherent
     * @param dateNaiss represente la date de naissance de l adherent
     * @param courriel represente l adresse mail de l adherent
     */
    public Adherent(Client client, String nom, String prenom, LocalDate dateNaiss, String courriel) {
        this(client, nom, prenom, dateNaiss, courriel, null);
    }


    /**
     * Constructeur de la classe Adherent dans le cas ou il y a une carte d'abonnement mere
     * @param client 
     * @see Client
     * @param nom chaine de caractere representant le nom de famille de l adherent
     * @param prenom chaine de caractere representant le prenom de l adherent
     * @param dateNaiss represente la date de naissance de l adherent
     * @param courriel represente l adresse mail de l adherent
     * @param mere carte mere de la carte adherent créé
     */
    public Adherent(Client client, String nom, String prenom, LocalDate dateNaiss, String courriel, CarteAbonnement mere) {
        super(client);
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.courrielAdr = courriel;
        this.titulaire = new CarteAbonnement(mere);
        this.possede = new ArrayList<CarteAbonnement>();
        this.possede.add(this.getTitulaire());
    }

    /**
     * Fonction qui sera appeler si l'on veut emprunter un film
     * 
     * @param film est le film a emprunter
     * @return Boolean vrai si l'opération s'est faite sinon faux
     */
    public int emprunter(Support film) {
        if (this.getTitulaire().getBlocage()){
            System.out.println("carte bloquée !");
            return -1;
        }

        if (film.getFilm().verifieGenre(this.getTitulaire().getRestriction())){
            System.out.println("film interdit !");
            return -1;
        }
        return super.emprunter(film);

    }
    
    @Override
    protected double tarif() {
    	return 4.0;
    }

    public Boolean estEnCours(CD cd) {
		return Location.estEnCours(this,cd);
	}
    
    /**
     * Fonction appelée pour rendre un CD
     *
     * @param film      est le film a rendre
     * @param endommage vrai si le CD est emdommagé sinon faux
     * @throws LocationException
     */
    public boolean rendre(CD film, Boolean endommage) {
        if(this.estEnCours(film))
            return super.rendre(film, endommage);
        else
            return false;
    }

    /**
     * méthode qui permet de créditer une carte d'adhérent carte d'une somme
     * 
     * @param carte carte à créditer
     * @param somme est la somme à rajouter au solde de la carte en param
     * @return void
     */
    public void créditerCarte(CarteAbonnement carte, double somme) {
        carte.crediterCarte(somme);
    }

    /**
     * 
     * @return histo Une chaine de caractère qui représente l'ensemble des location faite par l'adhérent
     */
    public List<Location> consulterHistorique() {
        return Location.consulterHistorique(this);
    }

    /**
     * méthode qui permet de retourner les informations de l'adhérent
     * @return info est une chaine de caractères contenant les informations de l'adhérents
     */
    public String consulterInformationsProfil() {
        String info = this.toString();
        return info;
    }

    /** 
     * Fonction qui redefini la fonction tostring.<br>
     * Permet d'avoir un affichage plus comprehensible 
     * @see Object.toString
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
            "nom = '" + nom + "'" +
            "\n prenom = '" + prenom + "'" +
            "\n dateNaiss = '" + dateNaiss + "'" +
            "\n courrielAdr = '" + courrielAdr + "'" +
            "\n titulaire = '" + getTitulaire() + "'" +
            "\n possede = '" + possede + "'" +
            "\n}\n";
    }

    /**
     * méthode qui débite un prix de la carte de l'adhérent
     * 
     * @param Prix représente le prix d'une location
     * @see CarteAbonnement.debiterCarte
     * @return reussite le boolean sera true si la transaction c'est bien passé 
     */
    public Boolean paiement(double prix) {
        Boolean reussite = getTitulaire().debiterCarte(prix);
        return reussite;
    }

    /**
     * méthode qui permet de changer les restrictions de la carte carte 
     * 
     * @param carte est la carte dont on va changer les restrictions
     * @param restrictions sont les nouvelles restrictions
     * @return void
     */
    public void changerRestrictions(CarteAbonnement carte, String[] restrictions) {
        carte.setRestriction(restrictions);
    }

    
    /** 
     * Methode qui permet de créer une nouvelle carte d'abonnement.<br>
     * La nouvelle carte d abonnement créer sera rattaché à this.
     * @param nom représente le nom de la nouvelle personne detenant la carte.
     * @param prenom représente le prenom de la nouvelle personne detenant la carte.
     * @param dateNaiss représente la nouvelle date de naissance de la nouvelle personne detenant la carte.
     * @param adr représente la nouvelle adresse de la nouvelle personne detenant la carte.
     * @return Adherent retourne l'Adherent nouvellement créé.
     */
    public Adherent Souscrire(String nom, String prenom, LocalDate dateNaiss, String adr) {
        CarteAbonnement carteAbonnement = new CarteAbonnement(this.getTitulaire());
        if (possede == null) {
            possede = new ArrayList<CarteAbonnement>();
        }
        possede.add(carteAbonnement);
        return new Adherent(this, nom, prenom, dateNaiss, adr, this.getTitulaire());
    }


	public CarteAbonnement getTitulaire() {
		return titulaire;
	}
	
	public ArrayList<CarteAbonnement> getPossede() {
		return possede;
	}
	
	

}
