package fc;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choix;
        boolean truc = true;
        CarteAbonnement ca;
        FacadeNf out = new FacadeNf();
        do {
            System.out.println("veuillez taper 1 pour vous créer un compte");
            System.out.println("veuillez taper 2 pour vous connecter");
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
                    System.out.println("veuillez renseigner votre numéro de carte");
                    String noCb = sc.nextLine();
                    System.out.println("veuillez renseigner votre cryptogramme visuel");
                    String crypto = sc.nextLine();
                    System.out.println("veuillez renseigner votre mois d expiration");
                    int month = sc.nextInt();
                    System.out.println("veuillez renseigner votre année d expiration");
                    int year = sc.nextInt();
                    LocalDate date = LocalDate.of(year, month, 1);
                    CarteBancaire cb = new CarteBancaire(noCb, crypto, date);
                    System.out.println("veuillez entrer votre adresse de facturation sur une seul ligne");
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
                    ca = out.souscrire(cb, adr, nom, prenom, anniv, email);
                    System.out.println("votre compte est bien créer merci d utiliser AL2000");
                    break;




                case 2:
                try {
                    out.connexion(ca);
                } catch (AbonnementNonReconnusException e) {
                    System.out.println("l'abonner n'est pas reconnu");
                    e.printStackTrace();
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
}
