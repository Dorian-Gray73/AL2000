package ui;

import fc.CarteBancaire;
import fc.FacadeNf;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class Souscription extends JPanel {
	private JPanel fieldsPanel;
	private JPanel personnalPanel;
	private JPanel datePanel;
	private JPanel othersPanel;
	private JPanel cbPanel;
	private JTextField nameField;
	private JTextField prenomField;
	private JTextField jourField;
	private JTextField moisField;
	private JTextField anneeField;
	private JTextField adresseField;
	private JTextField mailField;
	private JTextField numCBField;// num carte
	private JTextField moisCBField;// mois
	private JTextField anneeCBField;// annee
	private JTextField CryptoCBField;// crypto
	
	private JButton valideButton;
	
	private FacadeNf out = new FacadeNf();

	public Souscription() {
		super();
		setLayout(new BorderLayout());
		JPanel centragePanel = new JPanel(new FlowLayout());
		centragePanel.add(new JLabel("Veuillez remplir les champs suivant svp :"));
		add(centragePanel, BorderLayout.NORTH);
		add(fieldsPanel = new JPanel(new GridLayout(1, 1, 10, 10)));
		
		personnalPanel = new JPanel(new StackLayout());
		othersPanel = new JPanel(new StackLayout());
		cbPanel = new JPanel(new StackLayout());
		
		fieldsPanel.add(personnalPanel);
		fieldsPanel.add(othersPanel);
		fieldsPanel.add(cbPanel);
		
		personnalPanel.add(new JLabel("Votre nom :"));
		personnalPanel.add(nameField = new JTextField());
		personnalPanel.add(new JLabel("Votre prénom :"));
		personnalPanel.add(prenomField = new JTextField());
		personnalPanel.add(new JLabel("Votre date de naissance :"));
		personnalPanel.add(datePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)));
		
		datePanel.add(jourField = new JTextField(2));
		datePanel.add(moisField = new JTextField(2));
		datePanel.add(anneeField = new JTextField(4));
		
		othersPanel.add(new JLabel("Votre adresse : "));
		othersPanel.add(adresseField = new JTextField());
		othersPanel.add(new JLabel("Votre mail : "));
		othersPanel.add(mailField = new JTextField());
		
		cbPanel.add(new JLabel("Numéro de cb :"));
		cbPanel.add(numCBField = new JTextField());
		cbPanel.add(new JLabel("Mois :"));
		cbPanel.add(moisCBField = new JTextField(2));
		cbPanel.add(new JLabel("Année :"));
		cbPanel.add(anneeCBField = new JTextField(4));
		cbPanel.add(new JLabel("Cryptogramme :"));
		cbPanel.add(CryptoCBField = new JTextField());
		

		valideButton = new JButton();
		valideButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nom = nameField.getText();
				String prenom = prenomField.getText();
				String mail = mailField.getText();
				String annivMoisStr = moisField.getText();
				int annivMois = Integer.parseInt(annivMoisStr);
				String annivAnStr = anneeField.getText();
				int annivAn = Integer.parseInt(annivAnStr);
				String annivJourStr = jourField.getText();
				int annivJour = Integer.parseInt(annivJourStr);
				String adr = adresseField.getText();
				LocalDate dateAnniv = LocalDate.of(annivAn, annivMois, annivJour);
				String numCb = numCBField.getText();
				String crypto = CryptoCBField.getText();
				String monthStr = moisCBField.getText();
				int month = Integer.parseInt(monthStr);
				String yearStr = anneeCBField.getText();
				int year = Integer.parseInt(yearStr);
				LocalDate dateCb = LocalDate.of(year, month, 1);
				CarteBancaire cb = new CarteBancaire(numCb, crypto, dateCb);
				System.out.println(out.souscrire(cb, adr, nom, prenom, dateAnniv, mail));
			}
		});
	}

}
