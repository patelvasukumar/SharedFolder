#pragma once

#ifndef PAYROLLMANAGER_H
#define PAYROLLMANAGER_H

#include <vector> // For std::vector
#include <memory> // For std::shared_ptr
#include <algorithm> // For std::max_element
#include <numeric> // For std::accumulate
#include <iostream>  // For std::cout
#include <iomanip> // For std::setprecision

using namespace std;

template <typename T> // T should be a class that has calculatePay() and displayInfo() methods

// PayrollManager class manages a collection of employees and provides functionalities to compute payroll and display employee information.
class PayrollManager{

	private: 
		vector<shared_ptr<T>> employees; // Vector to store shared pointers to employee objects

	public:

		// Adds an employee to the payroll manager. Takes a shared pointer to an employee object and adds it to the employees vector.
		void addEmployee(shared_ptr<T> emp){
		
			if(emp){
				employees.push_back(emp);
			}

		}


		// Computes the total payroll by summing up the pay of all employees. Uses std::accumulate to iterate through the employees vector and sum their pay.
		double computeTotalPayroll() const {
		
		return accumulate(employees.begin(), employees.end(),0.0, 
							
			[](double sum, const shared_ptr<T>& emp) {
				return sum + emp->calculatePay();
			}
		);

		}


		// Computes the average pay by dividing the total payroll by the number of employees. Checks if there are any employees to avoid division by zero.
		double computeAveragePay() const{
		
			if(employees.empty()) { return 0.0; }

			return computeTotalPayroll() / static_cast<double>(employees.size());

		
		}


		// Finds the highest paid employee by using std::max_element to compare the pay of each employee. 
		// Returns a shared pointer to the highest paid employee or nullptr if there are no employees.
		shared_ptr<T> findHighestPaidEmployee() const {
		
			if (employees.empty()) { return nullptr; }

			auto it = max_element(employees.begin(), employees.end(),

					  [](const shared_ptr<T>& a, const shared_ptr<T>& b) {
					           return a->calculatePay() < b->calculatePay();
				      }
				
				);	
			
			if (it != employees.end()) { return *it; }
			else { return nullptr; }
		
		}

		
		// Displays information of all employees by iterating through the employees vector and calling the displayInfo() method for each employee.
		// If there are no employees, it displays a message indicating that.
		void displayAll() const {
		
			if (employees.empty()) { cout << "\nNo employees.\n"; return; }
			
			cout << "\nAll Employees:\n";
			for (const auto& emp : employees) { emp->displayInfo(); }
		
		}


		
};

#endif