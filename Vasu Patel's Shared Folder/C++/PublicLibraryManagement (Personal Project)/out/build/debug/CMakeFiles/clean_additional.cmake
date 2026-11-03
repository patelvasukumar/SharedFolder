# Additional clean files
cmake_minimum_required(VERSION 3.16)

if("${CONFIG}" STREQUAL "" OR "${CONFIG}" STREQUAL "Debug")
  file(REMOVE_RECURSE
  "CMakeFiles\\PublicLibraryManagement_autogen.dir\\AutogenUsed.txt"
  "CMakeFiles\\PublicLibraryManagement_autogen.dir\\ParseCache.txt"
  "PublicLibraryManagement_autogen"
  )
endif()
