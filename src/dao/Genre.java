/*
 * © 2016 DHAVAL DOSHI  ALL RIGHTS RESERVED
 */

package dao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dhaval on 9/14/2016.
 * This class is used to store genre and keywords associated with it
 */
public class Genre {

    private String genereName;
    private Map<String, Integer> keywords;

    public Genre() {
        keywords = new HashMap<>();
    }

    public String getGenereName() {
        return genereName;
    }

    public void setGenereName(String genereName) {
        this.genereName = genereName;
    }

    //This method returns a map consisting of keywords associated with the particular genre
    public Map<String, Integer> getKeywords() {
        return keywords;
    }

    public void setKeywords(Map<String, Integer> keywords) {
        this.keywords = keywords;
    }
}
