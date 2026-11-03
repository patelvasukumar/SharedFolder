#include "DataManager.h"
#include "StandardLoan.h"
#include "LostItemLoan.h"
#include <fstream>
#include <iomanip>
#include <stdexcept>
#include <regex>
#include "json.hpp"

using json = nlohmann::json;

namespace {

    const std::regex idRegex(R"(^[A-Za-z]{2}\d{4}$)");
    const std::regex nameRegex(R"(^[A-Za-z]+(?: [A-Za-z]+)*$)");
    const std::regex gmailRegex(R"(^[A-Za-z0-9._%+-]+@gmail\.com$)");
    const std::regex phoneRegex(R"(^\d{10}$)");

    bool isValidId(const std::string& text) {
        return std::regex_match(text, idRegex);
    }

    bool isValidName(const std::string& text) {
        return std::regex_match(text, nameRegex);
    }

    bool isValidGmail(const std::string& text) {
        return std::regex_match(text, gmailRegex);
    }

    bool isValidPhone(const std::string& text) {
        return std::regex_match(text, phoneRegex);
    }
}

void DataManager::saveData(const LibraryManager& m, std::string file) {
    if (file.empty()) {
        file = "data/library.json";
    }

    json j;

    for (const auto& item : m.getItems()) {
        j["items"].push_back({
            {"itemID", item->getID()},
            {"title", item->getTitle()},
            {"creator", item->getCreator()},
            {"format", item->getFormat()},
            {"available", item->getAvailability()}
            });
    }

    for (const auto& patron : m.getPatrons()) {
        j["patrons"].push_back({
            {"patronID", patron->getID()},
            {"name", patron->getName()},
            {"phone", patron->getPhone()},
            {"email", patron->getEmail()},
            {"type", patron->getType()},
            {"fine", patron->getFine()}
            });
    }

    for (const auto& loanPtr : m.getLoans()) {
        const Loan* loan = loanPtr.get();
        json lj = {
            {"loanID", loan->getLoanID()},
            {"patronID", loan->getPatronID()},
            {"itemID", loan->getItemID()},
            {"checkoutDate", loan->getCheckoutDate()},
            {"dueDate", loan->getDueDate()}
        };

        if (auto s = dynamic_cast<const StandardLoan*>(loan)) {
            lj["type"] = "standard";
            lj["loanPeriodDays"] = s->getLoanPeriodDays();
        }
        else if (auto l = dynamic_cast<const LostItemLoan*>(loan)) {
            lj["type"] = "lost";
            lj["replacementCost"] = l->getReplacementCost();
        }
        else {
            lj["type"] = "base";
        }

        j["loans"].push_back(lj);
    }

    for (const auto& hold : m.getHoldsSnapshot()) {
        j["holds"].push_back({
            {"holdID", hold.holdID},
            {"patronID", hold.patronID},
            {"patronName", hold.patronName},
            {"itemID", hold.itemID},
            {"pickupDate", hold.pickupDate},
            {"status", hold.status}
            });
    }

    std::ofstream f(file);
    if (!f) throw std::runtime_error("Cannot open file for saving.");
    f << std::setw(4) << j;
}

void DataManager::loadData(LibraryManager& m, std::string file) {
    if (file.empty()) {
        file = "data/library.json";
    }

    std::ifstream f(file);
    if (!f) throw std::runtime_error("Cannot open file for loading.");

    json j;
    f >> j;

    m.clearAll();

    if (j.contains("items")) {
        for (const auto& item : j["items"]) {
            std::string itemID = item.value("itemID", "");
            std::string title = item.value("title", "");
            std::string creator = item.value("creator", "");
            std::string format = item.value("format", "");
            bool available = item.value("available", true);

            if (!isValidId(itemID)) continue;
            if (title.empty()) continue;
            if (!isValidName(creator)) continue;

            m.addItem(std::make_unique<LibraryItem>(
                itemID,
                title,
                creator,
                format,
                available
            ));
        }
    }

    if (j.contains("patrons")) {
        for (const auto& p : j["patrons"]) {
            std::string patronID = p.value("patronID", "");
            std::string name = p.value("name", "");
            std::string phone = p.value("phone", "");
            std::string email = p.value("email", "");
            std::string type = p.value("type", "Adult");
            double fine = p.value("fine", 0.0);

            if (!isValidId(patronID)) continue;
            if (!isValidName(name)) continue;
            if (!isValidPhone(phone)) continue;
            if (!isValidGmail(email)) continue;

            auto patron = std::make_unique<Patron>(
                patronID,
                name,
                phone,
                email,
                type
            );
            patron->addFine(fine);
            m.registerPatron(std::move(patron));
        }
    }

    if (j.contains("loans")) {
        for (const auto& l : j["loans"]) {
            std::string type = l.value("type", "standard");
            std::string loanID = l.value("loanID", "");
            std::string patronID = l.value("patronID", "");
            std::string itemID = l.value("itemID", "");
            std::string checkoutDate = l.value("checkoutDate", "");
            std::string dueDate = l.value("dueDate", "");

            if (!isValidId(loanID)) continue;
            if (!isValidId(patronID)) continue;
            if (!isValidId(itemID)) continue;
            if (!m.findPatronByID(patronID)) continue;
            if (!m.findItemByID(itemID)) continue;

            if (type == "lost") {
                double replacementCost = l.value("replacementCost", 0.0);
                m.checkoutItem(std::make_unique<LostItemLoan>(
                    loanID, patronID, itemID, checkoutDate, dueDate, replacementCost
                ));
            }
            else {
                int days = l.value("loanPeriodDays", 7);
                m.checkoutItem(std::make_unique<StandardLoan>(
                    loanID, patronID, itemID, checkoutDate, dueDate, days
                ));
            }
        }
    }

    if (j.contains("holds")) {
        for (const auto& h : j["holds"]) {
            if (h.is_string()) {
                std::string itemID = h.get<std::string>();
                HoldRecord record;
                record.holdID = itemID;
                record.patronID = "";
                record.patronName = "";
                record.itemID = itemID;
                record.pickupDate = "";
                record.status = "Item on hold";
                m.restoreHold(record);
                continue;
            }

            std::string holdID = h.value("holdID", "");
            std::string patronID = h.value("patronID", "");
            std::string patronName = h.value("patronName", "");
            std::string itemID = h.value("itemID", "");
            std::string pickupDate = h.value("pickupDate", "");
            std::string status = h.value("status", "Item on hold");

            if (holdID.empty()) holdID = itemID;

            HoldRecord record;
            record.holdID = holdID;
            record.patronID = patronID;
            record.patronName = patronName;
            record.itemID = itemID;
            record.pickupDate = pickupDate;
            record.status = status;

            m.restoreHold(record);
        }
    }
}