#pragma once
#include <vector>
#include <memory>
#include <queue>
#include <string>
#include "LibraryItem.h"
#include "Patron.h"
#include "Loan.h"

struct HoldRecord {
    std::string holdID;
    std::string patronID;
    std::string patronName;
    std::string itemID;
    std::string pickupDate;
    std::string status;
};

class LibraryManager {
    std::vector<std::unique_ptr<LibraryItem>> items;
    std::vector<std::unique_ptr<Patron>> patrons;
    std::vector<std::unique_ptr<Loan>> loans;
    std::vector<HoldRecord> holds;

public:
    void addItem(std::unique_ptr<LibraryItem>);
    void registerPatron(std::unique_ptr<Patron>);
    void checkoutItem(std::unique_ptr<Loan>);
    void returnItem(std::string);

    bool placeHold(const std::string&, const std::string&, const std::string&, const std::string&, const std::string&);
    bool updateHold(const std::string&, const std::string&, const std::string&, const std::string&, const std::string&);
    bool removeHold(const std::string&);
    bool placeItemForLoan(const std::string&, std::unique_ptr<Loan>);
    void restoreHold(const HoldRecord&);
    bool isOnHold(std::string) const;

    bool editItem(const std::string&, const std::string&, const std::string&, const std::string&, bool);
    bool updateLoan(const std::string&, std::unique_ptr<Loan>);
    bool renewLoan(const std::string&, const std::string&);
    bool processLostItem(const std::string&, double);

    std::vector<LibraryItem*> searchItems(std::string) const;
    std::vector<LibraryItem*> filterByFormat(std::string) const;

    LibraryItem* findItemByID(const std::string&);
    Patron* findPatronByID(const std::string&);
    Loan* findLoanByID(const std::string&);
    HoldRecord* findHoldByID(const std::string&);
    const HoldRecord* findHoldByID(const std::string&) const;

    bool deleteItem(const std::string&);
    bool deletePatron(const std::string&);
    bool checkItemAvailability(const std::string&) const;

    void clearAll();
    std::vector<HoldRecord> getHoldsSnapshot() const;

    double getPatronOutstandingFine(const std::string&) const;

    const std::vector<std::unique_ptr<Loan>>& getLoans() const;
    const std::vector<std::unique_ptr<LibraryItem>>& getItems() const;
    const std::vector<std::unique_ptr<Patron>>& getPatrons() const;
};    