/*
 -> student.cpp file
Student Name : Vasukumar (Vasu) Patel
Student Number : 041153940
Lab Activity 3 - Student Management System
Due Date : 8th March (11:59 PM)
Lab Professor : Surbhi Bahri
*/

/* Note : endl has two functions 1. add /n new line and clear buffer. it is slower than \n because \n does not clear the buffer stream  */

#include "Student.h"

/* clear() : member of std::istream, which clear error flags
    returns nothing
    3 bits : failbit (input operation failure), 
            badbit (hardware), 
            eofbit (end of file)

    ignore(): ignores all characters and new line '\n'
    two para : first max char. to ignor and second delimiter at which stops to ignore
    default is \n
*/
static void clearCin() {
    cin.clear();
    cin.ignore(numeric_limits<streamsize>::max(), '\n');
}

/*  === CHANGED FOR LAB-3 ===
used smart pointer to transfer the ownership to other objects 
also it release resources automatically
*/
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


/* Not going to use for Lab 3, but used in Lab 2  
                
void inputStudent(Student& s, const std::unique_ptr<Student[]>& students, int count) {
    
    while (true) {

        cout << "\nEnter ID: ";

        if (cin >> s.id && s.id > 0) {

            if (checkDuplicateId(students, count, s.id)) {
                cout << "\nError: Duplicate ID not allowed. Try again.\n";
                clearCin();
                continue;
            }

            break;  
        
        }

        cout << "\nError: Please enter a positive integer.\n";
        clearCin();
   
    }
    clearCin();


    while (true) {

        cout << "\nEnter Name: ";
        std::getline(cin, s.name);

        if (checkNameSpaces(s.name)) {
            break;
        }

        cout << "\nError: Name cannot be empty or spaces only. Try again.\n";
    }


    

    for (int i = 0; i < SUBJECT_COUNT; ++i) {
    
        while (true) {

            cout << "Enter marks for subject " << (i + 1) << ": ";
            
            if (cin >> s.marks[i]) {
                
                if (s.marks[i] >= 0.0f && s.marks[i] <= 100.f) {
                        break;
                    }

                cout << "Error : Enter a number between 0 and 100.\n";
                clearCin();
            
            }

        }
    
        clearCin();

    }


    
}

bool checkDuplicateId(const std::unique_ptr<Student[]>& students, int count, int id) {

    for (int i = 0; i < count; ++i) {
       
       if (students[i].id == id) {
            return true;
        }
    
    }
    
    return false;

}

bool checkNameSpaces(const std::string& name) {

    for (char ch : name) {
       
        if (!std::isspace(static_cast<unsigned char>(ch))) {
            return true;
        }

    }

    return false;

}

*/


/* === CHANGED FOR LAB-3 === */
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


/* NOT === NEEDED FOR LAB-3 === 
void displayStudent(const Student &s) {
    
    /* used different types of iomanipulation operators 
    std::fixed -> do not use scientific notation for that
    */ /*
    cout << std::left << std::setw(8) << s.id
        << std::setw(20) << s.name
        << std::right << std::fixed << std::setprecision(2)
        << std::setw(8) << s.average << "  "
        << std::setw(4) << gradeToString(s.grade) << "  "
        << std::setw(4);

        if (s.status == PASS) {
            cout << "PASS";
        }
        else {
            cout << "FAIL";
        }
        
        cout << '\n';

}
*/

/* === CHANGED FOR LAB-3 === */
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

/* === CHANGED FOR LAB-3 === */
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

/* === CHANGED FOR LAB-3 === */
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


/* === LAB-3 NEW FUNCTIONALITIES  === */
/*=== Constructors ===*/

/* Default constructor */
Student::Student(): id(0), name(), marks(make_unique<float[]>(SUBJECT_COUNT)),
                    total(0.0f), average(0.0f), status(FAIL), 
                    grade(Grade::F) 
{
        
        ++totalStudentsCreated;
        for(int i = 0; i < SUBJECT_COUNT; i++){ 
			marks[i] = 0.0f;
        }

}   

/* Parameterized constructor */
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

/* Copy constructor */
Student::Student(const Student &other): 
id(other.id), name(other.name), marks(make_unique<float[]>(SUBJECT_COUNT)),
 total(other.total), average(other.average), status(other.status), grade(other.grade) 
 {
        
        ++totalStudentsCreated;
        for (int i = 0; i < SUBJECT_COUNT; i++) {
            this->marks[i] = other.marks[i];
        }

}

/* Move constructor */
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

/* Parameterized constructor with initializer list */
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

/* Delegating constructor */
Student::Student(int id): Student() { this->id = id; }

/* Copy assignment */
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

/* Move assignment */
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

/* display() method  */
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

/* updateMarks() method */
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

