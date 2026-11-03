# CMakeList.txt : CMake project for Lab1_Vasukumar_Patel, include source and define
# project specific logic here.
#
cmake_minimum_required (VERSION 3.10)

# Enable Hot Reload for MSVC compilers if supported.
if (POLICY CMP0141)
  cmake_policy(SET CMP0141 NEW)
  set(CMAKE_MSVC_DEBUG_INFORMATION_FORMAT "$<IF:$<AND:$<C_COMPILER_ID:MSVC>,$<CXX_COMPILER_ID:MSVC>>,$<$<CONFIG:Debug,RelWithDebInfo>:EditAndContinue>,$<$<CONFIG:Debug,RelWithDebInfo>:ProgramDatabase>>")
endif()

project ("Lab4_Vasukumar_Patel")

# Add source to this project's executable.
add_executable (Lab4_Vasukumar_Patel "main.cpp" "student.h" "student.cpp"

	 # === EMPLOYEE HEADER FILES ===
    "headerFilesEmployee/Employee.h"
    "headerFilesEmployee/SalariedEmployee.h"
    "headerFilesEmployee/HourlyEmployee.h"
    "headerFilesEmployee/CommissionEmployee.h"

    # === EMPLOYEE SOURCE FILES ===
    "sourceFilesEmployee/Employee.cpp"
    "sourceFilesEmployee/SalariedEmployee.cpp"
    "sourceFilesEmployee/HourlyEmployee.cpp"
    "sourceFilesEmployee/CommissionEmployee.cpp"

)

target_include_directories(Lab4_Vasukumar_Patel PRIVATE headerFilesEmployee)

if (CMAKE_VERSION VERSION_GREATER 3.12)
  set_property(TARGET Lab4_Vasukumar_Patel PROPERTY CXX_STANDARD 20)
endif()

# TODO: Add tests and install targets if needed.
