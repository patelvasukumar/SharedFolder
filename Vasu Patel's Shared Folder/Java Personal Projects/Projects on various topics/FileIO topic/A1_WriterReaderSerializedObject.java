package Week2_FileIO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileInputStream;

public class A1_WriterReaderSerializedObject {
    public static void main(String[] args) {
        A1_Student pl = new A1_Student(111, "John", "Anderson", 4.1);
        A1_Student p2 = new A1_Student(222, "Cindy", "Dorman", 3.9);

        try {
            // Writing to file (serialized object)
            try (ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream("studentsObject.ser"))) {
                objectOutput.writeObject(pl);
                objectOutput.writeObject(p2);
                System.out.println("Objects have been serialized to studentsObject.ser");
            }

            // Reading from file (serialized object)
            try (ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream("studentsObject.ser"))) {
                A1_Student pr1 = (A1_Student) objectInput.readObject();
                A1_Student pr2 = (A1_Student) objectInput.readObject();

                // Print deserialized objects
                System.out.println(pr1.toString());
                System.out.println(pr2.toString());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
            e.printStackTrace();
        }
    }
}
