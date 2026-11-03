#include "LibraryManager.h"
#include "StandardLoan.h"
#include "LostItemLoan.h"
#include <algorithm>
#include <QDate>
#include <QString>

namespace {
    double calculateLateFee(const Loan* loan) {
        if (!dynamic_cast<const StandardLoan*>(loan)) {
            return 0.0;
        }

        QDate due = QDate::fromString(QString::fromStdString(loan->getDueDate()), "yyyy-MM-dd");
        if (!due.isValid()) {
            return 0.0;
        }

        QDate today = QDate::currentDate();
        if (today <= due) {
            return 0.0;
        }

        return static_cast<double>(due.daysTo(today)) * 25.0;
    }

    bool hasLoanForItem(const std::vector<std::unique_ptr<Loan>>& loans, const std::string& itemID) {
        for (const auto& loan : loans) {
            if (loan->getItemID() == itemID) {
                return true;
            }
        }
        return false;
    }

    bool hasHoldForItem(const std::vector<HoldRecord>& holds, const std::string& itemID, const std::string& exceptHoldID = "") {
        for (const auto& hold : holds) {
            if (hold.itemID == itemID && hold.holdID != exceptHoldID) {
                return true;
            }
        }
        return false;
    }
}

void LibraryManager::addItem(std::unique_ptr<LibraryItem> item) {
    items.push_back(std::move(item));
}

void LibraryManager::registerPatron(std::unique_ptr<Patron> p) {
    patrons.push_back(std::move(p));
}

void LibraryManager::checkoutItem(std::unique_ptr<Loan> l) {
    if (LibraryItem* item = findItemByID(l->getItemID())) {
        item->updateAvailability(false);
    }
    loans.push_back(std::move(l));
}

void LibraryManager::returnItem(std::string id) {

    for (auto it = loans.begin(); it != loans.end(); ++it) {
        
        if ((*it)->getItemID() == id) {
           
           if (Patron* patron = findPatronByID((*it)->getPatronID())) {
                patron->addFine(calculateLateFee(it->get()));
            }

            loans.erase(it);

            if (LibraryItem* item = findItemByID(id)) {
                if (!hasHoldForItem(holds, id) && !hasLoanForItem(loans, id)) {
                    item->updateAvailability(true);
                }
            }

            break;
        }

    }

}

bool LibraryManager::placeHold(const std::string& holdID, const std::string& patronID, const std::string& itemID, const std::string& pickupDate, const std::string& status) {
    if (holdID.empty() || patronID.empty() || itemID.empty()) {
        return false;
    }

    if (findHoldByID(holdID) != nullptr) {
        return false;
    }

    if (hasHoldForItem(holds, itemID)) {
        return false;
    }

    Patron* patron = findPatronByID(patronID);
    if (!patron) {
        return false;
    }

    if (getPatronOutstandingFine(patronID) > 0.0) {
        return false;
    }

    LibraryItem* item = findItemByID(itemID);
    if (!item) {
        return false;
    }

    if (!hasLoanForItem(loans, itemID)) {
        return false;
    }

    std::string finalStatus = status;
    if (finalStatus != "Item on hold" && finalStatus != "Item lost") {
        finalStatus = "Item on hold";
    }

    holds.push_back(HoldRecord{ holdID, patronID, patron->getName(), itemID, pickupDate, finalStatus });
    item->updateAvailability(false);
    return true;
}

bool LibraryManager::updateHold(const std::string& holdID, const std::string& patronID, const std::string& itemID, const std::string& pickupDate, const std::string& status) {
    HoldRecord* hold = findHoldByID(holdID);
    if (!hold) {
        return false;
    }

    Patron* patron = findPatronByID(patronID);
    if (!patron) {
        return false;
    }

    LibraryItem* item = findItemByID(itemID);
    if (!item) {
        return false;
    }

    if (getPatronOutstandingFine(patronID) > 0.0) {
        return false;
    }

    if (!hasLoanForItem(loans, itemID)) {
        return false;
    }

    if (itemID != hold->itemID && hasHoldForItem(holds, itemID, holdID)) {
        return false;
    }

    if (itemID != hold->itemID) {
        if (LibraryItem* oldItem = findItemByID(hold->itemID)) {
            if (!hasHoldForItem(holds, hold->itemID, holdID) && !hasLoanForItem(loans, hold->itemID)) {
                oldItem->updateAvailability(true);
            }
        }
    }

    hold->patronID = patronID;
    hold->patronName = patron->getName();
    hold->itemID = itemID;
    hold->pickupDate = pickupDate;
    hold->status = (status == "Item lost" ? "Item lost" : "Item on hold");

    item->updateAvailability(false);
    return true;
}

