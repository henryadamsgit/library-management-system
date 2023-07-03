package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.admin.Admin;
import org.example.book.Book;
import org.example.user.User;

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
        System.out.println("Please enter '1' for User access OR '2' for Admin access");

        Scanner userInput = new Scanner(System.in);
        int choice = userInput.nextInt();

        if (choice == 1) {
            // User operations
            System.out.println("Welcome, User!");
            // Implement user-related functionality here
            User user = new User();
            user.userSelection();
        } else if (choice == 2) {
            // Admin operations
            System.out.println("Enter the admin password: ");
            String password = userInput.next();

            if (password.equals("abcdefg")) {
                System.out.println("Welcome, Admin!");
                // Implement admin-related functionality here
                Admin admin = new Admin();
                admin.adminSelection();
            } else {
                System.out.println("Incorrect password. Access denied");
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
            // Handle invalid choice scenario
        }
    }

    public static void viewAllBooks() {
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

    public void viewAvailableBooks() {
        // Implement the logic to view available books
        System.out.println("Viewing available books...");
    }

    public void viewBooksByCategory() {
        System.out.println("Please select a category:");
        System.out.println("1: Non-Fiction");
        System.out.println("2: Fiction");
        System.out.println("3: Tech");
        System.out.println("4: Science");
        System.out.println("5: History");
        System.out.println("6: Philosophy");

        Scanner userInput = new Scanner(System.in);
        int categoryOption = userInput.nextInt();

        switch (categoryOption) {
            case 1:
                // Non-Fiction
                getBooksByCategory("Non-Fiction");
                break;
            case 2:
                // Fiction
                getBooksByCategory("Fiction");
                break;
            case 3:
                // Tech
                getBooksByCategory("Tech");
                break;
            case 4:
                // Science
                getBooksByCategory("Science");
                break;
            case 5:
                // History
                getBooksByCategory("History");
                break;
            case 6:
                // Philosophy
                getBooksByCategory("Philosophy");
                break;
            default:
                System.out.println("Invalid category option. Please try again.");
                viewBooksByCategory();
                break;
        }
    }

    public void getBooksByCategory(String category) {
        try (FileReader fileReader = new FileReader(BOOKS_FILE)) {
            Book[] booksArray = gson.fromJson(fileReader, Book[].class);
            List<Book> books = Arrays.asList(booksArray);

            System.out.println("Books in the category \"" + category + "\":");
            for (Book book : books) {
                if (book.getGenre().equalsIgnoreCase(category)) {
                    System.out.println(book);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading books file: " + e.getMessage());
        }
    }


    public void viewBooksByCategory(String category) {
    }
}
