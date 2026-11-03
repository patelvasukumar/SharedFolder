#pragma once
#include <vector>
#include <unordered_map>
#include <string>
#include <memory>

template<typename T>

class AnalyticsEngine {
public:
    int computeTotalCirculation(const std::vector<T>& v) {
        return static_cast<int>(v.size());
    }

    int computeActiveLoansCount(const std::vector<T>& v) {
        return static_cast<int>(v.size());
    }

    std::unordered_map<std::string, int> computeItemsByFormat(const std::vector<T>& items) {
        std::unordered_map<std::string, int> map;
        for (const auto& i : items)
            map[i->getFormat()]++;
        return map;
    }

    std::unordered_map<std::string, int> computeMostBorrowedItems(const std::vector<T>& loans) {
        std::unordered_map<std::string, int> map;
        for (const auto& loan : loans)
            map[loan->getItemID()]++;
        return map;
    }
};