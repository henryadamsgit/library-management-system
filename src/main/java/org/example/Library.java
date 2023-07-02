package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.book.Book;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Library {
    private static final String BOOKS_FILE = "books.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        System.out.println("Welcome to the Library!");
        System.out.println("Please enter '1' for User or '2' for Admin");

        Scanner userInput = new Scanner(System.in);
        int choice = userInput.nextInt();

        if (choice == 1) {
            // User operations
            System.out.println("Welcome, User!");
            // Implement user-related functionality here
            viewAllBooks();
        } else if (choice == 2) {
            // Admin operations
            System.out.println("Enter the admin password: ");
            String password = userInput.next();

            if (password.equals("abcdefg")) {
                System.out.println("Welcome, Admin!");
                // Implement admin-related functionality here
                viewAllBooks();
            } else {
                System.out.println("Incorrect password. Access denied");
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
            // Handle invalid choice scenario
        }
    }

    private static void viewAllBooks() {
        try (FileReader fileReader = new FileReader(BOOKS_FILE)) {
            Book[] booksArray = gson.fromJson(fileReader, Book[].class);
            List<Book> books = Arrays.asList(booksArray);

            System.out.println("All Books in the Library:");
            for (Book book : books) {
                System.out.println(book);
            }
        } catch (IOException e) {
            System.out.println("Error reading books file: " + e.getMessage());
        }
    }
}
