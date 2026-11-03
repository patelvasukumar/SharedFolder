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
	string getName() const { return name; }
	double getBaseSalary() const { return baseSalary; }

	void setName(const string& name) { this->name = name; }
	void setBaseSalary(double baseSalary) { this->baseSalary = baseSalary; }

	static bool isValidName(const string& text);

};

// Function prototypes for employee management
void addEmployee(vector<unique_ptr<Employee>>& employees);
void displayAllEmployees(const vector<unique_ptr<Employee>>& employees);
void findHighestPaidEmployee(const vector<unique_ptr<Employee>>& employees);
bool isWholeNumber(const string& text);

#endif 