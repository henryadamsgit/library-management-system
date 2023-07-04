package org.example.admin;

import org.example.Library;
import org.example.user.User;

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
    }

    private void viewUsers() {
    }

}
