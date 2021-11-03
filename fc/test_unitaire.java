package fc;
//debut de test pas dingue ...
public class test_unitaire {
    public static void main(String[] args) {
        CarteAbonnement mom = new CarteAbonnement();
        CarteAbonnement ca = new CarteAbonnement(mom);
        System.out.println(mom.toString());
        System.out.println(ca.debiterCarte(5.2f));
        System.out.println(ca.debiterCarte(11.2f));
        System.out.println(ca.getBlocage());
        
    }
    
}
