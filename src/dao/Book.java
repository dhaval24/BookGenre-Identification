/*
 * © 2016 DHAVAL DOSHI  ALL RIGHTS RESERVED
 */

package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dhaval on 9/14/2016.
 * This is the main class and each of its object represent a Book and its associated data
 * This class contains various methods, core processing is performed in processDescription method which
 * takes of helper methods getAverageScore() to calculate the averageScore of the keywords associated with each genere
 * in particular description and getBigrams() generates the bigrams of the text description.
 */
public class Book {

    private String title;
    private String description;
    private Map<String, List<Integer>> map; // Map to maintain the genre along with the score of associated keywords encountered
    private Map<String, Integer> unsortedMap; // Map which stores the genre and its associated score however is unsorted
    private Map<String, Integer> wordCountMap; // Map which stores the genre and total number of individual words in keywords list
    // as a keyword may comprise of more than 1 word.
    private List<Map.Entry<String, Integer>> topList; //List which maintains the top genre and associated score

    public List<Map.Entry<String, Integer>> getTopList() {
        return topList;
    }

    public Book(String title, String description) {

        this.title = title;
        this.description = description;
        map = new HashMap<>();
        unsortedMap = new HashMap<>();
        wordCountMap = new HashMap<>();

    }

    public String getTitle() {
        return title;
    }

    public Map<String, List<Integer>> getMap() {
        return map;
    }

    public String getDescription() {
        return description;
    }


    /**
     * This method processes the description to generate a list which consist of genre and its associated calculated
     * score in decreasing order
     */
    public void processDescription() {

        String[] words = description.replaceAll("[^a-zA-Z- ]", "").replaceAll("[-]", " ").toLowerCase().split("\\s+"); //Remove all punctuations
        // and convert all to lower case
        List<String> biGramWords = getBigrams(words); //Bigrams list of description
        //System.out.println(biGramWords);
        //System.out.println(Arrays.toString(words));
        List<Genre> genreList = GenereCollection.getGenreCollectionList();

        //Match bigrams
        for (String word : biGramWords) {

            for (Genre genre : genreList) {

                if (genre.getKeywords().containsKey(word)) {
                    if (!map.containsKey(genre.getGenereName())) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(genre.getKeywords().get(word));
                        map.put(genre.getGenereName(), temp);
                        wordCountMap.put(genre.getGenereName(), 2);
                    } else {
                        List<Integer> temp = map.get(genre.getGenereName());
                        temp.add(genre.getKeywords().get(word));
                        map.put(genre.getGenereName(), temp);
                        wordCountMap.put(genre.getGenereName(), wordCountMap.get(genre.getGenereName()) + 1);
                    }

                }
            }

        }


        //Match Unigrams

        for (String word : words) {

            for (Genre genre : genreList) {

                if (genre.getKeywords().containsKey(word)) {
                    if (!map.containsKey(genre.getGenereName())) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(genre.getKeywords().get(word));
                        map.put(genre.getGenereName(), temp);
                        wordCountMap.put(genre.getGenereName(), 2);
                    } else {
                        List<Integer> temp = map.get(genre.getGenereName());
                        temp.add(genre.getKeywords().get(word));
                        map.put(genre.getGenereName(), temp);
                        wordCountMap.put(genre.getGenereName(), wordCountMap.get(genre.getGenereName()) + 1);
                    }


                }
            }

        }

        //Populate unsorted Map with genre and its averge score
        for (Map.Entry entry : map.entrySet()) {

            List<Integer> temp = map.get(entry.getKey());
            int size = temp.size();
            int avgScore = getAverageSore(temp, size);
            unsortedMap.put((String) entry.getKey(), avgScore * wordCountMap.get(entry.getKey())); //put entry into unsorted map
            //Average score is multiplied with total word count

        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(unsortedMap.entrySet());
        Collections.sort(entryList, (o1, o2) -> Integer.compare(o2.getValue(), o1.getValue())); //custom sorting entryset based on value
        topList = new ArrayList<>(entryList); //assigning the value to topList
        //System.out.println(entryList);
    }

    //This method is used to calculate the average of all the integers in the list
    private int getAverageSore(List<Integer> temp, int size) {
        int sum = 0;
        for (int A : temp) {
            sum += A;
        }
        return (sum / size);
    }

    //This method is used to generate bigrams from the array of words
    private List<String> getBigrams(String[] arr) {

        List<String> result = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            String temp = arr[i] + " " + arr[i + 1];
            result.add(temp);
        }
        return result;
    }

}
