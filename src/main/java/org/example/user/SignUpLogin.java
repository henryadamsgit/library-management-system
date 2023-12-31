package org.example.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

import org.example.JSONFileHandler;
import org.example.book.Book;

public class SignUpLogin {
    public static final String USERS_FILE = "users.json";

    List<Book> booksBorrowed = new ArrayList<>();

    public void signUp() {
        System.out.println("USER SIGN UP");
        Scanner userInput = new Scanner(System.in);

        long libraryNumber = generateRandomLibraryNumber();
        System.out.println("Your Library Number is: " + libraryNumber);

        System.out.print("Please choose a password: ");
        String password = userInput.nextLine();

        User user = new User(libraryNumber, password);

        try {
            JSONFileHandler<User> jsonFileHandler = new JSONFileHandler<>(USERS_FILE, User.class);
            jsonFileHandler.addObject(user);
            System.out.println("Signup successful! Welcome!");
        } catch (Exception e) {
            System.out.println("Error signing up: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void login() {
        System.out.println("USER LOGIN");
        System.out.println("Please enter your details:");

        Scanner userInput = new Scanner(System.in);

        System.out.print("Library Number: ");
        long libraryNumber = userInput.nextLong();
        userInput.nextLine();

        System.out.print("Password: ");
        String password = userInput.nextLine();

        try {
            JSONFileHandler<User> jsonFileHandler = new JSONFileHandler<>(USERS_FILE, User.class);
            List<User> users = jsonFileHandler.getObjects();

            for (User user : users) {
                if (user.getLibraryNumber() == libraryNumber && user.getPassword().equals(password)) {
                    System.out.println("Login successful!");
                    return;
                }
            }
            System.out.println("Invalid library number or password. Please try again.");
        } catch (Exception e) {
            System.out.println("Error logging in: " + e.getMessage());
        }
    }

    private long generateRandomLibraryNumber() {
        Random random = new Random();
        long libraryNumber = 100000 + random.nextInt(900000);
        return libraryNumber;
    }

}
