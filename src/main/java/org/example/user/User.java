package org.example.user;

import org.example.JSONFileHandler;
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
import static org.example.user.SignUpLogin.USERS_FILE;

public class User {

    public static void main(String[] args) {
        Library library = new Library();
        List<Book> allBooks = library.getAllBooks();

        User user = new User();
        user.setLoanedBooks(allBooks);
        user.userSelection();
    }

    private String password;
    private long libraryNumber;
    private List<Book> booksBorrowed;

    private static List<Book> loanedBooks;

    public User() {
        this.booksBorrowed = new ArrayList<>();
    }

    public User(long libraryNumber, String password) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.booksBorrowed = new ArrayList<>();
    }

    public List<Book> getBooksBorrowed(long libraryNumber) {
        return booksBorrowed;
    }

    public void setBooksBorrowed(List<Book> booksBorrowed) {
        this.booksBorrowed = booksBorrowed;
    }


    public static List<Book> getLoanedBooks() {
        return loanedBooks;
    }
    public void setLoanedBooks(List<Book> loanedBooks) {
        this.loanedBooks = loanedBooks;
    }


    public long getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public void addBookBorrowed(Book book) {
        booksBorrowed.add(book);
    }

    public void removeBookBorrowed(Book book) {
        booksBorrowed.remove(book);
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
                library.viewUnavailableBooks();
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

                            // Add the loaned book to the user's booksBorrowed list
                            booksBorrowed.add(book);

                            // Update the user's booksBorrowed list in the JSON file
                            saveUserBooks();

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

                            // Remove the returned book from the user's booksBorrowed list
                            booksBorrowed.remove(book);

                            // Update the user's booksBorrowed list in the JSON file
                            saveUserBooks();

                            justBrowsing();
                        } catch (IOException e) {
                            System.out.println("Error updating book data: " + e.getMessage());
                        }
                    } else {
                        System.out.println("SORRY! This book is not out on loan, so it can't be returned!");
                        returnBook();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading books file: " + e.getMessage());
        }
    }

    private void saveUserBooks() {
        try {
            JSONFileHandler<User> jsonFileHandler = new JSONFileHandler<>(USERS_FILE, User.class);
            List<User> users = jsonFileHandler.getObjects();

            for (int i = 0; i < users.size(); i++) {
                User currentUser = users.get(i);
                if (currentUser.getLibraryNumber() == libraryNumber) {
                    // Update the user's booksBorrowed list
                    users.set(i, this);
                    jsonFileHandler.saveObjects(users);
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Error saving user books: " + e.getMessage());
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