/*
 -> student.h file
Student Name : Vasukumar (Vasu) Patel
Student Number : 041153940
Lab Activity 3 - Student Management System
Due Date : 8th March (11:59 PM)
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

#include <initializer_list> /* For initializer_list */

using namespace std;    /* namespace like a container . libraries like cout and cin can be used without std:: similar to directive */
                        /* one disadvantage that cout, cin and other cannot be used as variable names after using the line because of the name conflict */

enum Status { FAIL, PASS };
enum class Grade { A, B, C, D, F };

                    /* Struct helps to define custome data type for a group. In C++, function can declared, in C -> not possible */
                    /* Struct members are public by default, but class memebers are private */
class Student {     /* Student class -> default private */

    private:
        int id;
        string name;   /* std namespace (container), and scope resolution operator is used to access String class  */
		unique_ptr<float[]> marks; /* smart pointer to manage dynamic array of marks */
        float total;
        float average;
        Status status;
        Grade grade;

        /* number of subjects per student (compile-time constant) and passing line */
        static constexpr int SUBJECT_COUNT = 3;
		static constexpr float PASSING_LINE = 50.0f;

        /*
            definition and initialization of the static member
            'inline' prevents multiple-definition errors across translation units
        */
        /*
            Static member shared by all Student objects
            Tracks how many Student objects currently exist
        */
        static inline int totalStudentsCreated = 0;

    public:

		Student(); /* default constructor, no parameters, initializes members to default values */

		Student(int id, const string& name, const float* marks);    /* parameterized constructor, initializes members with provided values */

		Student(const Student& other); /* copy constructor, creates a new Student as a copy of an existing one */

		Student(Student&& other) noexcept; /* move constructor, transfers resources from a temporary Student to a new one, leaving the temporary in a valid but unspecified state */

    Student(int id, const string& name, initializer_list<float> marksList); /* constructor with initializer list */

	Student(int id); /* constructor with only ID, other members initialized to default values */

    /* decrement count when a Student is destroyed */
    virtual ~Student() {
        --totalStudentsCreated;
    }

	Student& operator=(const Student& other); /* copy assignment operator, assigns values from one Student to another, handling self-assignment and resource management */

	Student& operator=(Student&& other) noexcept; /* move assignment operator, transfers resources from a temporary Student to an existing Student, leaving the temporary in a valid but unspecified state */

	virtual void display() const; /* displays the student's information */

	/* === LAB-3 NEW FUNCTIONALITIES  === */
	void calculateResults();

    void updateMarks(const float* newMarks);
    void updateMarks(float m1, float m2, float m3);

	float recursiveTotal(int index) const;

    int getId() const { return id; };
    string getName() const { return name; };
    float getAverage() const { return average; };
	Status getStatus() const { return status; };
	Grade getGrade() const { return grade; }   
    float* getMarks() const { return marks.get(); };
    float getTotal() const { return total; }

    static int getTotalStudentsCreated() { return totalStudentsCreated; }
    void readFromInput(const vector<unique_ptr<Student>>& existingStudents);
    static bool isWholeNumber(const string& number);


    
};



/* method declaration */

/*
void inputStudent(Student& s, const std::unique_ptr<Student[]>& students, int count);
*/

void addStudents(vector<unique_ptr<Student>>& students);
void displayStudentsTable(const vector<unique_ptr<Student>>& students);
void findHighestScorer(const vector<unique_ptr<Student>>& students);
void displayClassSummary(const vector<unique_ptr<Student>>& students);

/*
bool checkDuplicateId(const std::unique_ptr<Student[]>& students, int count, int id);
bool checkNameSpaces(const std::string& name);
*/

/* Not going to use for Lab 3, but used in Lab 2  
void saveStudents(const std::unique_ptr<Student[]>& students, int count, const std::string& filename = "students.txt");
void loadStudents(std::unique_ptr<Student[]>& students, int& count, const std::string& filename = "students.txt");
*/

/*
    inline reference : [CoreDumped] https://www.youtube.com/watch?v=7YyALikxAlU
    it replace function calls with function code. It alwasys great
    to use inline in header files.
*/
inline string gradeToString(Grade grade) {

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