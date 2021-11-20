package fc.Dao;

import java.sql.*;

import fc.Film;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class FilmDaoImp implements FilmDao {
	Connection conn = null;
	static final String CONN_URL = "jdbc:sqlite:BASE.db";


	@Override
	public ArrayList<Film> chercher(HashMap<String, String> filtres) {
		try {
			conn = DriverManager.getConnection(CONN_URL);
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			String sql = "SELECT f.id, f.titre, g.nom, f.resume, act.nom, act.prenom, real.nom, real.prenom, prod.nom, prod.prenom " +
					"FROM Film f " +
					"JOIN Genre g ON f.idGenre = g.id " +
					"JOIN Acteur a ON f.id = a.idFilm " +
					"JOIN Personne act ON act.id = a.idPersonne " +
					"JOIN Personne real ON f.idRealisateur = real.id " +
					"JOIN Personne prod ON f.idProducteur = prod.id";

			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet res = statement.executeQuery();

			ArrayList<Film> listeFilms = new ArrayList<>();

			while (res.next()) {
				boolean filtresSatisfaits = true;

				String filtreTitre = filtres.get("titre");
				String filtreGenre = filtres.get("genre");
				String filtreNomActeur = filtres.get("nomActeur");
				String filtrePrenomActeur = filtres.get("prenomActeur");

				int id = res.getInt(1);
				String titre = res.getString(2);
				String genre = res.getString(3);
				String resume = res.getString(4);
				String nomActeur = res.getString(5);
				String prenomActeur = res.getString(6);
				String nomReal = res.getString(7);
				String prenomReal = res.getString(8);
				String nomProd = res.getString(9);
				String prenomProd = res.getString(10);

				if (filtreTitre != null && !filtreTitre.equals(titre)) {
					filtresSatisfaits = false;
				}

				if (filtreGenre != null && !filtreGenre.equals(genre)) {
					filtresSatisfaits = false;
				}

				if (filtreNomActeur != null && !filtreNomActeur.equals(nomActeur)) {
					filtresSatisfaits = false;
				}

				if (filtrePrenomActeur != null && !filtrePrenomActeur.equals(prenomActeur)) {
					filtresSatisfaits = false;
				}

				if (filtresSatisfaits) {
					Film film = new Film(titre, genre);
					if (!listeFilms.contains(film)) {
						film.setResume(resume);
						film.setRealisateur(nomReal, prenomReal);
						film.setProducteur(nomProd, prenomProd);
						ArrayList<String> acteurs = recupererActeurs(id);
						if (acteurs != null) {
							for (String acteur : acteurs) {
								film.ajouterActeur(acteur);
							}
						}
						listeFilms.add(film);
					}
				}
			}

			statement.close();
			conn.commit();
			return listeFilms;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return null;
	}

	public static void main(String args[]) {
		FilmDaoImp a = new FilmDaoImp();
		HashMap<String, String> map = new HashMap<>();
		//map.put("titre", "Mourir peut attendre");
		//map.put("genre", "Espionnage");
		//map.put("nomActeur", "Craig");
		//map.put("prenomActeur", "Léa");

		ArrayList<Film> films = a.chercher(map);
		for (Film film : films) {
			System.out.println(film);
		}
	}

	/**
	 * Méthode de recherche d'une location en cours sur le film.
	 *
	 * @param titre Le titre du film recherché
	 * @return Renvoie une chaine de caractère contenant les informations du(des) film(s)
	 * trouver dans notre BD
	 */
	@Override
	public String consulter(String titre) {
		HashMap<String, String> filtres = new HashMap<String, String>();
		filtres.put("titre", titre);
		ArrayList<Film> list = this.chercher(filtres);
		Iterator<Film> it = list.iterator();
		String res = "";
		while (it.hasNext()) {
			res += it.next().toString() + "\n";
		}
		return res;
	}

	private ArrayList<String> recupererActeurs(int id) {
		try {
			conn = DriverManager.getConnection(CONN_URL);
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			String sql = "SELECT nom, prenom " +
					"FROM Personne p " +
					"JOIN Acteur a ON p.id = a.idPersonne " +
					"WHERE idFilm = ? ";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet res = statement.executeQuery();

			ArrayList<String> listeActeurs = new ArrayList<String>();
			while (res.next()) {
				listeActeurs.add(res.getString(1) + " " + res.getString(2));
			}

			statement.close();
			conn.commit();
			return listeActeurs;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
