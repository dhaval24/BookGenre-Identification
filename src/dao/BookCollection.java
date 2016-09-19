/*
 * © 2016 DHAVAL DOSHI  ALL RIGHTS RESERVED
 */

package dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Dhaval on 9/14/2016.
 * This class maintains the Collection of Books and contains method which prints
 * the output
 */
public class BookCollection {

    private static List<Book> booksList = new ArrayList<>();

    public static Book addBook(Book book) { // Add book to the Collection
        booksList.add(book);
        return book;
    }

    //This method returns collection of books
    public static List<Book> getBooksList() {
        return booksList;
    }

    //This method is used to write the final output to the file

    public static void writeBookList(String OUTPUTFILE) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUTFILE))) {

            for (Book book : booksList) {

                writer.write(book.getTitle());
                writer.newLine();
                List<Map.Entry<String, Integer>> entryList = book.getTopList();
                for (int i = 0; i < entryList.size() && i < 3; i++) {

                    writer.newLine();
                    Map.Entry<String, Integer> currentEntry = entryList.get(i);
                    writer.write(currentEntry.getKey() + ",");
                    writer.write(" " + currentEntry.getValue());

                }

                writer.newLine();
                writer.newLine();
            }
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
