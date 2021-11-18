package fc;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choix;
        boolean truc = true;
        CarteAbonnement ca = null;
        Client client = null;
        FacadeNf out = new FacadeNf();




        do {
            System.out.println("veuillez taper 1 pour vous créer un compte");
            System.out.println("veuillez taper 2 pour vous connecter");
            System.out.println("veuillez taper 3 pour rechercher un film");
            System.out.println("veuillez taper 4 pour emprunter un film");
            System.out.println("veuillez taper 5 pour créditer votre carte d'abonnement");
            System.out.println("veuillez taper 6 pour rendre un film");
            System.out.println("veuillez taper 7 pour consulter l'historique");
            System.out.println("veuillez taper 9 pour quitter");
            do{
                try {
                    choix = sc.nextInt();
                    truc = false;
                } catch (Exception e) {
                    System.out.println("Veuillez entrer une valeur correct !");
                    sc.nextLine();
                    truc = true;
                    choix = 0;
                }
            }while(truc);
            switch (choix) {

                case 1:
                    System.out.println("Afin de souscrire un abonnement veuillez renseigner ces champs :");
                    CarteBancaire cb = infoCb(sc);
                    ca = infoAdherent(sc, out, cb, client);
                    System.out.println("votre compte est bien créer, merci d utiliser AL2000");
                    break;




                case 2:
                try {
                    out.connexion(ca);
                    //client = null; //On garde un client et une carte de abonnement ?
                } catch (AbonnementNonReconnusException e) {
                    System.out.println("l'abonné n'est pas reconnu");
                    e.printStackTrace();
                }
                    break;
                
                case 3:
                    System.out.println("Veuillez rentrer le titre du Film que vous recherchez: ");
                    sc.nextLine();
                    String titre = sc.nextLine();
                    HashMap<Film, List<CD>> resultat= out.rechercherFilm(titre);
                    for(Map.Entry<Film, List<CD>> map : resultat.entrySet()){
                        Film filmRes = map.getKey();
                        System.out.println("Voici les films disponibles: " + filmRes.getTitre());
                    }
                    break;
                
                case 4: 
                    //TODO comment avoir la cb
                    // TODO c'est un adhérent ?
                    if (ca !=null) {
                        //ca.getAdherent.emprunter();
                        //out.emprunt(cb, adresseFacturation, film);
                    }
                    else{
                        CarteBancaire cbClient = infoCb(sc);
                        System.out.println("Veuillez rentrer votre addresse sur une seul ligne: ");
                        String adr = sc.nextLine();
                    }
                    break;
                
                case 5:
                    System.out.println("Veuillez entrer le montant à créditer :");
                    double montant = sc.nextDouble();
                    out.crediterCarte(montant);
                    break;
                
                case 6:
                    System.out.println("Veuillez rentrer votre code de location: ");
                    int codeLocation = sc.nextInt();
                    System.out.println("Veuillez rentrer la valeur 1 si le CD est endommagé, 0 sinon");
                    int endommage = sc.nextInt();
                    CD cd = null; //TODO
                    if (endommage == 1) {
                        try {
                            out.rendre(codeLocation, cd, true);
                        } catch (ErreurRenduException e) {
                            System.out.println("Une erreur est survenu veuillez nous excuser");
                            e.printStackTrace();
                        }                      
                    } else{
                        try {
                            out.rendre(codeLocation, cd, false);
                        } catch (ErreurRenduException e) {
                            System.out.println("Une erreur est survenu veuillez nous excuser");
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Merci d'avoir utilisé l'AL2000");
                    break;
                    
                case 7:
                    List<Location> histo = out.consulterHistorique();
                    for(int i=0; i < histo.size(); i++){
                        Location loc = histo.get(i);
                        System.out.println(loc.toString());
                    }
                    break;
                
                case 9:
                    break;

                default:
                System.out.println("Veuillez entrer une valeur correct !");
                    break;
            }

            
        } while (choix != 9);
        System.out.println("merci d'avoir utilisé l'AL2000");
        sc.close();
    }

    private static CarteAbonnement infoAdherent(Scanner sc, FacadeNf out, CarteBancaire cb,Client client) {
        CarteAbonnement ca;
        System.out.println("veuillez entrer votre adresse de facturation sur une seul ligne");
        sc.next();
        String adr = sc.nextLine();
        System.out.println("veuillez donner votre nom");
        String nom = sc.nextLine();
        System.out.println("veuillez donner votre prénom");
        String prenom = sc.nextLine();
        System.out.println("veuillez entrer votre adresse email");
        String email = sc.nextLine();
        System.out.println("Veuillez entrer votre jour de naissance");
        int j = sc.nextInt();
        System.out.println("Veuillez entrer votre mois de naissance");
        int m = sc.nextInt();
        System.out.println("Veuillez entrer votre année de naissance");
        int a = sc.nextInt();
        LocalDate anniv = LocalDate.of(a, m, j);
        client = new Client(adr,cb);
        ca = out.souscrire(cb, adr, nom, prenom, anniv, email);
        return ca;
    }

    private static CarteBancaire infoCb(Scanner sc) {
        System.out.println("veuillez renseigner votre numéro de carte");
        int noCbInt = sc.nextInt();
        String noCb =  ""+noCbInt;
        System.out.println("veuillez renseigner votre cryptogramme visuel");
        int cryptoInt = sc.nextInt();
        String crypto = ""+cryptoInt;
        System.out.println("veuillez renseigner votre mois d expiration");
        int month = sc.nextInt();
        System.out.println("veuillez renseigner votre année d expiration");
        int year = sc.nextInt();
        LocalDate date = LocalDate.of(year, month, 1);
        CarteBancaire cb = new CarteBancaire(noCb, crypto, date);
        return cb;
    }
}
