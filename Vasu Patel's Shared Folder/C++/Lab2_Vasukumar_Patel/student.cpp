/*
 -> student.cpp file
Student Name : Vasukumar (Vasu) Patel
Student Number : 041153940
Lab Activity 2 - Student Management System
Due Date : 8th February (11:59 PM)
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
    cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
}

/*
used smart pointer to transfer the ownership to other objects 
also it release resources automatically
*/
void addStudents(std::unique_ptr<Student[]>& students, int& count) {

    int numberToAdd;

    while (true) {
        cout << "\nEnter number of students to add: ";
        if (cin >> numberToAdd && numberToAdd > 0 && numberToAdd < 8) { break; }
        cout << "\nInvalid number. Must be > 0 and < 7.\n";
        clearCin();
    }
    clearCin();

    int newCount = count + numberToAdd;
    std::unique_ptr<Student[]> newArr = std::make_unique<Student[]>(newCount);

    for (int i = 0; i < count; ++i) {
        newArr[i] = std::move(students[i]);
    }

    for (int i = count; i < newCount; ++i) {
        cout << "\nStudent " << (i + 1) << ":\n";
        inputStudent(newArr[i], newArr, i);
        calculateResults(newArr[i]);
    }

    students = std::move(newArr);
    count = newCount;

    cout << "\nAdded " << numberToAdd << " students. Total students created: "
        << Student::totalStudentsCreated << "\n";

}

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
        /* to check if there is a space or not */
        if (!std::isspace(static_cast<unsigned char>(ch))) {
            return true;
        }

    }

    return false;

}

void calculateResults(Student &s) {
    
    s.total = 0.0f;

    for (int i = 0; i < SUBJECT_COUNT; ++i) {
        s.total += s.marks[i];
    }

    s.average = static_cast<float>(s.total) / static_cast<float>(SUBJECT_COUNT);

    if (s.average >= PASSING_LINE) {
        s.status = PASS;
    } else {
        s.status = FAIL;
    }

    if (s.average >= 80.0f) {
        s.grade = Grade::A;
    } else if (s.average >= 70.0f) {
        s.grade = Grade::B;
    } else if (s.average >= 60.0f) {
        s.grade = Grade::C;
    } else if (s.average >= 50.0f) { 
        s.grade = Grade::D;
    } else { s.grade = Grade::F; }

}

void displayStudent(const Student &s) {
    
    /* used different types of iomanipulation operators 
    std::fixed -> do not use scientific notation for that
    */
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

void displayStudentsTable(const std::unique_ptr<Student[] >& students, int count) {

    if (count <= 0) {
        cout << "\nNo student records to display.\n";
        return;
    }

    cout << "\nID      Name                 Avg     Grade Status\n";
    cout << "------------------------------------------------\n";

    /* the same concept from JavaScript of span.
    span knows about array's size and element
    it does not store the value
    */
    std::span<Student> sp(students.get(),count);
    for (const auto& s : sp) {
        displayStudent(s);
    }

}

void findHighestScorer(const std::unique_ptr<Student[]>& students, int count) {

    if (count <= 0) {
        cout << "\nNo records available.\n";
        return;
    }

    auto begin = students.get();
    auto end = students.get() + count;

    /* It returns an iterator to the Student with the highest total value in the given range. */
    auto it = std::max_element(begin, end, [](const Student& a, const Student& b) {
        return a.total < b.total;
        });

    cout << "\nHighest Scorer:\n";
    cout << "ID      Name                 Avg     Grade Status\n";
    cout << "------------------------------------------------\n";
    displayStudent(*it);

}

void displayClassSummary(const std::unique_ptr<Student[]>& students, int count) {

    if (count <= 0) {
        cout << "\nNo records to summarize.\n";
        return;
    }

    float sumAvg = 0.0f;
    int passed = 0;

    std::span<Student> sp(students.get(), count);
    for (const auto& s : sp) {
        sumAvg += s.average;
        if (s.status == PASS) { ++passed; }
    }

    float classAvg = sumAvg / static_cast<float>(count);

    cout << "\nClass Summary:\n";
    cout << "Total Students: " << count << "\n";
    cout << std::fixed << std::setprecision(2) << "Class Average: " << classAvg << "\n";
    cout << "Passed: " << passed << " / " << count << "\n";

}

void saveStudents(const std::unique_ptr<Student[]>& students, int count, const std::string& filename) {
        
    if (!students || count <= 0) {
        cout << "\nError: No student data available to save.\n";
        return;
    }
        /* to open and save the data to the file, if file is there
        , it delete previous data and rewrite again.
        Also, if ifle is not there, it creates.
        */
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

    /* It creates a hash-based set that stores **unique `int` values**, 
    used to quickly check if an ID has already been seen (no duplicates).
 */
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

/*Theory learning references : https://www.geeksforgeeks.org/cpp/c-plus-plus/ */