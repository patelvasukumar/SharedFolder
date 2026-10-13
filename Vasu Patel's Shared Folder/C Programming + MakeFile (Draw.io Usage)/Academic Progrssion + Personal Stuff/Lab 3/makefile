FileFlag = -Wall -Wextra -pedantic -ansi
# storing all the falgs into a variable

.PHONY: clean run
# clean and run the phoney target.
# so if there is a file name clean, due to phony target it is not going to
# confuse.
# so, phony targets give names to actions which are not about creating a
# specific file, but it performs commands like clean and run.

# -> Note : Since here we are using a header file, we need to put header file name besides .c file name in the prerequirement. It is a good 
#  practice. It is running on programmer's system, but it might not run on other's system.


Vasukumar_Patel_Linux: Vasukumar_Patel_Lab3.o 
	gcc $(FileFlag) Vasukumar_Patel_Lab3.o -o Vasukumar_Patel_Linux

Vasukumar_Patel_Lab3.o: Vasukumar_Patel_Lab3.c VasuHeader.h 
	gcc $(FileFlag) -c Vasukumar_Patel_Lab3.c

run: Vasukumar_Patel_Linux
	./Vasukumar_Patel_Linux

clean: 
	rm -f Vasukumar_Patel_Linux Vasukumar_Patel_Lab3.o Vasukumar_Patel_Lab3.exe



#
#       target : prerequirement
#       command
#       command
#
#       so, basically makefile is not a script. we need make command to interpret the file just like in phython.
#       we need a interpreter.
#
#       here, we are using some basic makefile rule.
#
#       so, externally, when we try to type command like make run. by default, make command
#       search for Makefile name (if Makefile name does not exists, we need -f ) and try to
#       check the prerequirement. here, if the file is upto date and exists, it runs the command.
#
#       However, if it is not, it tries to fulfill the the prerequirements. It tries to make object file and
#       try to compile .c file.
#
#   there are also two conditions, so, if we do not have first rule, make command will prompt
#    an error message. However, if we do not have the second rule object , it will still works if
#     the make command tries to implement, its compiling command that is going to find .c file and compile it.
#       However, it is good practice to write the object file type ruls in a make file.
#
#       At the last, clean rule, it removes linux file and object from the system.
#       we need to use -f for safety purpose. so, if the file is not on the directory, it just ignore and do
#       not give us error messages.
#
#
