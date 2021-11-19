package fc;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

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

		CarteAbonnement ca = null;
		Client client = null;
		FacadeNf out = new FacadeNf();

		do {
			System.out.println("Les fonction portant la mention #adh sont réservé aux abonnés");
			System.out.println("veuillez taper 0 pour quitter");
			System.out.println("veuillez taper 1 pour vous créer un compte");
			System.out.println("veuillez taper 2 pour vous connecter #adh ");
			System.out.println("veuillez taper 3 pour rechercher un film");
			System.out.println("veuillez taper 4 pour emprunter un film");
			System.out.println("veuillez taper 5 pour créditer votre carte d'abonnement #adh ");
			System.out.println("veuillez taper 6 pour rendre un film");
			System.out.println("veuillez taper 7 pour consulter l'historique #adh ");
			System.out.println("veuillez taper 8 pour ajouter des restrictions #adh ");
			choix = erreurMenu(sc);
			switch (choix) {

			case CREER:
				System.out.println("Afin de souscrire un abonnement veuillez renseigner ces champs :");
				CarteBancaire cb = infoCb(sc);
				ca = infoAdherent(sc, out, cb, client);
				System.out.println("votre compte est bien créer, merci d utiliser AL2000");
				System.out.println("si vous souhaitez emprunter directement pensez à vous connecter en tapant 2 dans le menu");
				break;

			case CONNECTER:
				try {
					out.connexion(ca);
					System.out.println("Ravi de vous revoir !");
					// client = null; //On garde un client et une carte de abonnement ?
				} catch (AbonnementNonReconnusException e) {
					System.out.println("l'abonné n'est pas reconnu");
					e.printStackTrace();
				}
				break;

			case RECHERCHER:
				String titre = texteValide("Veuillez rentrer le titre du Film que vous recherchez: ", sc);
				HashMap<Film, List<CD>> resultat = out.rechercherFilm(titre);
				for (Map.Entry<Film, List<CD>> map : resultat.entrySet()) {
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
				if (ca != null) {
					empruntAdh(sc, out);
				} else {
                    empruntClient(sc, out);
				}
                System.out.println("Votre location à été bien générer");
				break;

			case CREDITER:
				System.out.println("Votre solde est de : " + out.getSolde());
				double montant = doubleValide("Veuillez entrer le montant à créditer :", sc);
				out.crediterCarte(montant);
				System.out.println("Votre nouveau montant est de " + out.getSolde());
				break;

			case RENDRE:
				int codeLocation = intValide("Veuillez rentrer votre code de location: ", sc);
				int endommage = intValide("Veuillez rentrer la valeur 1 si le CD est endommagé, 0 sinon", sc);
				CD cd = cdEmprunter;
				if (endommage == 1) {
					try {
						out.rendre(codeLocation, cd, true);
					} catch (ErreurRenduException e) {
						System.out.println("Une erreur est survenu veuillez nous excuser");
						e.printStackTrace();
					}
				} else {
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
				if (histo.equals(null)) {
					System.out.println("vous n'avez encore rien emprunté :'( ");
				} else {
					for (int i = 0; i < histo.size(); i++) {
						Location loc = histo.get(i);
						System.out.println(loc.toString());
					}
				}
				break;

			case RESTRICTION:
				System.out.println();
				int menu = intValide(
						"Si vous voulez supprimer les restrictions taper 1 ou n'importe quel autre chiffre pour en ajouter",
						sc);
				if (menu == 1) {
					String[] restrVide = {};
					ca.setRestriction(restrVide);
				} else {
					int nbRestr = intValide("Veuillez indiquer le nombre de restriction à ajouter: ", sc);
					String[] restriction = new String[nbRestr];
					for (int i = 0; i < nbRestr; i++) {
						String restrictionStr = texteValide("Veuillez rentrer votre restriction", sc);
						restriction[i] = restrictionStr;
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

		} while (choix != 0);
		System.out.println("merci d'avoir utilisé l'AL2000");
		sc.close();
	}

	private static int erreurMenu(Scanner sc) {
		int choix;
		boolean truc;
		do {
			try {
				choix = sc.nextInt();
				truc = false;
			} catch (Exception e) {
				System.out.println("Veuillez entrer une valeur correct !");
				sc.nextLine();
				truc = true;
				choix = 0;
			}
		} while (truc);
		return choix;
	}

	private static void empruntAdh(Scanner sc, FacadeNf out) {
		String titreLoc = texteValide("Veuillez rentrer le titre du Film que vous recherchez: ", sc);
		System.out.println("titre recup : " + titreLoc);

		HashMap<Film, List<CD>> resultatLoc = out.rechercherFilm(titreLoc);
		Set<Film> filmChoisie = resultatLoc.keySet();
		Iterator<Film> it = filmChoisie.iterator();
		Film choixFilm = it.next();
		System.out.println("Liste de cd  : " + resultatLoc.get(choixFilm));
		if (resultatLoc.get(choixFilm) == null) {
			System.out.println("Vous ne pouvez louer ce film que sous forme de QRCode");
			locationAdhQr(out, choixFilm);
		} else {
			int choixType = intValide("taper 1 pour avoir un Qr code ou n'importe quel autre chiffre pour avoir un CD",
					sc);
			if (choixType == 1) {
				locationAdhQr(out, choixFilm);
			} else {
				locationAdhCD(out, resultatLoc, choixFilm);

			}
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
			System.out.println("voici votre numéro de location ne le perdez pas");
			System.out.println(out.emprunt(cdlouer));
			cdEmprunter = cdlouer;
		} catch (ErreurEmpruntException e) {
			System.out.println("Une erreur lors de votre emprunt est survenu");
			e.printStackTrace();
		}
	}

	private static void empruntClient(Scanner sc, FacadeNf out) {
		CarteBancaire cbClient = infoCb(sc);

		String adr = texteValide("Veuillez rentrer votre addresse sur une seul ligne: ", sc);
		String titreLoc = texteValide("Veuillez rentrer le titre du Film que vous recherchez: ", sc);

		HashMap<Film, List<CD>> resultatLoc = out.rechercherFilm(titreLoc);
		Set<Film> filmChoisie = resultatLoc.keySet();
		Iterator<Film> it = filmChoisie.iterator();
		Film choixFilm = it.next();

		if (resultatLoc.get(choixFilm) == null) {
			System.out.println("Vous ne pouvez louer ce film que sous forme de QRCode");
			locationQr(out, cbClient, adr, choixFilm);
		} else {
			int choixType = intValide("taper 1 pour avoir un Qr code ou n'importe quel autre chiffre pour avoir un CD",
					sc);
			if (choixType == 1) {
				locationQr(out, cbClient, adr, choixFilm);
			} else {
				locationCd(out, cbClient, adr, resultatLoc, choixFilm);
			}

		}

	}

	private static void locationCd(FacadeNf out, CarteBancaire cbClient, String adr,
			HashMap<Film, List<CD>> resultatLoc, Film choixFilm) {
		List<CD> cdalouer = resultatLoc.get(choixFilm);
		CD cdlouer = cdalouer.get(0);
		try {
			System.out.println("voici votre numéro de location ne le perdez pas");
			System.out.println(out.emprunt(cbClient, adr, cdlouer));
			cdEmprunter = cdlouer;
		} catch (ErreurEmpruntException e) {
			System.out.println("Une erreur lors de votre emprunt est survenu");
			e.printStackTrace();
		}
	}

	private static int intValide(String commentaire, Scanner sc) {
		int choix;
		boolean truc;
		System.out.println(commentaire);
		do {
			try {
				choix = sc.nextInt();
				sc.nextLine();
				truc = false;
			} catch (Exception e) {
				System.out.println("Veuillez entrer une valeur correct !\n" + commentaire);
				sc.nextLine();
				sc.nextLine();
				truc = true;
				choix = 0;
			}
		} while (truc);
		return choix;
	}

	private static double doubleValide(String commentaire, Scanner sc) {
		double choix;
		boolean truc;
		System.out.println(commentaire);
		do {
			try {
				choix = sc.nextDouble();
				sc.nextLine();
				truc = false;
			} catch (Exception e) {
				System.out.println("Veuillez entrer une valeur correct !\n" + commentaire);
				sc.nextLine();
				sc.nextLine();
				truc = true;
				choix = 0;
			}
		} while (truc);
		return choix;
	}

	private static String texteValide(String commentaire, Scanner sc) {
		String texte;
		boolean truc;
		System.out.println(commentaire);
		do {
			try {
				texte = sc.nextLine();
				truc = false;
				if (texte.equals("")) {
					truc = true;
					System.out.println("Veuillez entrer une chaine de caractère non nulle !\n" + commentaire);
				}
			} catch (Exception e) {
				sc.nextLine();
				truc = true;
				texte = "";
			}
		} while (truc);
		return texte;
	}

	private static void locationQr(FacadeNf out, CarteBancaire cbClient, String adr, Film choixFilm) {
		QRCode qr = new QRCode(choixFilm);
		try {
			out.emprunt(cbClient, adr, qr);
		} catch (ErreurEmpruntException e) {
			System.out.println("Une erreur lors de votre emprunt est survenu");
			e.printStackTrace();
		}
	}

	private static CarteAbonnement infoAdherent(Scanner sc, FacadeNf out, CarteBancaire cb, Client client) {
		CarteAbonnement ca;
		String adr = texteValide("veuillez entrer votre adresse de facturation sur une seul ligne", sc);
		String nom = texteValide("veuillez donner votre nom", sc);
		String prenom = texteValide("veuillez donner votre prénom", sc);
		String email = texteValide("veuillez entrer votre adresse email", sc);
		int j = intValide("Veuillez entrer votre jour de naissance", sc);
		int m = intValide("Veuillez entrer votre mois de naissance", sc);
		int a = intValide("Veuillez entrer votre année de naissance", sc);
		LocalDate anniv = LocalDate.of(a, m, j);
		client = new Client(adr, cb);
		ca = out.souscrire(cb, adr, nom, prenom, anniv, email);
		return ca;
	}

	private static CarteBancaire infoCb(Scanner sc) {
		int noCbInt = intValide("veuillez renseigner votre numéro de carte", sc);
		String noCb = "" + noCbInt;
		int cryptoInt = intValide("veuillez renseigner votre cryptogramme visuel", sc);
		String crypto = "" + cryptoInt;
		int month = intValide("veuillez renseigner votre mois d expiration", sc);
		int year = intValide("veuillez renseigner votre année d expiration", sc);
		LocalDate date = LocalDate.of(year, month, 1);
		CarteBancaire cb = new CarteBancaire(noCb, crypto, date);
		return cb;
	}
}
