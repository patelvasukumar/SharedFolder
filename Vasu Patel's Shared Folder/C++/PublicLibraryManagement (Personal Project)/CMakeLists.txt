cmake_minimum_required(VERSION 3.16)

project(PublicLibraryManagement LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTOUIC ON)
set(CMAKE_AUTORCC ON)

find_package(Qt6 REQUIRED COMPONENTS Widgets)

add_executable(${PROJECT_NAME}
    src/main.cpp
    src/GUI.cpp
    src/GUI.ui
    src/DataManager.cpp
    src/LibraryItem.cpp
    src/LibraryManager.cpp
    src/Loan.cpp
    src/LostItemLoan.cpp
    src/Patron.cpp
    src/StandardLoan.cpp

    include/GUI.h
    include/DataManager.h
    include/LibraryItem.h
    include/LibraryManager.h
    include/Loan.h
    include/LostItemLoan.h
    include/Patron.h
    include/StandardLoan.h
    include/Analytics.h
)

target_include_directories(${PROJECT_NAME} PRIVATE include)

target_link_libraries(${PROJECT_NAME} PRIVATE Qt6::Widgets)