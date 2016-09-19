/*
 * © 2016 DHAVAL DOSHI  ALL RIGHTS RESERVED
 */

package dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dhaval on 9/14/2016.
 * This class is used to store collection of Genre and method to search
 * the list of Genre
 */
public class GenereCollection {

    private static List<Genre> genreCollectionList = new ArrayList<>();

    //This method adds a Genre to the collection
    public static void addGenere(Genre genre) {
        genreCollectionList.add(genre);
    }

    //This method returns a collection of Genre
    public static List<Genre> getGenreCollectionList() {
        return genreCollectionList;
    }

    // This method is used to search the Genre list
    public static Genre getGenere(String genere) {
        for (Genre g : genreCollectionList) {
            if (g.getGenereName().equals(genere)) {
                return g;
            }
        }
        return null;
    }
}
