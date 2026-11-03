/*
 -> student.h file
Student Name : Vasukumar (Vasu) Patel
Student Number : 041153940
Lab Activity 2 - Student Management System
Due Date : 8th February (11:59 PM)
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
#include <memory> /*  smart pointers like unique_ptr, shared_ptr */
#include <span> /* lightweight view over a sequence of data */
#include <sstream>  /* string stream for parsing strings */
#include <algorithm>    /*  Algorithms like sort, max_element, find */
#include <vector>   /* Dynamic array container */
#include <limits>   /*  Numeric limits for data types */
#include <cctype>   /* Character handling functions (isdigit, isalpha) */
#include <array>    /* Fixed-size array container */
#include <unordered_set>    /* Hash-based set for unique elements */

using namespace std;    /* namespace like a container . libraries like cout and cin can be used without std:: similar to directive */
                        /* one disadvantage that cout, cin and other cannot be used as variable names after using the line because of the name conflict */

/* number of subjects per student (compile-time constant) and passing line */
constexpr int SUBJECT_COUNT = 3;
constexpr float PASSING_LINE = 50.0f;

enum Status { FAIL, PASS };
enum class Grade { A, B, C, D, F };

                    /* Struct helps to define custome data type for a group. In C++, function can declared, in C -> not possible */
                    /* Struct members are public by default, but class memebers are private */
struct Student {    
    int id;
    string name;   /* std namespace (container), and scope resolution operator is used to access String class  */
    std::array<float, SUBJECT_COUNT> marks;
    float total;
    float average;
    Status status;
    Grade grade;

    /*
    Static member shared by all Student objects
    Tracks how many Student objects currently exist
    */
    static int totalStudentsCreated;

    /* Default constructor */
    Student() : id(0), name(""),
        marks{0.0f, 0.0f, 0.0f},
        total(0.0f),
        average(0.0f),
        status(FAIL),
        grade(Grade::F)
    {
        ++totalStudentsCreated; /* increment count when a Student is created */
    }

    /* decrement count when a Student is destroyed */
    ~Student() {
        --totalStudentsCreated;
    }


    
};

/*
definition and initialization of the static member
'inline' prevents multiple-definition errors across translation units
*/

inline int Student::totalStudentsCreated = 0;

/* method declaration */
void inputStudent(Student& s, const std::unique_ptr<Student[]>& students, int count);
void calculateResults(Student &s);
void displayStudent(const Student &s);

void addStudents(std::unique_ptr<Student[]>& students, int& count);
void displayStudentsTable(const std::unique_ptr<Student[]>& students, int count);
void findHighestScorer(const std::unique_ptr<Student[]>& students, int count);
void displayClassSummary(const std::unique_ptr<Student[]>& students, int count);
bool checkDuplicateId(const std::unique_ptr<Student[]>& students, int count, int id);
bool checkNameSpaces(const std::string& name);

void saveStudents(const std::unique_ptr<Student[]>& students, int count, const std::string& filename = "students.txt");
void loadStudents(std::unique_ptr<Student[]>& students, int& count, const std::string& filename = "students.txt");


/*
    inline reference : [CoreDumped] https://www.youtube.com/watch?v=7YyALikxAlU
    it replace function calls with function code. It alwasys great
    to use inline in header files.
*/
inline std::string gradeToString(Grade grade) {

    switch (grade) {

        case Grade::A:
        return "A";

        case Grade::B:
        return "B";

        case Grade::C:
        return "C";

        case Grade::D:
        return "D";

        case Grade::F:
        return "F";

        default:
        return "Unknown Grade";

    }


}

#endif

/*Theory learning references : https://www.geeksforgeeks.org/cpp/c-plus-plus/ */