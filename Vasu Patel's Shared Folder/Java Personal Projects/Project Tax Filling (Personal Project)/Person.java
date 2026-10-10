package ProjectTaxFiling;

import java.util.Scanner;

public class Person {

    Scanner key = new Scanner(System.in); // Scanner

    private int personCount;         //1
    private String personName;       //2
    private int personDay;           //3
    private double personSalary;     //4
    public String[] personNameAr;    //5
    public int[] personDayAr;        //6
    public double[] personSalaryAr;  //7

    // constructor
    Person() {}

    // getters and setters

    //1
    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    //2
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    //3
    public int getPersonDay() {
        return personDay;
    }

    public void setPersonDay(int personDay) {
        this.personDay = personDay;
    }

    //4
    public double getPersonSalary() {
        return personSalary;
    }

    public void setPersonSalary(double personSalary) {
        this.personSalary = personSalary;
    }

    //5
    public String[] getPersonNameAr() {
        return personNameAr;
    }

    public void setPersonNameAr(String[] personNameAr) {
        this.personNameAr = personNameAr;
    }

    //6
    public int[] getPersonDayAr() {
        return personDayAr;
    }

    public void setPersonDayAr(int[] personDayAr) {
        this.personDayAr = personDayAr;
    }

    //7
    public double[] getPersonSalaryAr() {
        return personSalaryAr;
    }

    public void setPersonSalaryAr(double[] personSalaryAr) {
        this.personSalaryAr = personSalaryAr;
    }

    // methods

    //1
    public int personCount() {
        System.out.print("For how many people do you want to tax file? ");
        personCount = intCheck();
        return personCount;
    }

    //2
    public String personName() {
        int i = 0;
        while (i < personCount) {
            System.out.print("Write full name for the " + (i + 1) + " person: ");
            String personName = key.nextLine();
            personNameAr[i] = personName;
            i++;
        }
        return "";
    }

    //3
    public int personDay() {
        int i = 0;
        while (i < personCount) {
            System.out.print("How many days " + personNameAr[i] + " works: ");
            personDayAr[i] = intCheck();
            i++;
        }
        return 0;
    }

    //4
    public double personSalary() {
        int i = 0;
        while (i < personCount) {
            System.out.print("What is the salary of " + personNameAr[i] + ": ");
            personSalaryAr[i] = doubleCheck();
            i++;
        }
        return 0;
    }

    // Checking for int, double

    //Int
    private int intCheck() {
        int number = 0;
        boolean input = true;

        while (input) {
            if (key.hasNextInt()) {
                number = key.nextInt();
                input = false;
            } else {
                System.out.print("Your input is invalid. Please, enter a valid input: ");
            }
            key.nextLine(); // clear buffer
        }
        return number;
    }

    //Double
    private double doubleCheck() {
        double value = 0;
        boolean input = true;

        while (input) {
            if (key.hasNextDouble()) {
                value = key.nextDouble();
                input = false;
            } else {
                System.out.print("Your input is invalid. Please, enter a valid input: ");
            }
            key.nextLine(); // clear buffer
        }
        return value;
    }

    //String (commented for future use)
    /*
    private String stringCheck() {
        boolean input = false;
        String message = "";
        
        while (!input) {
            if (key.hasNextLine()) {
                message = key.nextLine();
                input = true;
            } else {
                System.out.print("Your input is invalid. Please, enter a valid input: ");
                key.nextLine();
            }
        }
        return message;
    }
    */
}
