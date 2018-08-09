
cmake_minimum_required(VERSION 3.4.1)
add_library( # Sets the name of the library.
            mylib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             src/main/cpp/mylib.cpp
             #src/main/jni/native-lib.c
             )


find_library(
              log-lib
              log )


target_link_libraries(     mylib  ${log-lib} )