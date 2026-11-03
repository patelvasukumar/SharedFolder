/* -> employee.h
Student Name : Vasukumar (Vasu) Patel
Student Number : 041153940
Lab Activity 4 - Student Management System
Due Date : 22nd March (11:59 PM)
Lab Professor : Surbhi Bahri
*/

#pragma once

#ifndef EMPLOYEE_H
#define EMPLOYEE_H

#include <iostream>
#include <string>
#include <iomanip>
#include <vector>
#include <memory>
#include <algorithm>
#include <limits>
#include <cctype>
#include <regex>

using namespace std;

class Employee {

private:
	string name;
	double baseSalary;;
	

 public: 

	Employee();
	Employee(const string& name, double baseSalary);


	// Virtual destructor for proper cleanup of derived classes
	virtual ~Employee();

	// Pure virtual function to calculate pay, must be implemented by derived classes
	virtual double calculatePay() const = 0;
	virtual void displayInfo() const; // Display basic info (name and base salary)

	// Getters and setters
	string getName() const;
	double getBaseSalary() const;

	void setName(const string& name);
	void setBaseSalary(double baseSalary);

	static bool isValidNameRegex(const string& text);

};

bool isWholeNumber(const string& text);
double getValidNumber(const string& prompt);
shared_ptr<Employee> createEmployee();



#endif 