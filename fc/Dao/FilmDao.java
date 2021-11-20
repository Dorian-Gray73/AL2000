package fc.Dao;

import java.util.ArrayList;
import java.util.HashMap;

import fc.Film;
/**
     * interface de gestion de la persistance des films.
     */
public interface FilmDao {
    /**
     * @param filtres filtre de recherche
     * @return ArrayList
     */
    public ArrayList<Film> chercher(HashMap<String, String> filtres);
    /**
     * Méthode de recherche d'une location en cours sur le film.
     *
     * @param titre Le titre du film recherché
     * @return Renvoie une chaine de caractère contenant les informations du(des) film(s)
     * trouver dans notre BD
     */
    public String consulter(String titre);
}
