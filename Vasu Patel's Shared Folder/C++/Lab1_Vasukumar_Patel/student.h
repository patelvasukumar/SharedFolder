/*
 -> student.h file
Student Name : Vasukumar (Vasu) Patel
Student Number : 041153940
Lab Activity 1 - Student Management System
Due Date : 25th January (11:59 PM)
Lab Professor : Surbhi Bahri
*/

#pragma once        /* the file is only going to used once */ 

                    /* define directives to define users' custome header's files */
#ifndef STUDENT_H   /* Directives # , and micros */
#define STUDENT_H

#include <iostream> /* input and out stream with objects like cin and cout */
#include <fstream>  /* file reading and writing stream */
#include <iomanip>  /* Input/Output Manipulators library for precision function */
#include <string>   /* Importing String class which contains methods and objects to manipulate the string (characters) */
#include <cstdlib>  /* general purpose functions which are inherited from C Language */

using namespace std;    /* namespace like a container . libraries like cout and cin can be used without std:: similar to directive */
                        /* one disadvantage that cout, cin and other cannot be used as variable names after using the line because of the name conflict */

                    /* Struct helps to define custome data type for a group. In C++, function can declared, in C -> not possible */
                    /* Struct members are public by default, but class memebers are private */
struct Student {    
    int id;
    string name;   /* std namespace (container), and scope resolution operator is used to access String class  */
    float marks[3]; /* array */
    float total;
    float average;
};

/* method declaration */
void inputStudent(Student &s);
void calculateResults(Student &s);
void displayStudent(const Student &s);

void saveStudents(Student *students, int count);
void loadStudents(Student *&students, int &count);

#endif

/*Theory learning references : https://www.geeksforgeeks.org/cpp/c-plus-plus/ */