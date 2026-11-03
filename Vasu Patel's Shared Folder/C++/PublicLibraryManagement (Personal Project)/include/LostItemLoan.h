#pragma once
#include "Loan.h"

class LostItemLoan : public Loan {
    double replacementCost;

public:
    LostItemLoan(std::string, std::string, std::string, std::string, std::string, double);
    double computeFee() const override;
    std::string displayLoan() const override;

    double getReplacementCost() const;

};