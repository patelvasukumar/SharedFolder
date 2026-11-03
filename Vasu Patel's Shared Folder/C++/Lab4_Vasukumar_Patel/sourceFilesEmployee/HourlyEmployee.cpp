/* -> HourlyEmployee.cpp 
Student Name : Vasukumar (Vasu) Patel
Student Number : 041153940
Lab Activity 4 - Student Management System
Due Date : 22nd March (11:59 PM)
Lab Professor : Surbhi Bahri
*/

#include "HourlyEmployee.h"

HourlyEmployee::HourlyEmployee() : Employee(), hoursWorked(0), hourlyRate(0) {}

HourlyEmployee::HourlyEmployee(const string& name, double hoursWorked, double hourlyRate)
    : Employee(name, 0.0), hoursWorked(hoursWorked), hourlyRate(hourlyRate) {
}

double HourlyEmployee::calculatePay() const {
    return hoursWorked * hourlyRate;
}

void HourlyEmployee::displayInfo() const {
    cout << left << setw(20) << getName()
        << right << setw(12) << hoursWorked
        << setw(12) << hourlyRate
        << setw(12) << calculatePay()
        << "  Hourly\n";
}