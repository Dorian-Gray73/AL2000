package fc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choix;
        boolean truc = true;
        do {
            System.out.println("veuillez taper 1 pour vous connecter");
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
                    System.out.println("test");
                    break;

                case 2:
                    System.out.println("test2");
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
