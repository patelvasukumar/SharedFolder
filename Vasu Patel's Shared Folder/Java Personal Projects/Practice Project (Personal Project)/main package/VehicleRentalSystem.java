package main;

import java.util.Scanner;
import rental.VehicleFactory;
import rental.Vehicle;
import rental.RentalAction;

public class VehicleRentalSystem {

    private VehicleRentalSystem() {}

    public static void main(String[] args) {
        VehicleRentalSystem system = new VehicleRentalSystem();
        Scanner scanner = new Scanner(System.in);
        VehicleFactory factory = new VehicleFactory();

        System.out.print("Enter the type of the vehicle (Car or Bike): ");
        String type = scanner.nextLine();

        System.out.print("Enter the model: ");
        String model = scanner.nextLine();

        System.out.print("Enter brand: ");
        String brand = scanner.nextLine();

        System.out.print("Enter Plate Number: ");
        int number = 0;
        try {
            number = Integer.parseInt(scanner.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("Invalid plate number. Exiting.");
            scanner.close();
            return;
        }

        System.out.print("Is it available (True/False): ");
        boolean isAvailable = Boolean.parseBoolean(scanner.nextLine());

        Vehicle myVehicle = factory.getVehicle(type, model, brand, number, isAvailable);
        if(myVehicle == null) {
            System.out.println("Invalid vehicle type entered.");
            scanner.close();
            return;
        }

        boolean loop = true;
        while(loop) {
            system.menu();
            try {
                System.out.print("Enter Your Choice: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch(choice) {
                    case 1:
                        myVehicle.displayInfo();
                        break;
                    case 2:
                        ((RentalAction) myVehicle).rentVehicle(model);
                        break;
                    case 3:
                        ((RentalAction) myVehicle).returnVehicle(model);
                        break;
                    case 4:
                        loop = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("The option is wrong. Please try again!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input format. Please enter a number for your choice.");
            } catch (Exception e) {
                System.out.println("There is something wrong! " + e.getMessage());
            }
        }
        scanner.close();
    }

    private void menu() {
        System.out.println();
        System.out.println("Welcome to the Vehicle Rental System");
        System.out.println("1 - View available vehicle");
        System.out.println("2 - Rent a vehicle");
        System.out.println("3 - Return a vehicle");
        System.out.println("4 - Exit");
        System.out.println();
    }
}
