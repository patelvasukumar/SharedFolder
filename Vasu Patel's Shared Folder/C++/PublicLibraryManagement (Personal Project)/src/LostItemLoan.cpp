#include "LostItemLoan.h"

LostItemLoan::LostItemLoan(std::string l, std::string p, std::string i, std::string c, std::string d, double cost)
    : Loan(l, p, i, c, d), replacementCost(cost) {
}

double LostItemLoan::computeFee() const {
    return replacementCost;
}

std::string LostItemLoan::displayLoan() const {
    return Loan::displayLoan() + " LOST";
}

double LostItemLoan::getReplacementCost() const {
    return replacementCost;
}