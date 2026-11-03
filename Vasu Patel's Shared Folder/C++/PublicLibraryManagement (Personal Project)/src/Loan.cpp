#include "Loan.h"

Loan::Loan(std::string l, std::string p, std::string i, std::string c, std::string d)
    : loanID(l), patronID(p), itemID(i), checkoutDate(c), dueDate(d) {
}

std::string Loan::displayLoan() const {
    return loanID + " " + patronID + " " + itemID;
}

std::string Loan::getLoanID() const { return loanID; }
std::string Loan::getItemID() const { return itemID; }
std::string Loan::getPatronID() const { return patronID; }
std::string Loan::getCheckoutDate() const { return checkoutDate; }
std::string Loan::getDueDate() const { return dueDate; }

bool Loan::operator==(const Loan& other) const {
    return loanID == other.loanID;
}

std::ostream& operator<<(std::ostream& os, const Loan& l) {
    os << l.displayLoan();
    return os;
}