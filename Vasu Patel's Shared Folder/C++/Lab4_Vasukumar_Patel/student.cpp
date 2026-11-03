/*
 -> student.cpp file
Student Name : Vasukumar (Vasu) Patel
Student Number : 041153940
Lab Activity 4 - Student Management System
Due Date : 22nd March (11:59 PM)
Lab Professor : Surbhi Bahri

Note : Not using for Lab-4 at all

*/

#include "Student.h"


static void clearCin() {
    cin.clear();
    cin.ignore(numeric_limits<streamsize>::max(), '\n');
}

void addStudents(vector<unique_ptr<Student>>& students) {

    const int MAX_STUDENTS = 7;
    int numberToAdd = 0;

    while (true) {

        cout << "\nEnter number of students to add: ";

        string input;
        getline(cin, input);

        if (!Student::isWholeNumber(input)) {
            cout << "Error : Please enter a whole number.\n";
            continue;
        }

        numberToAdd = stoi(input);

        if (numberToAdd <= 0) {
            cout << "Error : Must be > 0.\n";
            continue;
        }

        if (numberToAdd > MAX_STUDENTS) {
            cout << "Error : Number of students must be 7 or less.\n";
            continue;
        }

        if (students.size() + numberToAdd > MAX_STUDENTS) {
            cout << "Error : Cannot exceed maximum limit of 7 students.\n";
            cout << "You can only add "
                << (MAX_STUDENTS - students.size())
                << " more student(s).\n";
            continue;
        }

        break;
    }

    for (int i = 0; i < numberToAdd; ++i) {

        cout << "\n--- Student " << (students.size() + 1) << " ---\n";

        auto student = make_unique<Student>();

        student->readFromInput(students);

        students.push_back(move(student));

        cout << "\nStudent Added.\n";
    }

    cout << "\nTotal students now: " << students.size()
        << " (live Student objects: "
        << Student::getTotalStudentsCreated() << ")\n";
}


void Student::calculateResults() {
    
    total = 0.0f;

    for (int i = 0; i < SUBJECT_COUNT; ++i) {
        total += marks[i];
    }

    average = static_cast<float>(total) / static_cast<float>(SUBJECT_COUNT);

    if (average >= PASSING_LINE) {
        status = PASS;
    } else {
        status = FAIL;
    }

    if (average >= 80.0f) {
        grade = Grade::A;
    } else if (average >= 70.0f) {
        grade = Grade::B;
    } else if (average >= 60.0f) {
        grade = Grade::C;
    } else if (average >= 50.0f) { 
        grade = Grade::D;
    } else { grade = Grade::F; }

}


void displayStudentsTable(const vector<unique_ptr<Student>>& students) {

    if (students.empty()) {
        cout << "\nNo student records to display.\n";
        return;
    }

    cout << "\nID      Name                 Avg     Grade Status\n";
    cout << "------------------------------------------------\n";

    for (const auto& s : students) {
		s->display();
    }

}


void findHighestScorer(const vector<unique_ptr<Student>>& students) {

    if (students.empty()) {
        cout << "\nNo records available.\n";
        return;
    }

    auto it = max_element(students.begin(), students.end(), [](const unique_ptr<Student>& a, const unique_ptr<Student>& b) {
        return a->getTotal() < b->getTotal();
        });

    cout << "\nHighest Scorer:\n";
    cout << "ID      Name                 Avg     Grade Status\n";
    cout << "------------------------------------------------\n";
	(*it)->display();  

}


void displayClassSummary(const vector<unique_ptr<Student>>& students) {

    if (students.empty()) {
        cout << "\nNo records to summarize.\n";
        return;
    }

    float sumAvg = 0.0f;
    int passed = 0;

    for (const auto& s : students) {
        sumAvg += s->getAverage();
        if (s->getStatus() == PASS) { ++passed; }
    }

    float classAvg = sumAvg / static_cast<float>(students.size());

    cout << "\nClass Summary:\n";
    cout << "Total Students: " << students.size() << "\n";
    cout << fixed << setprecision(2) << "Class Average: " << classAvg << "\n";
    cout << "Passed: " << passed << " / " << students.size() << "\n";

}



Student::Student(): id(0), name(), marks(make_unique<float[]>(SUBJECT_COUNT)),
                    total(0.0f), average(0.0f), status(FAIL), 
                    grade(Grade::F) 
{
        
        ++totalStudentsCreated;
        for(int i = 0; i < SUBJECT_COUNT; i++){ 
			marks[i] = 0.0f;
        }

}   


Student::Student(int id, const string& name, const float* marks): 
                  id(id), name(name), marks(make_unique<float[]>(SUBJECT_COUNT)), 
                  total(0.0f), average(0.0f), status(FAIL), 
                  grade(Grade::F) 
{
        
        ++totalStudentsCreated;
        for (int i = 0; i < SUBJECT_COUNT; i++) {
            this->marks[i] = marks[i];
        }
		calculateResults();

}


