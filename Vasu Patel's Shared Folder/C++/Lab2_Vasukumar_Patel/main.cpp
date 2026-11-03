/*
 -> main.cpp file
Student Name : Vasukumar (Vasu) Patel
Student Number : 041153940
Lab Activity 2 - Student Management System
Due Date : 8th February (11:59 PM)
Lab Professor : Surbhi Bahri
*/

#include "Student.h"

/*
  Declared static because it's only used inside this file (main.cpp).
  other .cpp files cannot call it.
  its symbol is not visible to the linker for other files.
 */
static void showMenu() {

    cout << "\n===== Advanced Student Management System =====\n";
    cout << "1. Add student records\n";
    cout << "2. Display all students\n";
    cout << "3. Find highest scoring student\n";
    cout << "4. Display class summary\n";
    cout << "5. Save records to file\n";
    cout << "6. Load records from file\n";
    cout << "7. Clear screen and show menu\n";
    cout << "8. Exit\n";
    cout << "Enter your choice: ";

}

/*
  Volatile global variable to track external progress.
  volatile tells the compiler not to optimize it, because its value can change unexpectedly.
 */
volatile int i_progress = 0;

int main() {

    /* smart pointer which release resources automatically */
    std::unique_ptr<Student[]> students = nullptr;  
    int count = 0;

    i_progress = 1; 
    cout << "\nProgram starting. External progress flag = " << i_progress << "\n";

    bool exitProgram = false;

    while (!exitProgram) {
            
            showMenu();
            
            string input;
            getline(cin, input);
            /* reads string from user */

            /* isdigit checks if it is digit or not */
            bool isValid = !input.empty();
            for (char c : input) {
                if (!isdigit(c)) {  
                    isValid = false;
                    break;
                }
            }

            if (!isValid) {
                cout << "\nError: Please enter a whole number only (no decimals).\n";
                continue;   
            }

            /* parsing from string to integer */
            int choice = stoi(input); 

            if (choice < 1 || choice > 8) {
                cout << "\nError: Please select a valid option.\n";
                continue;
            }


            switch (choice) {

                case 1:
                addStudents(students, count);
                break;

                case 2:
                displayStudentsTable(students, count);
                break;

                case 3:
                findHighestScorer(students, count);
                break;

                case 4:
                displayClassSummary(students, count);
                break;

                case 5:
                saveStudents(students, count);
                break;

                case 6:
                loadStudents(students, count);
                break;

                case 8:
                exitProgram = true;
                break;

                case 7:
                system("cls");
                break;

                default:
                cout << "\nError : Please, select a valid option.\n";
                break;

            }

        }

        cout << "\nThe program is made by Vasu and it is closed.\n\n";
        return 0;

}

/*Theory learning references : https://www.geeksforgeeks.org/cpp/c-plus-plus/ */