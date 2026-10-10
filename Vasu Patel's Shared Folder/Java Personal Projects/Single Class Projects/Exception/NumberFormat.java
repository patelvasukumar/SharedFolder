package Exception;

import java.util.Scanner;

public class NumberFormat {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Taking first input
            System.out.println("Enter the first integer:");
            int first = Integer.parseInt(scanner.nextLine()); // Handles invalid input as NumberFormatException

            // Taking second input
            System.out.println("Enter the second integer:");
            int second = Integer.parseInt(scanner.nextLine());

            // Performing division
            int result = first / second;
            System.out.println("The result is: " + result);

        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Please enter a numerical value.");
        } finally {
            System.out.println("The program has been completed!");
            scanner.close(); // Ensures the scanner is closed
        }
    }
}