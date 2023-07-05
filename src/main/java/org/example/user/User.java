package org.example.user;

import org.example.Library;
import org.example.book.Book;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.Library.BOOKS_FILE;
import static org.example.Library.gson;

public class User {

    private String email;
    private String password;
    private String name;
    private long libraryNumber;
    private Book booksBorrowed;

    private static List<Book> loanedBooks;

    public void setLoanedBooks(List<Book> loanedBooks) {
        this.loanedBooks = loanedBooks;
    }

    public static void main(String[] args) {
        Library library = new Library();
        List<Book> allBooks = library.getAllBooks(); // Get all books in the library

        User user = new User();
        user.setLoanedBooks(allBooks); // Set all books as loaned books
        user.userSelection();
    }

    public void userSelection() {
        System.out.println("What would you like to do today?");
        System.out.println("1: View the collection of books");
        System.out.println("2: Take a book out on loan");
        System.out.println("3: Return a book");
        System.out.println("4: Just browsing...");
        System.out.println("5: Leave library");
        System.out.println("Please enter your action (1-5):");

        Scanner userInput = new Scanner(System.in);
        int action = userInput.nextInt();

        switch (action) {
            case 1:
                viewBooks();
                break;
            case 2:
                loanBook();
                break;
            case 3:
                returnBook();
            case 4:
                justBrowsing();
                break;
            case 5:
                leaveLibrary();
                break;
            default:
                System.out.println("Invalid action. Please try again.");
                userSelection();
                break;
        }
    }


    private void viewBooks() {
        System.out.println("Welcome to our collection, please select a viewing option:");
        System.out.println("1: View all books");
        System.out.println("2: View unavailable books (out on loan)");
        System.out.println("3: View books by category");

        Scanner userInput = new Scanner(System.in);
        int option = userInput.nextInt();

        Library library = new Library();

        switch (option) {
            case 1:
                library.viewAllBooks();
                break;
            case 2:
                library.viewAvailableBooks();
                break;
            case 3:
                library.viewBooksByCategory();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                viewBooks();
                break;
        }
    }

    private void loanBook() {
        System.out.println("Enter the ID of the book you want to loan (1-120):");
        Scanner userInput = new Scanner(System.in);
        int bookId = userInput.nextInt();

        try (FileReader fileReader = new FileReader(BOOKS_FILE)) {
            Book[] booksArray = gson.fromJson(fileReader, Book[].class);
            List<Book> books = new ArrayList<>(Arrays.asList(booksArray));

            for (Book book : books) {
                if (book.getNumber() == bookId) {
                    if (book.isAvailable()) {
                        System.out.println("You have successfully loaned the book:");
                        System.out.println(book);
                        book.setAvailable(false);
                        book.incrementLoanCount();

                        // Update the book data in the JSON file
                        try (FileWriter fileWriter = new FileWriter(BOOKS_FILE)) {
                            gson.toJson(books, fileWriter);
                            justBrowsing();
                        } catch (IOException e) {
                            System.out.println("Error updating book data: " + e.getMessage());
                        }
                    } else {
                        System.out.println("SORRY! This book is already out on loan.");
                        loanBook();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading books file: " + e.getMessage());
        }
    }

    private void returnBook() {
        System.out.println("Enter the ID of the book you want to return (1-120):");
        Scanner userInput = new Scanner(System.in);
        int bookId = userInput.nextInt();

        try (FileReader fileReader = new FileReader(BOOKS_FILE)) {
            Book[] booksArray = gson.fromJson(fileReader, Book[].class);
            List<Book> books = new ArrayList<>(Arrays.asList(booksArray));

            for (Book book : books) {
                if (book.getNumber() == bookId) {
                    if (!book.isAvailable()) {
                        System.out.println("You have successfully returned the book:");
                        System.out.println(book);
                        book.setAvailable(true);

                        // Update the book data in the JSON file
                        try (FileWriter fileWriter = new FileWriter(BOOKS_FILE)) {
                            gson.toJson(books, fileWriter);
                            justBrowsing();
                        } catch (IOException e) {
                            System.out.println("Error updating book data: " + e.getMessage());
                        }
                    } else {
                        System.out.println("SORRY! This book is not on loan.");
                        returnBook();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading books file: " + e.getMessage());
        }
    }


    public void justBrowsing() {
        System.out.println("Feel free to browse the library!");
        System.out.println("Would you like to do anything else? (Y/N)");

        Scanner userInput = new Scanner(System.in);
        String choice = userInput.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            userSelection();
        } else if (choice.equalsIgnoreCase("N")) {
            leaveLibrary();
        } else {
            System.out.println("Invalid choice. Please try again.");
            justBrowsing();
        }
    }

    public void leaveLibrary() {
        System.out.println("Leaving the library...");
        System.out.println("We hope to see you again");
    }
}