/*
 * © 2016 DHAVAL DOSHI  ALL RIGHTS RESERVED
 */

import dao.Book;
import dao.BookCollection;
import dao.Genre;
import dao.GenereCollection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Dhaval on 9/14/2016.
 * This is the Driver class of the Project.
 * It contins two static methods, method 1 parses the JSON file BookJson.txt and creates various data structures
 * viz BookCollection which consist of All book objects
 * <p>
 * The second method parses the Keywords CSV file to generate Genre and GenereCollection data structures
 * <p>
 * Main method processes all the books and produces the ouput file
 */

public class InputParser {

    /**
     * Method to parse the JSON Book file, initialize BooksCollection Datastructure
     *
     * @param fileName
     */
    public static void parseBooksJson(String fileName) {

        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(new FileReader(fileName));
            JSONArray jsonArray = (JSONArray) obj;
            Iterator<JSONObject> iterator = jsonArray.iterator();

            while (iterator.hasNext()) {

                JSONObject temp = iterator.next();
                Book book = new Book((String) temp.get("title"), (String) temp.get("description"));
                BookCollection.addBook(book);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method parses the keywords.csv file and initializes Genre and GenereCollection Datastructures
     *
     * @param fileName
     */
    public static void parseGenreKeywordsCSV(String fileName) {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            Set<String> set = new HashSet<>(); // To check if Genre is already created
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue; //Skip header
                }
                String[] strArray = line.split(",");
                if (set.add(strArray[0])) { // If Genre not created, create a new genre and add it to genre collections
                    Genre genre = new Genre();
                    genre.setGenereName(strArray[0]);
                    genre.getKeywords().put(strArray[1].trim(), Integer.valueOf(strArray[2].trim()));
                    GenereCollection.addGenere(genre);
                } else {
                    Genre genre = GenereCollection.getGenere(strArray[0]); //retrieve genre from genre collections
                    genre.getKeywords().put(strArray[1].trim(), Integer.valueOf(strArray[2].trim())); //update genre keywords
                }

            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        InputParser.parseBooksJson(args[0]);
        InputParser.parseGenreKeywordsCSV(args[1]);
        for (Book book : BookCollection.getBooksList()) {
            book.processDescription(); // process each book
        }
        BookCollection.writeBookList(args[2]); //Print output

    }
}
