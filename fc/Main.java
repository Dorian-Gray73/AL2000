package fc;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
public class Main {
    static int codeLocation = 0;
    static CD cdEmprunter = null;
    public static void main(String[] args) {
        final int QUITTER = 0;
        final int CREER = 1;
        final int CONNECTER = 2;
        final int RECHERCHER = 3;
        final int EMPRUNTER = 4;
        final int CREDITER = 5;
        final int RENDRE = 6;
        final int CONSULTER = 7;
        final int RESTRICTION = 8;

        Scanner sc = new Scanner(System.in);
        int choix;
       // boolean truc = true;
       
        CarteAbonnement ca = null;
        Client client = null;
        FacadeNf out = new FacadeNf();

        do {
            System.out.println("veuillez taper 0 pour quitter");
            System.out.println("veuillez taper 1 pour vous créer un compte");
            System.out.println("veuillez taper 2 pour vous connecter");
            System.out.println("veuillez taper 3 pour rechercher un film");
            System.out.println("veuillez taper 4 pour emprunter un film");
            System.out.println("veuillez taper 5 pour créditer votre carte d'abonnement");
            System.out.println("veuillez taper 6 pour rendre un film");
            System.out.println("veuillez taper 7 pour consulter l'historique");
            System.out.println("veuillez taper 8 pour ajouter des restrictions");
            choix = erreurMenu(sc);
            switch (choix) {

                case CREER:
                    System.out.println("Afin de souscrire un abonnement veuillez renseigner ces champs :");
                    CarteBancaire cb = infoCb(sc);
                    ca = infoAdherent(sc, out, cb, client);
                    System.out.println("votre compte est bien créer, merci d utiliser AL2000");
                    break;

                case CONNECTER:
                    try {
                        out.connexion(ca);
                        //client = null; //On garde un client et une carte de abonnement ?
                    } catch (AbonnementNonReconnusException e) {
                        System.out.println("l'abonné n'est pas reconnu");
                        e.printStackTrace();
                    }
                        break;
                
                case RECHERCHER:
                    System.out.println("Veuillez rentrer le titre du Film que vous recherchez: ");
                    sc.nextLine();
                    String titre = sc.nextLine();
                    HashMap<Film, List<CD>> resultat= out.rechercherFilm(titre);
                    for(Map.Entry<Film, List<CD>> map : resultat.entrySet()){
                        Film filmRes = map.getKey();
                        System.out.println("Voici les films disponibles: " + filmRes.toString());
                        List<CD> biblio = map.getValue();
                        if (biblio == null) {
                            System.out.println("Vous ne pouvez emprunter ce film que sous forme de QR code");
                        } else {
                            System.out.println("Vous pouvez pouvez l'emprunter sous forme de CD ou de QR code");
                            System.out.println("Si vous souhaitez l'emprunter choisissez l'option 4 dans le menu");
                        }
                    }
                    break;
                
                case EMPRUNTER: 
                    if (ca !=null) {
                        empruntAdh(sc, out);
                    }
                    else{
                        empruntClient(sc, out);                   
                    }
                    break;
                
                case CREDITER:
                    System.out.println("Veuillez entrer le montant à créditer :");
                    double montant = sc.nextDouble();
                    out.crediterCarte(montant);
                    break;
                
                case RENDRE:
                    System.out.println("Veuillez rentrer votre code de location: ");
                    int codeLocation = sc.nextInt();
                    System.out.println("Veuillez rentrer la valeur 1 si le CD est endommagé, 0 sinon");
                    int endommage = sc.nextInt();
                    CD cd = cdEmprunter; 
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
                    
                case CONSULTER:
                    List<Location> histo = out.consulterHistorique();
                    for(int i=0; i < histo.size(); i++){
                        Location loc = histo.get(i);
                        System.out.println(loc.toString());
                    }
                    break;

                case RESTRICTION:
                    System.out.println("Si vous voulez supprimer les restrictions taper 1 ou n'importe quel autre chiffre pour en ajouter");
                    int menu = sc.nextInt();
                    if (menu == 1) {
                        String [] restrVide = {};
                        ca.setRestriction(restrVide);
                    }
                    else{
                        System.out.println("Veuillez indiquer le nombre de restriction à ajouter: ");
                        int nbRestr = sc.nextInt();
                        sc.nextLine();
                        String [] restriction = new String[nbRestr];
                        for (int i = 0; i < nbRestr; i++) {
                            restriction[i] = sc.nextLine();
                            System.out.println(restriction[i]);
                        }
                        ca.setRestriction(restriction);
                    }

                break;
               
                case QUITTER:
                    break;

                default:
                System.out.println("Veuillez entrer une valeur correct !");
                    break;
            }

            
        } while (choix != 9);
        System.out.println("merci d'avoir utilisé l'AL2000");
        sc.close();
    }


