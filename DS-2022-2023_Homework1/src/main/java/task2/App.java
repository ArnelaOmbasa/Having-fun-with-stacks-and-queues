package task2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(System.in);

        while (true) {
            System.out.println("Available options: ");
            System.out.println("1. Enter an expression");
            System.out.println("2. Provide a path to a file containing expressions");
            System.out.println("3. Exit");

            int choice = reader.nextInt();
            reader.nextLine();

            if (choice == 1) {
                System.out.print("Enter an expression: ");
                String expression = reader.nextLine();
                Double result = Algorithm.calculate(expression);
                System.out.println("Result of expression: " + result);
            } else if (choice == 2) {
                System.out.print("Enter a file path: ");
                String filePath = reader.nextLine();
                ArrayList<Double> results = Algorithm.calculateFromFile(filePath);
                for (Double result : results) {
                    System.out.println("Result: " + result);
                }
            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid option. Please choose 1 or 2.");
            }
        }
    }}
