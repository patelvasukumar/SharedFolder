/* -> HourlyEmployee.cpp 
Student Name : Vasukumar (Vasu) Patel
Student Number : 041153940
Lab Activity 4 - Student Management System
Due Date : 22nd March (11:59 PM)
Lab Professor : Surbhi Bahri
*/

#include "HourlyEmployee.h"

HourlyEmployee::HourlyEmployee() : Employee(), hoursWorked(0.0), hourlyRate(0.0) {}

HourlyEmployee::HourlyEmployee(const string& name, double hoursWorked, double hourlyRate)
    : Employee(name, 0.0), hoursWorked(hoursWorked), hourlyRate(hourlyRate) {
}

double HourlyEmployee::calculatePay() const {
    return hoursWorked * hourlyRate;
}

void HourlyEmployee::displayInfo() const {
    cout << "Hourly Employee: Name = " << getName()
        << ", Hours = " << fixed << setprecision(8) << hoursWorked
        << ", Rate = " << fixed << setprecision(8) << hourlyRate
        << ", Total Pay = " << fixed << setprecision(8) << calculatePay()
        << "\n";
}