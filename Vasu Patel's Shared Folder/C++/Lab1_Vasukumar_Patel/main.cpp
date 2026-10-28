/*
 -> main.cpp file
Student Name : Vasukumar (Vasu) Patel
Student Number : 041153940
Lab Activity 1 - Student Management System
Due Date : 25th January (11:59 PM)
Lab Professor : Surbhi Bahri
*/

#include "Student.h"

int main() {
    
    int number;
    int repeat = 0;

    while(repeat == 0) {

    cout << "\nEnter number of students: ";
    cin >> number;

    if (number <= 0) {
        cout << "\nProblem: Student number must be more than 0 ! \n" << endl;
    }
    else {
        repeat = 1;
    }

    }

    Student* students = new Student[number];

        if (!students) {   
            cout << "\nError: Memory allocation failed.\n";
            exit(1);
        }

    for (int i = 0; i < number; i++) {
        cout << "\nEnter data for student " << i + 1 << endl;

        inputStudent(students[i]);
        calculateResults(students[i]);
    }

    cout << "\n--- Student Records ---\n";

    for (int i = 0; i < number; i++) {
        displayStudent(students[i]);
        cout << "\n";
    }

    int topper = 0;
    for (int i = 1; i < number; i++) {
        
        if (students[i].total > students[topper].total) {
            topper = i;
        }

    }

    cout << "\nHighest Scorer:" << endl;

    displayStudent(students[topper]);
    
    saveStudents(students,number);

    delete[] students;

    cout << "\nLoading data from file:" << endl;

    Student* StudentInfo;
    int count;

    loadStudents(StudentInfo, count);

    for (int i = 0; i < count; i++) {
        displayStudent(StudentInfo[i]);
        cout << "\n" << endl;
    }

    delete[] students; 

    cout << "\nThe program is made by Vasu Patel.\n\n" << endl;

    return 0;
}

/*Theory learning references : https://www.geeksforgeeks.org/cpp/c-plus-plus/ */