#pragma once
#include <string>
#include "LibraryManager.h"

class DataManager {
public:
    void saveData(const LibraryManager&, std::string);
    void loadData(LibraryManager&, std::string);
};