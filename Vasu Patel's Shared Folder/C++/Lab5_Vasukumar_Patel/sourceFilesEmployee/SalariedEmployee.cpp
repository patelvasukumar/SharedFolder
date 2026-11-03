/* -> SalariedEmployee.cpp
Student Name : Vasukumar (Vasu) Patel
Student Number : 041153940
Lab Activity 4 - Student Management System
Due Date : 22nd March (11:59 PM)
Lab Professor : Surbhi Bahri
*/

#include "SalariedEmployee.h"

SalariedEmployee::SalariedEmployee() : Employee(), bonus(0.0) {}

SalariedEmployee::SalariedEmployee(const string& name, double baseSalary, double bonus)
    : Employee(name, baseSalary), bonus(bonus) {
}

double SalariedEmployee::calculatePay() const {
    return getBaseSalary() + bonus;
}

void SalariedEmployee::displayInfo() const {
    cout << "Salaried Employee: Name = " << getName()
        << ", Base = " << fixed << setprecision(8) << getBaseSalary()
        << ", Bonus = " << fixed << setprecision(8) << bonus
        << ", Total Pay = " << fixed << setprecision(8) << calculatePay()
        << "\n";
}