bool LibraryManager::removeHold(const std::string& holdID) {
    for (auto it = holds.begin(); it != holds.end(); ++it) {
        if (it->holdID == holdID) {
            std::string itemID = it->itemID;
            holds.erase(it);

            if (LibraryItem* item = findItemByID(itemID)) {
                if (!hasHoldForItem(holds, itemID) && !hasLoanForItem(loans, itemID)) {
                    item->updateAvailability(true);
                }
            }

            return true;
        }
    }
    return false;
}

bool LibraryManager::placeItemForLoan(const std::string& holdID, std::unique_ptr<Loan> loan) {
    HoldRecord* hold = findHoldByID(holdID);
    if (!hold || !loan) {
        return false;
    }

    if (hold->status == "Item lost") {
        return false;
    }

    if (loan->getPatronID() != hold->patronID || loan->getItemID() != hold->itemID) {
        return false;
    }

    if (getPatronOutstandingFine(hold->patronID) > 0.0) {
        return false;
    }

    checkoutItem(std::move(loan));
    removeHold(holdID);
    return true;
}

void LibraryManager::restoreHold(const HoldRecord& record) {
    holds.push_back(record);
    if (LibraryItem* item = findItemByID(record.itemID)) {
        item->updateAvailability(false);
    }
}

bool LibraryManager::isOnHold(std::string id) const {
    for (const auto& hold : holds) {
        if (hold.itemID == id) {
            return true;
        }
    }
    return false;
}

bool LibraryManager::editItem(const std::string& id, const std::string& title, const std::string& creator, const std::string& format, bool available) {
    LibraryItem* item = findItemByID(id);
    if (!item) return false;
    *item = LibraryItem(id, title, creator, format, available);
    return true;
}

bool LibraryManager::updateLoan(const std::string& loanID, std::unique_ptr<Loan> newLoan) {
    if (!newLoan) {
        return false;
    }

    for (auto& loanPtr : loans) {
        if (loanPtr->getLoanID() != loanID) {
            continue;
        }

        const std::string oldItemID = loanPtr->getItemID();
        const std::string newItemID = newLoan->getItemID();

        if (newLoan->getLoanID() != loanID && findLoanByID(newLoan->getLoanID())) {
            return false;
        }

        if (newItemID != oldItemID) {
            LibraryItem* newItem = findItemByID(newItemID);
            if (!newItem) {
                return false;
            }

            if (!newItem->getAvailability()) {
                return false;
            }
        }

        loanPtr = std::move(newLoan);

        if (LibraryItem* newItem = findItemByID(newItemID)) {
            newItem->updateAvailability(false);
        }

        if (oldItemID != newItemID) {
            if (LibraryItem* oldItem = findItemByID(oldItemID)) {
                if (!hasHoldForItem(holds, oldItemID) && !hasLoanForItem(loans, oldItemID)) {
                    oldItem->updateAvailability(true);
                }
            }
        }

        return true;
    }

    return false;
}

bool LibraryManager::renewLoan(const std::string& loanID, const std::string& newDueDate) {
    for (auto& loanPtr : loans) {
        if (loanPtr->getLoanID() != loanID) continue;

        if (isOnHold(loanPtr->getItemID())) {
            return false;
        }

        if (auto* standardLoan = dynamic_cast<StandardLoan*>(loanPtr.get())) {
            loanPtr = std::make_unique<StandardLoan>(
                standardLoan->getLoanID(),
                standardLoan->getPatronID(),
                standardLoan->getItemID(),
                standardLoan->getCheckoutDate(),
                newDueDate,
                standardLoan->getLoanPeriodDays()
            );
            return true;
        }

        if (auto* lostLoan = dynamic_cast<LostItemLoan*>(loanPtr.get())) {
            loanPtr = std::make_unique<LostItemLoan>(
                lostLoan->getLoanID(),
                lostLoan->getPatronID(),
                lostLoan->getItemID(),
                lostLoan->getCheckoutDate(),
                newDueDate,
                lostLoan->getReplacementCost()
            );
            return true;
        }

        return false;
    }
    return false;
}

