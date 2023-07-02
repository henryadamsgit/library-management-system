package org.example;

import java.util.Scanner;

public class Library {
    public static void main(String[] args) {
        System.out.println("Welcome to the Library!");
        System.out.println("Please enter '1' for User or '2' for Admin");

        Scanner userInput = new Scanner(System.in);
        int choice = userInput.nextInt();

        if (choice == 1) {
            // User operations
            System.out.println("Welcome, User!");
            // Implement user-related functionality here
        } else if (choice == 2) {
            // Admin operations
            System.out.println("Enter the admin password: ");
            String password = userInput.next();

            if (password.equals("abcdefg")) {
                System.out.println("Welcome, Admin!");
                // Implement admin-related functionality here
            } else {
                System.out.println("Incorrect password. Access denied");

            }
        } else {
            System.out.println("Invalid choice. Please try again.");
            // Handle invalid choice scenario
        }
    }
}
