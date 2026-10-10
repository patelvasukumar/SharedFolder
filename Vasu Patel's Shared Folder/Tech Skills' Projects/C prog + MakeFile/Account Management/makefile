flag= -ansi -pedantic -Wall -Wextra

.PHONY: clean run

run: accountManagementLinux
	./accountManagementLinux

accountManagementLinux: accountManagement.o
	gcc $(flag) -O2 accountManagement.o -o accountManagementLinux -lm

accountManagement.o: accountManagement.c accountHeader.h
	gcc $(flag) -c accountManagement.c

clean:
	rm -f accountManagementLinux accountManagement.o

# by default, if we do not specify target and run make command it runs the first rule
#
#-> Note : -lm [link math library] , library name : libm, it uses double float data type for its functions.
# on Linux system, we need -lm flag to link math library with the binary object file after compilation.
#
# The programmer used O2 flag for optimization. So, the code can run faster, use less memory and become smaller in size.
# there are many optimization flags. For example, -O0 [No optimazation] , -O1 [basic] , -O2 [best and safe, but agrresive] , 
# -O3 [Max], -Os [only optimize size] , -Ofast [fastes but unsafe because ingnore standards].
#
#  Fact : 
#
#  1. optimization enable Dead code elimination Loop unrolling (sometimes), Inlining small functions, Constant folding, 
#     Common subexpression elimination
#
#  2. optimization affects debugging.
#
#  In order to do debugging best, used -g -O0 flag [default on most system while using gdb], which enables debug variables and more,
#  but not good for code efficency and faster usage and less memeory usage.
#
#  Other useful debug tools like : strace [Trace system calls], Itrace [Trace library calls]
#

# storing all the falgs into a variable flag

# clean and run the phoney target.
# so if there is a file name clean, due to phony target it is not going to
# confuse.
# so, phony targets give names to actions which are not about creating a
# specific file, but it performs commands like clean and run.
#
# -> Note : Since here we are using a header file, we need to put header file name besides .c file name in the prerequirement. It is a good
#  practice. It is running on programmer's system, but it might not run on other's system.

# 
#
# target : prerequirement
# 	command
# 	command
#
# so, basically makefile is not a script. we need make command to interpret the file just like in phython.
# we need a interpreter.
#
# here, we are using some basic makefile rule.
#
# so, externally, when we try to type command like make run. by default, make command
# search for Makefile name (if Makefile name does not exists, we need -f ) and try to
# check the prerequirement. here, if the file is upto date and exists, it runs the command.
#
# However, if it is not, it tries to fulfill the the prerequirements. It tries to make object file and
# try to compile .c file.
#
# there are also two conditions, so, if we do not have first rule, make command will prompt
# an error message. However, if we do not have the second rule object , it will still works if
# the make command tries to implement, its compiling command that is going to find .c file and compile it.
# However, it is good practice to write the object file type ruls in a make file.
#
# At the last, clean rule, it removes linux file and object from the system.
# we need to use -f for safety purpose. so, if the file is not on the directory, it just ignore and do
# not give us error messages.
#
#
                         
