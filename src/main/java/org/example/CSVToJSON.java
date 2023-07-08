package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.example.book.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class CSVToJSON {

    public static void main(String[] args) {
        try {
            // Read the CSV file
            CSVReader csvReader = new CSVReader(new FileReader(CSVToJSON.class.getResource("/books_data.csv").getFile()));
            List<String[]> csvData = csvReader.readAll();
            csvReader.close();


            // Convert CSV data to JSON objects
            List<Book> books = new ArrayList<>();
            String[] header = csvData.get(0);
            for (int i = 1; i < csvData.size(); i++) {
                String[] row = csvData.get(i);
                Book book = new Book();
                for (int j = 0; j < row.length; j++) {
                    String key = header[j].trim();
                    String value = row[j].trim();
                    switch (key) {
                        case "Number":
                            book.setNumber(Integer.parseInt(value));
                            break;
                        case "Title":
                            book.setTitle(value);
                            break;
                        case "Author":
                            book.setAuthor(value);
                            break;
                        case "Genre":
                            book.setGenre(value);
                            break;
                        case "SubGenre":
                            book.setSubGenre(value);
                            break;
                        case "Publisher":
                            book.setPublisher(value);
                            break;
                    }
                }
                book.setAvailable(true);
                book.setLoanCount(0);
                books.add(book);
            }

            // Convert books to JSON string
           Gson gson = new GsonBuilder().setPrettyPrinting().create();
           String json = gson.toJson(books);

            // Write JSON data to a file
            FileWriter fileWriter = new FileWriter("books.json");
            fileWriter.write(json);
            fileWriter.close();

            System.out.println("Success!");

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }



}

// create user.json to store user information
// on user selection, add sign up or login
//1. User Signup, generate them a unique library number
//2. Enter lib number and password
//3. Scanner reads input
//4. Stores number and associated password by writing a new json file
//5. Updates file with information as an object with key/values
//6. When user then wants to login, details are available and so is their data, current books, loan history etc.




// allow user to return book










