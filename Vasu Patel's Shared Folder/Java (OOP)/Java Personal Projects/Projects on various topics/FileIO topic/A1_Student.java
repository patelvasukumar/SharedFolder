package Week2_FileIO;

import java.io.Serializable;

public class A1_Student implements Serializable {
    private int studNumber;
    private String firstName;
    private String lastName;
    private double gpa;

    // Default constructor
    public A1_Student() {
        // Optionally, you can initialize variables with default values.
        this.studNumber = 0;
        this.firstName = "";
        this.lastName = "";
        this.gpa = 0.0;
    }

    // Parameterized constructor
    public A1_Student(int studNumber, String firstName, String lastName, double gpa) {
        this.studNumber = studNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gpa = gpa;
    }

    // Override toString method to return a formatted string representation of the Student object
    @Override
    public String toString() {
        return "Student Number: " + this.studNumber + "\n" +
               "First Name: " + this.firstName + "\n" +
               "Last Name: " + this.lastName + "\n" +
               "GPA: " + this.gpa;
    }
}

