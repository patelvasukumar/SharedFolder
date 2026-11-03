#include "StandardLoan.h"

StandardLoan::StandardLoan(std::string l, std::string p, std::string i, std::string c, std::string d, int days)
    : Loan(l, p, i, c, d), loanPeriodDays(days) {
}

double StandardLoan::computeFee() const {
    return 0.0;
}

std::string StandardLoan::displayLoan() const {
    return Loan::displayLoan() + " STANDARD";
}

int StandardLoan::getLoanPeriodDays() const {
    return loanPeriodDays;
} 