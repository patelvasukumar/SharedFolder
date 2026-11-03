#pragma once
#include <string>
#include <iostream>

class Loan {
protected:
    std::string loanID;
    std::string patronID;
    std::string itemID;
    std::string checkoutDate;
    std::string dueDate;

public:
    Loan(std::string, std::string, std::string, std::string, std::string);
    virtual ~Loan() = default;

    virtual double computeFee() const = 0;
    virtual std::string displayLoan() const;

    std::string getLoanID() const;
    std::string getItemID() const;
    std::string getPatronID() const;
    std::string getCheckoutDate() const;
    std::string getDueDate() const;

    bool operator==(const Loan& other) const;

    friend std::ostream& operator<<(std::ostream&, const Loan&);
};