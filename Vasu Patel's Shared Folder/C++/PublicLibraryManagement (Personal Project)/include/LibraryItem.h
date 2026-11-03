#pragma once
#include <string>
#include <iostream>

class LibraryItem {

    std::string itemID;
    std::string title;
    std::string creator;
    std::string format;
    bool isAvailable;

public:
    LibraryItem(std::string, std::string, std::string, std::string, bool);

    void updateAvailability(bool);
    std::string displayItemInfo() const;

    std::string getID() const;
    std::string getTitle() const;
    std::string getCreator() const;
    std::string getFormat() const;
    bool getAvailability() const;

    bool operator==(const LibraryItem&) const;

    friend std::ostream& operator<<(std::ostream&, const LibraryItem&);
};