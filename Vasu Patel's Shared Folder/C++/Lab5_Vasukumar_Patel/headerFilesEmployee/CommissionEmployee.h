/* -> CommissionEmployee.h file
Student Name : Vasukumar (Vasu) Patel
Student Number : 041153940
Lab Activity 4 - Student Management System
Due Date : 22nd March (11:59 PM)
Lab Professor : Surbhi Bahri
*/
#pragma once

#ifndef COMMISSIONEMPLOYEE_H
#define COMMISSIONEMPLOYEE_H

#include "Employee.h"

// CommissionEmployee class inherits from Employee and represents an employee who earns a base salary plus commission based on sales
class CommissionEmployee : public Employee {

    private:
        double sales;
        double commissionRate;

    public:
        CommissionEmployee();
        CommissionEmployee(const string& name, double baseSalary, double sales, double commissionRate);

        double calculatePay() const override;
        void displayInfo() const override;
};

#endif