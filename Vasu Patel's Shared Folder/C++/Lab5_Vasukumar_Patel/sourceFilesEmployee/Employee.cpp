/* +-> Employee.cpp
Student Name : Vasukumar (Vasu) Patel
Student Number : 041153940
Lab Activity 4 - Student Management System
Due Date : 22nd March (11:59 PM)
Lab Professor : Surbhi Bahri
*/

#include "Employee.h"
#include "SalariedEmployee.h"
#include "HourlyEmployee.h"
#include "CommissionEmployee.h"

using namespace std;

// === Employee Class Implementation ===
Employee::~Employee() {}

// Default constructor
Employee::Employee() : name(""), baseSalary(0.0) {}

// Parameterized constructor
Employee::Employee(const string& name, double baseSalary) : name(name), baseSalary(baseSalary) {}


// Display basic info (name and base salary)
void Employee::displayInfo() const {
    cout << left << setw(20) << name << right << setw(12) << fixed << setprecision(8) << baseSalary << "\n";
}


string Employee::getName() const { return name; }
double Employee::getBaseSalary() const { return baseSalary; }
void Employee::setName(const string& name) { this->name = name; }
void Employee::setBaseSalary(double baseSalary) { this->baseSalary = baseSalary; }


// Helper function to check if a string is a whole number (used for input validation)
bool isWholeNumber(const string& text) {

    if (text.empty()) { return false; }

    for (char ch : text) { if (!isdigit((unsigned char)ch)) { return false; } }
    return true;

}


// Helper function to get a valid non-negative number from user input (used for input validation)
static double  getValidNumber(const string& prompt) {
    string input;

    while(true){

        cout << prompt;
        getline(cin, input);

        if (!input.empty()) {
            bool valid = true;
            bool dotSeen = false;

            for (char ch : input) {
                if (ch == '.') {
                    if (dotSeen) { valid = false; break; }
                    dotSeen = true;
                }
                else if (!isdigit((unsigned char)ch)) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                double value = stod(input);
                if (value >= 0) { return value; }
            }
        }

        cout << "Invalid input. Enter non-negative number.\n";
    }
}


// Function to create an employee based on user input
shared_ptr<Employee> createEmployee() {
    
    string type;

    while (true) { 
        
        cout << "\n1. Salaried\n2. Hourly\n3. Commission\nChoose type: ";
        getline(cin, type);

        if (type == "1" || type == "2" || type == "3") {
            break;
        }

        cout << "Invalid type. Try again.\n";
    }

    string name;

    while (true) {
        
        cout << "Enter name: ";
        getline(cin, name);

        if (Employee::isValidNameRegex(name)) { break; }

        cout << "Invalid name. Cannot be empty or spaces.\n";
    }

    if (type == "1") {
        double base = getValidNumber("Base salary: ");
        double bonus = getValidNumber("Bonus: ");
        return make_shared<SalariedEmployee>(name, base, bonus);
    }

    if (type == "2") {
        double hours = getValidNumber("Hours: ");
        double rate = getValidNumber("Rate: ");
        return make_shared<HourlyEmployee>(name, hours, rate);
    }

    double base = getValidNumber("Base salary: ");
    double sales = getValidNumber("Sales: ");
    double rate = getValidNumber("Commission rate: ");
    return make_shared<CommissionEmployee>(name, base, sales, rate);

}


// Static function to validate name using regex (only letters and spaces, no leading/trailing spaces)
bool Employee::isValidNameRegex(const string& text) {

    if (text.empty()) { return false; }

    
    regex pattern("^[A-Za-z]+( [A-Za-z]+)*$");

    return regex_match(text, pattern);
}

