#pragma once
#include <string>

class Patron {
    std::string patronID;
    std::string name;
    std::string phone;
    std::string email;
    std::string patronType;
    double fine;

public:
    Patron(std::string, std::string, std::string, std::string, std::string);

    std::string getID() const;
    std::string getName() const;
    std::string getPhone() const;
    std::string getEmail() const;
    std::string getType() const;

    double getFine() const;
    void addFine(double);
    void clearFine();

    void updatePatronInfo(std::string);
    std::string displayPatron() const;
};