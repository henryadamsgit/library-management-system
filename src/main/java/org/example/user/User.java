package org.example.user;

import org.example.Library;
import java.util.Scanner;

public class User {

    private String email;
    private String password;
    private String name;

    public static void main(String[] args) {
        Library library = new Library();
        library.viewAllBooks();

        User user = new User();
        user.userSelection();
    }

    public void userSelection() {
        System.out.println("What would you like to do today?");
        System.out.println("1: View our collection of books");
        System.out.println("2: Take a book out on loan");
        System.out.println("3: Just browsing...");
        System.out.println("4: Leave library");
        System.out.println("Please enter your action (1-4):");

        Scanner userInput = new Scanner(System.in);
        int action = userInput.nextInt();

        switch (action) {
            case 1:
                // View books
                viewBooks();
                break;
            case 2:
                // Take a book out on loan
                loanBook();
                break;
            case 3:
                // Just browsing
                justBrowsing();
                break;
            case 4:
                // Leave library
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
        System.out.println("2: View available books (not on loan)");
        System.out.println("3: View books by category");

        Scanner userInput = new Scanner(System.in);
        int option = userInput.nextInt();

        Library library = new Library();

        switch (option) {
            case 1:
                // View all books
                library.viewAllBooks();
                break;
            case 2:
                // View available books
              //  library.viewAvailableBooks();
                break;
            case 3:
                // View books by category
                library.viewBooksByCategory();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                viewBooks();
                break;
        }
    }

    private void loanBook() {
        // Implement the logic for loaning a book
        System.out.println("Loaning a book...");
    }

    private void justBrowsing() {
        System.out.println("Okay, enjoy the library!");
        System.out.println("If you need assistance, just press enter.");
        Scanner userInput = new Scanner(System.in);
        userInput.nextLine();
        userSelection();
    }

    private void leaveLibrary() {
        // Implement the logic for leaving the library
        System.out.println("Leaving the library...");
    }
}
