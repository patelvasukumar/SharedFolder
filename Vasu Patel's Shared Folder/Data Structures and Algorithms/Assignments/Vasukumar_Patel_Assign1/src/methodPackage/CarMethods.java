package methodPackage;

/**
 * Node class to store vehicle information and pointers to next/previous nodes
 */
class Node {

    public int mileage;
    public String make;
    public String model;
    public String colour;
    public Node next;
    public Node previous;

    /**
     * Parameterized constructor
     */
    public Node(int mileage, String make, String model, String colour) {
        this.mileage = mileage;
        this.make = make;
        this.model = model;
        this.colour = colour;
    }
}

/**
 * CarMethods class provides all linked list operations
 */
public class CarMethods {

    private Node first;
    private Node last;

    /**
     * Default constructor
     */
    public CarMethods() {
        first = null;
        last = null;
    }

    /**
     * Check if the list is empty
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Insert node at the beginning
     */
    public void insertFirst(int mileage, String make, String model, String colour) {
        Node newNode = new Node(mileage, make, model, colour);
        newNode.next = first;
        newNode.previous = null;

        if (first != null) {
            first.previous = newNode;
        } else {
            last = newNode;
        }

        first = newNode;
    }

    /**
     * Insert node at the end
     */
    public void insertLast(int mileage, String make, String model, String colour) {
        Node newNode = new Node(mileage, make, model, colour);
        newNode.next = null;

        if (isEmpty()) {
            newNode.previous = null;
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.previous = last;
            last = newNode;
        }
    }

    /**
     * Print all vehicles
     */
    public void printOutput() {
        if (isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        System.out.printf("\n %-10s %-10s %-10s %-10s", "Mileage", "Make", "Model", "Colour");

        Node current = first;
        while (current != null) {
            System.out.printf("\n %-10d %-10s %-10s %-10s", current.mileage, current.make, current.model, current.colour);
            current = current.next;
        }

        System.out.println("\n\nThe program is made by Vasukumar Patel.");
    }

    /**
     * Print vehicle with maximum mileage
     */
    public void printMax() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }

        Node current = first;
        int max = current.mileage;
        String make = current.make;
        String model = current.model;
        String colour = current.colour;

        current = current.next;

        while (current != null) {
            if (current.mileage > max) {
                max = current.mileage;
                make = current.make;
                model = current.model;
                colour = current.colour;
            }
            current = current.next;
        }

        System.out.printf("\n\nMax Mileage Vehicle:\n %-10d %-10s %-10s %-10s", max, make, model, colour);
    }

    /**
     * Print vehicle with minimum mileage
     */
    public void printMin() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }

        Node current = first;
        int min = current.mileage;
        String make = current.make;
        String model = current.model;
        String colour = current.colour;

        current = current.next;

        while (current != null) {
            if (current.mileage < min) {
                min = current.mileage;
                make = current.make;
                model = current.model;
                colour = current.colour;
            }
            current = current.next;
        }

        System.out.printf("\n\nMin Mileage Vehicle:\n %-10d %-10s %-10s %-10s\n", min, make, model, colour);
    }
}
