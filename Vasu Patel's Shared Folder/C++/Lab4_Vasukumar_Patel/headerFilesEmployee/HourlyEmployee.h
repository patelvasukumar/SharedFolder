/* -> HourlyEmployee.h
Student Name : Vasukumar (Vasu) Patel
Student Number : 041153940
Lab Activity 4 - Student Management System
Due Date : 22nd March (11:59 PM)
Lab Professor : Surbhi Bahri
*/

#pragma once

#ifndef HOURLYEMPLOYEE_H
#define HOURLYEMPLOYEE_H

#include "Employee.h"

// Derived class for hourly employees
class HourlyEmployee : public Employee {
    
    private:
        double hoursWorked;
        double hourlyRate;

    public:
        HourlyEmployee();
        HourlyEmployee(const string& name, double hoursWorked, double hourlyRate);

        double calculatePay() const override;
        void displayInfo() const override;

};

#endif