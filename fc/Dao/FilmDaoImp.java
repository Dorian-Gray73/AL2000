package fc.Dao;

import java.sql.*;

import fc.Film;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class FilmDaoImp implements FilmDao {
	Connection conn = null;
	static final String CONN_URL = "jdbc:sqlite:BASE.db";
	
	
	/**
	 * @param titre Le titre du film recherché
	 * @return Renvoie l'instance ou les instances du des film(s)
	 * trouver dans notre BD
	 */
	@Override
	public ArrayList<Film> chercher(String nom) {
		try {
			ArrayList<Film> f = new ArrayList<>();
			conn = DriverManager.getConnection(CONN_URL);
			conn.setAutoCommit(false);
			System.out.println("Autocommit disabled");

			// Changing isolation level
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			System.out.println("Isolation level changed");

			PreparedStatement getFilms = conn.prepareStatement("select * from FILM where titre=?");
			getFilms.setString(1, nom);

			ResultSet res = getFilms.executeQuery();

			while (res.next()) {
				String nomf = res.getString("titre");
				String nomR = res.getString("producteur");
				String nomA = res.getString("acteursprincipaux");
				String resume = res.getString("resume");

				System.out.println(nomf + " " + nomR + " " + nomA + " " + resume);
				f.add(new Film(nomf, nomR, resume, nomA));
			}

			return f;
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
	/**
	 * Méthode de recherche d'une location en cours sur le film.
	 * 
	 * @param titre Le titre du film recherché
	 * @return Renvoie une chaine de caractère contenant les informations du(des) film(s)
	 * trouver dans notre BD
	 */
	@Override
	public String consulter(String titre) {
		ArrayList<Film> list = this.chercher(titre);
		Iterator<Film> it = list.iterator();
		String res = "";
		while (it.hasNext()) {
			res += it.next().toString() + "\n";
		}
		return res;
	}

	@Override
	public ArrayList<Film> chercher() {
		try {
			ArrayList<Film> f = new ArrayList<>();
			conn = DriverManager.getConnection(CONN_URL);
			conn.setAutoCommit(false);
			System.out.println("Autocommit disabled");

			// Changing isolation level
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			System.out.println("Isolation level changed");

			PreparedStatement getFilms = conn.prepareStatement("select * from FILM");

			ResultSet res = getFilms.executeQuery();

			while (res.next()) {
				String nomf = res.getString("titre");
				String nomR = res.getString("producteur");
				String nomA = res.getString("acteursprincipaux");
				String resume = res.getString("resume");

				System.out.println(nomf + " " + nomR + " " + nomA + " " + resume);
				f.add(new Film(nomf, nomR, resume, nomA));
			}

			return f;
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

}
