#include "LibraryItem.h"

LibraryItem::LibraryItem(std::string i, std::string t, std::string c, std::string f, bool a)
    : itemID(i), title(t), creator(c), format(f), isAvailable(a) {
}

void LibraryItem::updateAvailability(bool a) {
    isAvailable = a;
}

std::string LibraryItem::displayItemInfo() const {
    return itemID + " " + title;
}

std::string LibraryItem::getID() const { return itemID; }
std::string LibraryItem::getTitle() const { return title; }
std::string LibraryItem::getCreator() const { return creator; }
std::string LibraryItem::getFormat() const { return format; }
bool LibraryItem::getAvailability() const { return isAvailable; }

bool LibraryItem::operator==(const LibraryItem& other) const {
    return itemID == other.itemID;
}

std::ostream& operator<<(std::ostream& os, const LibraryItem& i) {
    os << i.displayItemInfo();
    return os;
}