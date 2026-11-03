/*
 -> main.cpp file
Student Name : Vasukumar (Vasu) Patel
Student Number : 041153940
Lab Activity 4 - Student Management System
Due Date : 22nd March (11:59 PM)
Lab Professor : Surbhi Bahri
*/

#include "Employee.h"
#include "SalariedEmployee.h"
#include "HourlyEmployee.h"
#include "CommissionEmployee.h"
#include "PayrollManager.h"

using namespace std;

/* 
	Global object initialized before main() starts and destroyed after main() ends
    -> global constructor runs before main()
    -> global destructor runs after main() ends

    C++ follows a specific execution herearchy for program :
    1. Global / static variables
    2. Global objects (constructors)
	3. main() function -> local variables and objects & program execution
    4. main() ends 
    5. Global objects (destructors)
    6. Program terminates

    -> if constructors are private or protected, the global object is not going to work.
    -> global class must have a public constructor and destructor
    -> if constructor is public but destructor is private or protected -> compile time error
    -> both constructor and destructor must be public for the global object to work properly, to run any program before main() properly 

*/
class ProgramLifecycle {

public:
    ProgramLifecycle() {
        cout << "\nWelcome To The Employee Management System\n";
    }

    ~ProgramLifecycle() {
        cout << "\nThe program is made by Vasu, and it is shut down.\n\n";
    }

};

ProgramLifecycle lifecycle;


/*
  Declared static because it's only used inside this file (main.cpp).
  other .cpp files cannot call it.
  its symbol is not visible to the linker for other files.
 */
static void showMenu() {

    cout << "\n===== Payroll Analytics System =====\n";
    cout << "1. Add employee\n";
    cout << "2. Display all employees\n";
    cout << "3. Display total payroll\n";
    cout << "4. Display average pay\n";
    cout << "5. Display highest-paid employee\n";
    cout << "6. Exit\n";
    cout << "Enter your choice: ";

}

int main() {

	PayrollManager<Employee> payroll; // creating an instance of PayrollManager to manage Employee objects
	bool exitProgram = false;

    while (!exitProgram) {
            
            showMenu();
            
            string choice;
            getline(cin, choice);

            if (!isWholeNumber(choice)) {
                cout << "\nError: Invalid option. Enter a number (1-6).\n";
                continue;
            }

            int choiceInt = std::stoi(choice);
            
            switch (choiceInt) {
            
            /*
            use { } after case 1: to create a separate scope, 
            so we can safely declare variables inside that case. 
            Without it, all cases share the same scope, which can cause compiler errors when variables are initialized.
            */
			case 1: {  
                shared_ptr<Employee> emp = createEmployee(); 
                payroll.addEmployee(emp);
                cout << "\nEmployee added successfully!\n";
                break;
            }
            
            case 2:
                payroll.displayAll();
                break;
            
            case 3:
                cout << "\nTotal Payroll: " << fixed << setprecision(8) << payroll.computeTotalPayroll() << "\n";
                break;
            
            case 4:
                cout << "\nAverage Pay: " << fixed << setprecision(8) << payroll.computeAveragePay() << "\n";
                break;
            
            case 5: 
            
            {
                
                auto highest = payroll.findHighestPaidEmployee();
                
                if (!highest) {
                    cout << "\nNo employees added.\n";
                } else {
                    cout << "\nHighest Paid Employee:\n";
                    highest->displayInfo();
                }
                
                break;
            
            }
            
            case 6:
                exitProgram = true;
                break;
            
            default:
                cout << "\nError: Please, select a valid option.\n";
                break;
            
            }
   
   }

        return 0;

}