Student::Student(const Student &other): 
id(other.id), name(other.name), marks(make_unique<float[]>(SUBJECT_COUNT)),
 total(other.total), average(other.average), status(other.status), grade(other.grade) 
 {
        
        ++totalStudentsCreated;
        for (int i = 0; i < SUBJECT_COUNT; i++) {
            this->marks[i] = other.marks[i];
        }

}


Student::Student(Student&& other) noexcept :
                id(other.id), name(move(other.name)), marks(move(other.marks)),
                total(other.total), average(other.average), status(other.status), grade(other.grade) 
 {
        
        ++totalStudentsCreated;
        other.id = 0;
        other.total = 0.0f;
        other.average = 0.0f;
        other.status = FAIL;
        other.grade = Grade::F;

		other.marks = make_unique<float[]>(SUBJECT_COUNT);

        for (int i = 0; i < SUBJECT_COUNT; i++) {
            other.marks[i] = 0.0f;
        }

}


Student::Student(int id, const string& name, initializer_list<float> marksList):
         id(id), name(name), marks(make_unique<float[]>(SUBJECT_COUNT)),
         total(0.0f), average(0.0f), status(FAIL), grade(Grade::F)
{
    
    ++totalStudentsCreated;
       
       int i = 0;
        for (float m : marksList) {
            if (i < SUBJECT_COUNT) {
                marks[i++] = m;
            } 
        }

        for(int j = i; j < SUBJECT_COUNT; ++j) {
            marks[j] = 0.0f;
		}
		
        calculateResults();

}


Student::Student(int id): Student() { this->id = id; }


Student& Student::operator=(const Student& other) {
     
     if (this == &other) { return *this; } 

	 id = other.id;
     name = other.name; 
	 total = other.total;
     average = other.average;
     status = other.status;
     grade = other.grade;
	 marks = make_unique<float[]>(SUBJECT_COUNT);

     for (int i = 0; i < SUBJECT_COUNT; ++i) {
         marks[i] = other.marks[i];
     }

	 return *this;

}


Student& Student::operator=(Student&& other) noexcept {
    
    if (this == &other) { return *this; }

    id = other.id;
    name = move(other.name);
    marks = move(other.marks);
    total = other.total;
    average = other.average;
    status = other.status;
    grade = other.grade;

    other.id = 0;
    other.total = 0.0f;
    other.average = 0.0f;
    other.status = FAIL;
    other.grade = Grade::F;
    other.marks = make_unique<float[]>(SUBJECT_COUNT);
    
    for (int i = 0; i < SUBJECT_COUNT; ++i) { other.marks[i] = 0.0f; }

    return *this;

}


void Student::display() const{

    cout << left << setw(8) << id 
        << setw(20) << name
        << right << fixed << setprecision(2)
		<< setw(8) << average << " "
        << setw(4) << gradeToString(grade) << " "
        << setw(4);
    
        if (status == PASS) {
            cout << "PASS";
        } else {
            cout << "FAIL";
        }
	    cout << '\n';

}


void Student::updateMarks(const float* newMarks){

    if(newMarks == nullptr) { return; }

    for (int i = 0; i < SUBJECT_COUNT; ++i) {
		marks[i] = newMarks[i];
    }

	calculateResults();

}

void Student::updateMarks(float m1, float m2, float m3) {
    marks[0] = m1; 
    marks[1] = m2; 
    marks[2] = m3;
    calculateResults();
}


float Student::recursiveTotal(int index) const {
    
    if (index < 0) { return 0.0f; }
    if (index == 0) { return marks[0]; }

    return marks[index] + recursiveTotal(index - 1);

}


void Student::readFromInput(const vector<unique_ptr<Student>>& existingStudents) {

    while (true) {

        cout << "\nEnter ID: ";

        if (cin >> id && id > 0) {

            bool duplicate = false;

            for (const auto& student : existingStudents) {
                if (student->getId() == id) {
                    duplicate = true;
                    break;
                }
            }

            if (duplicate) {
                cout << "Error : Duplicate ID not allowed.\n";
                clearCin();
                continue;
            }

            clearCin();
            break;
        }

        cout << "Error: ID must be positive.\n";
        clearCin();
    }

    while (true) {

        cout << "Enter Name: ";
        getline(cin, name);

        bool hasNonSpace = false;

        for (char ch : name) {
            if (!isspace((unsigned char)ch)) {
                hasNonSpace = true;
                break;
            }
        }

        if (hasNonSpace) break;

        cout << "Error : Name cannot be empty.\n";
    }

    for (int i = 0; i < SUBJECT_COUNT; ++i) {

        while (true) {

            cout << "Enter marks for subject " << (i + 1) << ": ";

            if (cin >> marks[i] && marks[i] >= 0 && marks[i] <= 100) {
                break;
            }

            cout << "Error: Marks must be between 0 and 100.\n";
            clearCin();
        }

        clearCin();
    }

    calculateResults();
}

bool Student::isWholeNumber(const string& number) {

    if (number.empty()) {
        return false;
    }

    for (char ch : number) {

        if (!isdigit(static_cast<unsigned char>(ch))) {
            return false;
        }

    }

    return true;
}

/*Theory learning references : https://www.geeksforgeeks.org/cpp/c-plus-plus/ */