#pragma once
#include "Loan.h"

class StandardLoan : public Loan {
    int loanPeriodDays;

public:
    StandardLoan(std::string, std::string, std::string, std::string, std::string, int);
    double computeFee() const override;
    std::string displayLoan() const override;

    int getLoanPeriodDays() const;
};