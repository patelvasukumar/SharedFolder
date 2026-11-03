/* -> SalariedEmployee.h
Student Name : Vasukumar (Vasu) Patel
Student Number : 041153940
Lab Activity 4 - Student Management System
Due Date : 22nd March (11:59 PM)
Lab Professor : Surbhi Bahri
*/

#pragma once

#ifndef SALARIEDEMPLOYEE_H
#define SALARIEDEMPLOYEE_H

#include "Employee.h"

// Derived class for salaried employees
class SalariedEmployee : public Employee {

    private:
    double bonus;

    public:
    SalariedEmployee();
    SalariedEmployee(const string& name, double baseSalary, double bonus);

    double calculatePay() const override;
    void displayInfo() const override;

};

#endif