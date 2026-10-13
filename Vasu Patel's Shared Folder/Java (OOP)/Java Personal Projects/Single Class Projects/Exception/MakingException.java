package Exception;
import java.util.InputMismatchException;
import java.util.Scanner;

class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
}

public class MakingException {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the first integer: ");
            int first = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter the second integer: ");
            int second = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (second == 0) {
                throw new MyException("Second integer cannot be zero for division.");
            }

            int result = first / second;
            System.out.println("The result is: " + result);

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter a numerical value.");
        } catch (MyException e) {
            System.out.println("Custom Error: " + e.toString());
        } finally {
            System.out.println("The program has been completed!");
            scanner.close();
        }
    }
}

	

