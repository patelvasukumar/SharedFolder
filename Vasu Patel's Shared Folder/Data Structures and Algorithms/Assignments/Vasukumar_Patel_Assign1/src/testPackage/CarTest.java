package testPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import methodPackage.CarMethods;

/**
 * Test class for CarMethods - reads data from file and processes it
 */
public class CarTest {

    public static void main(String[] args) {

        String fileName = "vehicleinfo.csv";
        CarMethods car = new CarMethods(); // ✅ One object reused

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int mileage = Integer.parseInt(parts[0].trim());
                String make = parts[1].trim();
                String model = parts[2].trim();
                String colour = parts[3].trim();

                car.insertFirst(mileage, make, model, colour); // ✅ Insert into same object
            }

        } catch (IOException ex) {
            System.out.println("Error: File issue! " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error: Something went wrong! " + ex.getMessage());
        }

        System.out.println("\n_______________________________________________________");
        System.out.println("List of Vehicles:");
        System.out.println("_______________________________________________________");

        car.printOutput();
        car.printMax();    
        car.printMin();    
    }
}
