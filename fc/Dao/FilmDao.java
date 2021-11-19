package fc.Dao;

import java.util.ArrayList;
import java.util.HashMap;

import fc.Film;

public interface FilmDao {
    public ArrayList<Film> chercher(HashMap<String, String> filtres);
    
    public String consulter(String titre);
}