    private static int erreurMenu(Scanner sc) {
        int choix;
        boolean truc;
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
        return choix;
    }



    private static void empruntAdh(Scanner sc, FacadeNf out) {
        System.out.println("Veuillez rentrer le titre du Film que vous recherchez: ");
        sc.nextLine();
        String titreLoc = sc.nextLine();

        HashMap<Film, List<CD>> resultatLoc= out.rechercherFilm(titreLoc);
        Set<Film> filmChoisie = resultatLoc.keySet();
        Iterator<Film> it = filmChoisie.iterator();
        Film choixFilm = it.next();
        System.out.println("Si vous voulez louer sous forme de QR Code taper 1");
        int choixType = sc.nextInt();

        if(choixType ==1){
            locationAdhQr(out, choixFilm);
        }
        else{
            locationAdhCD(out, resultatLoc, choixFilm);
            
            }
    }



    private static void locationAdhQr(FacadeNf out, Film choixFilm) {
        QRCode qrCodeAdh = new QRCode(choixFilm);
        try {
            out.emprunt(qrCodeAdh);                            
        } catch (ErreurEmpruntException e) {
            System.out.println("Une erreur lors de votre emprunt est survenu");
            e.printStackTrace();
        }
    }



    private static void locationAdhCD(FacadeNf out, HashMap<Film, List<CD>> resultatLoc, Film choixFilm) {
        List<CD> cdalouer = resultatLoc.get(choixFilm);
        CD cdlouer = cdalouer.get(0);
        try {
            codeLocation = out.emprunt(cdlouer);
            cdEmprunter = cdlouer;
        } catch (ErreurEmpruntException e) {
            System.out.println("Une erreur lors de votre emprunt est survenu");
            e.printStackTrace();
        }
    }



    private static void empruntClient(Scanner sc, FacadeNf out) {
        CarteBancaire cbClient = infoCb(sc);
        System.out.println("Veuillez rentrer votre addresse sur une seul ligne: ");
        String adr = sc.nextLine();

        System.out.println("Veuillez rentrer le titre du Film que vous recherchez: ");
        sc.nextLine();
        String titreLoc = sc.nextLine();

        HashMap<Film, List<CD>> resultatLoc= out.rechercherFilm(titreLoc);
        Set<Film> filmChoisie = resultatLoc.keySet();
        Iterator<Film> it = filmChoisie.iterator();
        Film choixFilm = it.next();

        System.out.println("Si vous voulez louer sous forme de QR Code taper 1");
        int choixType = sc.nextInt();
        
        if(choixType ==1){
            locationQr(out, cbClient, adr, choixFilm);
        }
        else{
            locationCd(out, cbClient, adr, resultatLoc, choixFilm);
        }
    }



    private static void locationCd(FacadeNf out, CarteBancaire cbClient, String adr, HashMap<Film, List<CD>> resultatLoc,
            Film choixFilm) {
        List<CD> cdalouer = resultatLoc.get(choixFilm);
        CD cdlouer = cdalouer.get(0);
        try {
            codeLocation = out.emprunt(cbClient, adr, cdlouer);
            cdEmprunter = cdlouer;
        } catch (ErreurEmpruntException e) {
            System.out.println("Une erreur lors de votre emprunt est survenu");
            e.printStackTrace();
        }
    }



    private static void locationQr(FacadeNf out, CarteBancaire cbClient, String adr, Film choixFilm) {
        QRCode qr = new QRCode(choixFilm);
        try {
            out.emprunt(cbClient,adr,qr);
        } catch (ErreurEmpruntException e) {
            System.out.println("Une erreur lors de votre emprunt est survenu");
            e.printStackTrace();
        }
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