bool LibraryManager::processLostItem(const std::string& loanID, double replacementCost) {
    for (auto& loanPtr : loans) {
        if (loanPtr->getLoanID() != loanID) continue;

        if (dynamic_cast<LostItemLoan*>(loanPtr.get())) {
            return false;
        }

        const std::string patronID = loanPtr->getPatronID();
        const std::string itemID = loanPtr->getItemID();

        Patron* patron = findPatronByID(patronID);
        double lateFee = calculateLateFee(loanPtr.get());

        if (patron) {
            patron->addFine(lateFee + replacementCost);
        }

        loanPtr = std::make_unique<LostItemLoan>(
            loanID,
            patronID,
            itemID,
            loanPtr->getCheckoutDate(),
            loanPtr->getDueDate(),
            replacementCost
        );

        if (LibraryItem* item = findItemByID(itemID)) {
            item->updateAvailability(false);
        }

        return true;
    }
    return false;
}

std::vector<LibraryItem*> LibraryManager::searchItems(std::string keyword) const {
    std::vector<LibraryItem*> result;
    for (const auto& i : items) {
        if (i->getTitle().find(keyword) != std::string::npos)
            result.push_back(i.get());
    }
    return result;
}

std::vector<LibraryItem*> LibraryManager::filterByFormat(std::string format) const {
    std::vector<LibraryItem*> result;
    for (const auto& i : items) {
        if (i->getFormat() == format)
            result.push_back(i.get());
    }
    return result;
}

LibraryItem* LibraryManager::findItemByID(const std::string& id) {
    for (auto& i : items)
        if (i->getID() == id) return i.get();
    return nullptr;
}

Patron* LibraryManager::findPatronByID(const std::string& id) {
    for (auto& p : patrons)
        if (p->getID() == id) return p.get();
    return nullptr;
}

Loan* LibraryManager::findLoanByID(const std::string& id) {
    for (auto& l : loans)
        if (l->getLoanID() == id) return l.get();
    return nullptr;
}

HoldRecord* LibraryManager::findHoldByID(const std::string& id) {
    for (auto& h : holds) {
        if (h.holdID == id) {
            return &h;
        }
    }
    return nullptr;
}

const HoldRecord* LibraryManager::findHoldByID(const std::string& id) const {
    for (const auto& h : holds) {
        if (h.holdID == id) {
            return &h;
        }
    }
    return nullptr;
}

bool LibraryManager::deleteItem(const std::string& id) {
    for (const auto& loan : loans) {
        if (loan->getItemID() == id) return false;
    }

    for (const auto& hold : holds) {
        if (hold.itemID == id) return false;
    }

    auto it = std::remove_if(items.begin(), items.end(),
        [&](const std::unique_ptr<LibraryItem>& p) { return p->getID() == id; });

    if (it == items.end()) return false;
    items.erase(it, items.end());
    return true;
}

bool LibraryManager::deletePatron(const std::string& id) {
    for (const auto& loan : loans) {
        if (loan->getPatronID() == id) return false;
    }

    for (const auto& hold : holds) {
        if (hold.patronID == id) return false;
    }

    auto it = std::remove_if(patrons.begin(), patrons.end(),
        [&](const std::unique_ptr<Patron>& p) { return p->getID() == id; });

    if (it == patrons.end()) return false;
    patrons.erase(it, patrons.end());
    return true;
}

bool LibraryManager::checkItemAvailability(const std::string& id) const {
    for (const auto& i : items)
        if (i->getID() == id) return i->getAvailability();
    return false;
}

void LibraryManager::clearAll() {
    items.clear();
    patrons.clear();
    loans.clear();
    holds.clear();
}

std::vector<HoldRecord> LibraryManager::getHoldsSnapshot() const {
    return holds;
}

double LibraryManager::getPatronOutstandingFine(const std::string& id) const {
    const Patron* patron = nullptr;
    for (const auto& p : patrons) {
        if (p->getID() == id) {
            patron = p.get();
            break;
        }
    }

    if (!patron) {
        return 0.0;
    }

    double total = patron->getFine();

    for (const auto& loan : loans) {
        if (loan->getPatronID() != id) continue;
        if (!dynamic_cast<const StandardLoan*>(loan.get())) continue;
        total += calculateLateFee(loan.get());
    }

    return total;
}

const std::vector<std::unique_ptr<Loan>>& LibraryManager::getLoans() const {
    return loans;
}

const std::vector<std::unique_ptr<LibraryItem>>& LibraryManager::getItems() const {
    return items;
}

const std::vector<std::unique_ptr<Patron>>& LibraryManager::getPatrons() const {
    return patrons;
}