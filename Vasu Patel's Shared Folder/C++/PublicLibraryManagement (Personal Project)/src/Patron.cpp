#include "Patron.h"

Patron::Patron(std::string i, std::string n, std::string p, std::string e, std::string t)
    : patronID(i), name(n), phone(p), email(e), patronType(t), fine(0.0) {
}

std::string Patron::getID() const { return patronID; }
std::string Patron::getName() const { return name; }
std::string Patron::getPhone() const { return phone; }
std::string Patron::getEmail() const { return email; }
std::string Patron::getType() const { return patronType; }

double Patron::getFine() const { return fine; }

void Patron::addFine(double f) {
    fine += f;
}

void Patron::clearFine() {
    fine = 0.0;
}

void Patron::updatePatronInfo(std::string n) {
    name = n;
}

std::string Patron::displayPatron() const {
    return patronID + " " + name;
}