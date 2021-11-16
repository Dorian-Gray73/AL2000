package fc.Test;

import java.util.ArrayList;

import fc.Film;

public interface FilmDao {
    public ArrayList<Film> chercher(String Nom);
    public ArrayList<Film> chercher();
    
    public String consulter(String titre);
    
    
}
