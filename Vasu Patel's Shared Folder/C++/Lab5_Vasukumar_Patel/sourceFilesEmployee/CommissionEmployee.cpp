/* -> CommissionEmployee.cpp file
Student Name : Vasukumar (Vasu) Patel
Student Number : 041153940
Lab Activity 4 - Student Management System
Due Date : 22nd March (11:59 PM)
Lab Professor : Surbhi Bahri
*/
#include "CommissionEmployee.h"
 
CommissionEmployee::CommissionEmployee() : Employee(), sales(0.0), commissionRate(0.0) {}

CommissionEmployee::CommissionEmployee(const string& name, double baseSalary, double sales, double commissionRate)
    : Employee(name, baseSalary), sales(sales), commissionRate(commissionRate) {
}

double CommissionEmployee::calculatePay() const {
    return getBaseSalary() + (sales * commissionRate);
}

void CommissionEmployee::displayInfo() const {
    cout << "Commission Employee: Name = " << getName()
        << ", Base = " << fixed << setprecision(8) << getBaseSalary()
        << ", Sales = " << fixed << setprecision(8) << sales
        << ", Rate = " << fixed << setprecision(8) << commissionRate
        << ", Total Pay = " << fixed << setprecision(8) << calculatePay()
        << "\n";
}