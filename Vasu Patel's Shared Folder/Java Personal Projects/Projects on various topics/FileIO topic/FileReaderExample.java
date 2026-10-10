package Week2_FileIO;

// Opening the file, reading and closing.

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileReaderExample {

    public static void openFile() {
        Scanner input = null;

        try {
            input = new Scanner(Paths.get(".\\.\\Files\\studentInput.txt"));

            while (input.hasNextLine()) {
                String line = input.nextLine();
                System.out.println(line);
            }

        } catch (FileNotFoundException fe) {
            System.err.println("Error: File not found.");
        } catch (IOException ioe) {
            System.err.println("Error: IO exception occurred.");
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    public static void writeFile(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(".\\.\\Files\\studentInput.txt", true))) {
            writer.write(text);
            writer.newLine();
        } catch (IOException ioe) {
            System.err.println("Error: Unable to write to the file.");
        }
    }

    public static void main(String[] args) {
        openFile();

        // Example of writing to the file
        writeFile("Bob Brown, 33445, Engineering");
    }
}

