package org.example.admin;

import org.example.Library;
import org.example.book.Book;
import org.example.user.User;

import java.util.List;
import java.util.Scanner;

public class Admin {

    public static void main(String[] args) {

    }

    public void adminSelection() {
        System.out.println("What would you like to do today?");
        System.out.println("1: View all users");
        System.out.println("2: Run report");
        System.out.println("3: Leave library");
        System.out.println("Please enter your action 1-3");


        Scanner userInput = new Scanner(System.in);
        int action = userInput.nextInt();

        switch (action) {
            case 1:
                viewUsers();
                break;
            case 2:
                runReport();
                break;
            case 3:
                leaveLibrary();
                break;
            default:
                System.out.println("Invalid action. Please try again.");
                adminSelection();
                break;
        }

    }

    private void leaveLibrary() {
        User user = new User();
        user.leaveLibrary();
    }

    private void runReport() {
        Library library = new Library();
        List<Book> booksOnLoan = library.getBooksOnLoan();

        if (booksOnLoan.isEmpty()) {
            System.out.println("No books are currently on loan.");
            nextAction();
        } else {
            System.out.println("All books currently on loan:");
            for (Book book : booksOnLoan) {
                System.out.println(book);

            }
        }
        nextAction();
    }

    private void viewUsers() {
        System.out.println("Viewing all users:");

//        try {
//            JSONFileHandler<User> jsonFileHandler = new JSONFileHandler<>(USERS_FILE, User.class);
//            List<User> users = jsonFileHandler.getObjects();
//
//            for (User user : users) {
//                System.out.println("Library Number: " + user.getLibraryNumber());
//                System.out.println("Password: " + user.getPassword());
//
//                List<Book> borrowedBooks = getBooksBorrowed(user.getLibraryNumber());
//                if (!booksBorrowed.isEmpty()) {
//                    System.out.println("Borrowed Books:");
//                    for (Book book : borrowedBooks) {
//                        System.out.println(book);
//                    }
//                } else {
//                    System.out.println("No books currently borrowed.");
//                }
//
//                System.out.println();
//            }
//        } catch (Exception e) {
//            System.out.println("Error retrieving user data: " + e.getMessage());
//        }
    }

    public void nextAction() {
        System.out.println("Would you like to do anything else? (Y/N)");

        Scanner userInput = new Scanner(System.in);
        String choice = userInput.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            adminSelection();
        } else if (choice.equalsIgnoreCase("N")) {
            leaveLibrary();
        } else {
            System.out.println("Invalid choice. Please try again.");
            nextAction();
        }
    }


}
