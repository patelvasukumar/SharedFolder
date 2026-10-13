package Week2_FileIO;

import java.io.IOException;
import java.lang.IllegalStateException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadTextFile {
    public static void main(String[] args) {
        // Specify the path to the file clients.txt
        Path path = Paths.get("clients.txt");

        // Open clients.txt, read its contents, and close the file
        try (Scanner input = new Scanner(path)) {
            System.out.printf("%-10s%-12s%-12s%10s%n", "Account", "First Name", "Last Name", "Balance");

            // Read records from the file
            while (input.hasNext()) {
                // Display record contents
                System.out.printf("%-10d%-12s%-12s%10.2f%n",
                        input.nextInt(), input.next(), input.next(), input.nextDouble());
            }
        } catch (IOException | NoSuchElementException | IllegalStateException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