/* recursiveTotal() method */
float Student::recursiveTotal(int index) const {
    
    if (index < 0) { return 0.0f; }
    if (index == 0) { return marks[0]; }

    return marks[index] + recursiveTotal(index - 1);

}

/* readFromInput() method */
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

/* isWholeNumber() method */
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

/*  Not going to use for Lab 3, but used in Lab 2   
void saveStudents(const std::unique_ptr<Student[]>& students, int count, const std::string& filename) {
        
    if (!students || count <= 0) {
        cout << "\nError: No student data available to save.\n";
        return;
    }
        
        std::ofstream out(filename, std::ios::out | std::ios::trunc);
        if (!out.is_open()) {
            cout << "Error: Cannot open file to write.\n";
            return;
        }

        out << count << '\n';

        for (int i = 0; i < count; i++) {
            
            const Student &s = students[i];
            out << s.id << '|' << s.name << '|';

            for (int j = 0; j < SUBJECT_COUNT; ++j) {
                out << s.marks[j];

                if (j < SUBJECT_COUNT - 1) { out << ' ';}

            }

            out << '|' << s.average << '|' << gradeToString(s.grade) << '|';

            if (s.status == PASS) {
                out << "PASS";
            } else {
                out << "FAIL";
            }

            out << '\n';

        }

        out.close();
        cout << "\nData saved to " << filename << "\n";

}

void loadStudents(std::unique_ptr<Student[]>& students, int& count, const std::string& filename) {

    std::ifstream in(filename);
    if (!in.is_open()) {
        cout << "\nError: Cannot open file to read.\n";
        return;
    }

    std::string line;
    if (!std::getline(in, line)) {
        cout << "\nNo records available\n";
        return;
    }

    int fileCount = std::stoi(line);
    if (fileCount <= 0) {
        std::cout << "\nError: Invalid count in file.\n";
        return;
    }

    std::vector<Student> temp;
    temp.reserve(fileCount);

    int lineNumber = 1;

    
    std::unordered_set<int> seenIds;
   
   while (std::getline(in, line)) {
        
        ++lineNumber;
        
        if (line.empty()) { continue; }

        std::stringstream ss(line);
        std::string token;
        std::vector<std::string> parts;

        while (std::getline(ss, token, '|')) {
            parts.push_back(token);
        }

        if (parts.size() != 6) {
            std::cout << "\nError: Skipping corrupt record at line " << lineNumber << ". Incorrect number of fields.\n";
            continue;
        }

        Student s;

        s.id = std::stoi(parts[0]);
        if (s.id <= 0) {
            std::cout << "\nError: Skipping record at line " << lineNumber << ". Invalid ID.\n";
            continue;
        }

        if (seenIds.find(s.id) != seenIds.end()) {
            cout << "\nWarning: Duplicate ID " << s.id
                << " found at line " << lineNumber
                << ". Skipping record.\n";
            continue;
        }

        seenIds.insert(s.id);

        s.name = parts[1];

        std::stringstream ms(parts[2]);
        bool marksOk = true;
        for (int i = 0; i < SUBJECT_COUNT; ++i) {
            float m;
            if (!(ms >> m) || m < 0.0f || m > 100.0f) {
                marksOk = false;
                break;
            }
            s.marks[i] = m;
        }

        if (!marksOk) {
            std::cout << "\nSkipping record at line " << lineNumber << ". Invalid marks.\n";
            continue;
        }

        std::string gstr = parts[4];
        if (gstr == "A") { s.grade = Grade::A; }
        else if (gstr == "B") { s.grade = Grade::B; }
        else if (gstr == "C") { s.grade = Grade::C; }
        else if (gstr == "D") { s.grade = Grade::D; }
        else if (gstr == "F") { s.grade = Grade::F; }
        else {
            std::cout << "\nSkipping record at line " << lineNumber << ". Invalid grade.\n";
            continue;
        }

        std::string sstat = parts[5];
        if (sstat == "PASS") { s.status = PASS; }
        else if (sstat == "FAIL") { s.status = FAIL; }
        else {
            std::cout << "Skipping record at line " << lineNumber << ". Invalid status.\n";
            continue;
        }

        calculateResults(s);

        temp.push_back(std::move(s));
    }
        
    int newCount = static_cast<int>(temp.size());
    if (newCount == 0) {
        std::cout << "Error: No valid records loaded from file.\n";
        return;
    }

    std::unique_ptr<Student[]> newArr = std::make_unique<Student[]>(newCount);
    for (int i = 0; i < newCount; ++i){
        newArr[i] = std::move(temp[i]);
        }

    students = std::move(newArr);
    count = newCount;

    std::cout << "\nLoaded " << count << " valid records from " << filename << "\n";


}
*/
/*Theory learning references : https://www.geeksforgeeks.org/cpp/c-plus-plus/ */