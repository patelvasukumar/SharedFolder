/* -> Employee.cpp
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
    cout << left << setw(20) << name << right << setw(12) << fixed << setprecision(2) << baseSalary;
}

// Validate name (not empty and not just spaces)
bool Employee::isValidName(const string& text) {
    if (text.empty()) { return false; }

    for (char ch : text) {
        if (!isspace(static_cast<unsigned char>(ch))) {
            return true;
        }
    }
    return false;
}


/* === Functions === */

// Add new employee with input validation
void addEmployee(vector<unique_ptr<Employee>>& employees) {

    string type;

    while (true) {
        cout << "\n1. Salaried\n2. Hourly\n3. Commission\nChoose type: ";
        getline(cin, type);

        if (type == "1" || type == "2" || type == "3") break;

        cout << "Invalid type. Try again.\n";
    }

    string name;
    
    while (true) {
        
        cout << "Enter name: ";
        getline(cin, name);

        if (Employee::isValidName(name)) { break; } 


        cout << "Invalid name. Cannot be empty or spaces.\n";
    }

    auto getValidNumber = [](const string& prompt) {
        string input;

        while (true) {
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
                    if (value >= 0) return value;
                }
            }

            cout << "Invalid input. Enter non-negative number.\n";
        }
        };

    if (type == "1") {

        double base = getValidNumber("Base salary: ");
        double bonus = getValidNumber("Bonus: ");

        employees.push_back(make_unique<SalariedEmployee>(name, base, bonus));

    }
    else if (type == "2") {

        double hours = getValidNumber("Hours: ");
        double rate = getValidNumber("Rate: ");

        employees.push_back(make_unique<HourlyEmployee>(name, hours, rate));

    }
    else if (type == "3") {

        double base = getValidNumber("Base salary: ");
        double sales = getValidNumber("Sales: ");
        double rate = getValidNumber("Commission rate: ");

        employees.push_back(make_unique<CommissionEmployee>(name, base, sales, rate));
    }

    cout << "\nEmployee added successfully!\n";
}

// Display all employees in a formatted table
void displayAllEmployees(const vector<unique_ptr<Employee>>& employees) {
    
    if (employees.empty()) { cout << "No employees.\n"; return; }
    cout << "\nName                Base Salary   Extra        Pay         Type\n";
    cout << "-----------------------------------------------------------------\n";
    for (const auto& e : employees) { e->displayInfo(); }

}

// Find and display the highest paid employee
void findHighestPaidEmployee(const vector<unique_ptr<Employee>>& employees) {
    if (employees.empty()) { cout << "No employees.\n"; return; }

    auto it = max_element(employees.begin(), employees.end(),[](const unique_ptr<Employee>& a, const unique_ptr<Employee>& b) {
                                                                return a->calculatePay() < b->calculatePay();
                                                              });

    cout << "\nHighest Paid:\n"; (*it)->displayInfo();

}

// Helper function to check if a string is a whole number (used for input validation)
bool isWholeNumber(const string& text) {
    
    if (text.empty()) { return false; }

    for (char ch : text) { if (!isdigit((unsigned char)ch)) { return false; } }
    return true;

}