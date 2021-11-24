package ui;

import fc.CarteBancaire;
import fc.Client;
import fc.FacadeNf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class souscription extends JPanle{
    private JTextField veuillezRemplirLesChampsTextField;
    private JTextField namefield;
    private JTextField moisField;
    private JTextField anneeField;
    private JTextField jourfield;
    private JTextField cartechamp1;//num carte
    private JTextField cartechamp2;//mois
    private JTextField cartechamp3;//anne
    private JTextField cartechamp4;//crypto
    private JLabel prenomField;
    private JPanel cartepane;
    private JButton valideButton;
    private JTextField adresseField;
    private JPanel centerpanel;
    private JTextField mailField;
    private FacadeNf out=new FacadeNf();

    public souscription(String title) {
        super(title);
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();


        valideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = namefield.getText();
                String prenom = prenomField.getText();
                String mail = mailField.getText();
                String annivMoisStr = moisField.getText();
                int annivMois = Integer.parseInt(annivMoisStr);
                String annivAnStr = anneeField.getText();
                int annivAn = Integer.parseInt(annivAnStr);
                String annivJourStr = jourfield.getText();
                int annivJour = Integer.parseInt(annivJourStr);
                String adr = adresseField.getText();
                LocalDate dateAnniv = LocalDate.of(annivAn,annivMois,annivJour);
                String numCb = cartechamp1.getText();
                String crypto = cartechamp4.getText();
                String monthStr = cartechamp2.getText();
                int month = Integer.parseInt(monthStr);
                String yearStr = cartechamp3.getText();
                int year = Integer.parseInt(yearStr);
                LocalDate dateCb = LocalDate.of(year, month, 1);
                CarteBancaire cb = new CarteBancaire(numCb,crypto,dateCb);
                System.out.println(out.souscrire(cb,adr,nom,prenom,dateAnniv,mail));
            }
        });
    }


    public  static void main(String args[]){
         JFrame frame=new souscription("souscription");
         frame.setVisible(true);
     }


}
