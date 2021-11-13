package fc.Test;

import java.sql.*;

import fc.Film;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmDaoImp implements FilmDao {
	Connection conn = null;
	static final String CONN_URL = "jdbc:sqlite:BASE.db";

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

	public static void main(String args[]) {
		FilmDaoImp a = new FilmDaoImp();
		a.chercher("ted2");
	}

	@Override
	public String consulter(String titre) {
		// TODO Auto-generated method stub
		return null;
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
