package fc;

import java.util.Date;

//debut de test pas dingue ...
public class test_unitaire {
    public static void main(String[] args) {
        System.out.println("\n\nTest sur les cartes abonnement ! \n\n");
        System.out.println("Testé actuellement : création, débit et blocage");
        CarteAbonnement mom = new CarteAbonnement();
        CarteAbonnement ca = new CarteAbonnement(mom);
        System.out.println(mom.toString());
        System.out.println(ca.toString());
        System.out.println(ca.debiterCarte(5.2f));
        System.out.println(ca.debiterCarte(11.2f));
        System.out.println(ca.getBlocage());
        System.out.println(ca.toString());
        
        System.out.println("\n\nTest sur les cartes Bancaire ! \n\n");
        System.out.println("Testé actuellement : création");
        CarteBancaire cb = new CarteBancaire("123", "456", new Date());
        System.out.println(cb);
        
        System.out.println("\n\nTest sur les Clients ! \n\n");
        System.out.println("Testé actuellement : création");
        Client client = new Client("UFR IM²AG - Bâtiment F 60, rue de la Chimie", cb);
        System.out.println(client);
        
        System.out.println("\n\nTest sur les Films ! \n\n");
        System.out.println("Testé actuellement : création");
        Film film = new Film("Mourrir peut attentre", "James Bond", "Un 007 comme un autre.", new String[] {"Daniel Craig" , "Léa Seydoux"});
        film.afficherInformations();
        System.out.println(film);
    }
    
}
