/*
 -> student.cpp file
Student Name : Vasukumar (Vasu) Patel
Student Number : 041153940
Lab Activity 1 - Student Management System
Due Date : 25th January (11:59 PM)
Lab Professor : Surbhi Bahri
*/

/* Note : endl has two functions 1. add /n new line and clear buffer. it is slower than \n because \n does not clear the buffer stream  */

#include "Student.h"

void inputStudent(Student &s) {
    
    cout << "\nEnter ID: ";
    cin >> s.id;

    cout << "Enter Name: ";
    cin >> s.name;

    for (int i = 0; i < 3; i++) {
        cout << "Enter marks for subject " << i + 1 << ": ";
        cin >> s.marks[i];
    }

    
}

void calculateResults(Student &s) {
    
    s.total = 0;

    for (int i = 0; i < 3; i++) {
        s.total += s.marks[i];
    }

    s.average = s.total / 3;
}

void displayStudent(const Student &s) {
    cout << "ID: " << s.id << ", Name: " << s.name << endl;
    cout << "Marks: ";

    for (int i = 0; i < 3; i++) {
        cout << s.marks[i] << " ";
    }

    cout << "\nTotal: " << s.total;
    cout << ", Average: " << setprecision(4) << s.average;
    cout << "\nStudent Status: ";

        if (s.average < 50) {
            cout << "Fail" << endl;
        }
        else {

            cout << "Pass" << endl;

        }
        
}

void saveStudents(Student *students, int count) {
        
        fstream file("students.txt", ios::out | ios::trunc);
        
        if ( !file.is_open() ) {
            cout << "\nError : Can not open the file to write the data ! \n" << endl;
            exit(1);
        }

        file << count << endl;

        for (int i = 0; i < count; i++) {
            
            file << students[i].id << endl;
            file << students[i].name << endl;

                    for (int j = 0; j < 3; j++) {
                        file << students[i].marks[j] << " ";
                    }

            file << endl;
            file << students[i].total << endl;
            file << students[i].average << endl;

        }

        file.close();
        cout << "\nData saved to students.txt" << endl;

}

void loadStudents(Student *&students, int &count) {

    fstream file("students.txt", ios::in);
    
    if ( !file.is_open() ) {
        cout << "\nError: Can not open the file to read ! \n" << endl;
        exit(1);
    }

    file >> count;

    students = new Student[count];

    if (students == nullptr) {
        cout << "\nError: Memory Allocation Failure ! \n" << endl;
        file.close();
        exit(1);
    }

        for (int i = 0; i < count; i++) {
            
            file >> students[i].id;
            file >> students[i].name;

                for (int j = 0; j < 3; j++) {
                    file >> students[i].marks[j];
                }

            file >> students[i].total;
            file >> students[i].average;

        }

        file.close();

}

/*Theory learning references : https://www.geeksforgeeks.org/cpp/c-plus-plus/ */