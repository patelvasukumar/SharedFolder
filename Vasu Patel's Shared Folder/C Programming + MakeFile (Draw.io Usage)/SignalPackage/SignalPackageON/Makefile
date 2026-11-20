FILEFLAGS = -Wall -Wextra -pedantic -ansi
# storing all the falgs into a variable 

.PHONY: clean run
# clean and run the phoney target.
# so if there is a file name clean, due to phony target it is not going to 
# confuse.
# so, phony targets give names to actions which are not about creating a 
# specific file, but it performs commands like clean and run.

ArtifactFinding: ArtifactFinding.o
	gcc $(FILEFLAGS) ArtifactFinding.o -o ArtifactFinding

ArtifactFinding.o: ArtifactFinding.c
	gcc $(FILEFLAGS) -c ArtifactFinding.c

run: ArtifactFinding
	./ArtifactFinding

clean:
	rm -f ArtifactFinding ArtifactFinding.o 

#
#	target : prerequirement
#	command
#	command
#
#	so, basically makefile is not a script. we need make command to interpret the file just like in phython. 
#	we need a interpreter.
#
#	here, we are using some basic makefile rule. 
#
#	so, externally, when we try to type command like make run. by default, make command
#	search for Makefile name (if Makefile name does not exists, we need -f ) and try to
#	check the prerequirement. here, if the file is upto date and exists, it runs the command.
#
#	However, if it is not, it tries to fulfill the the prerequirements. It tries to make object file and
#	try to compile .c file.
#
#	there are also two conditions, so, if we do not have first rule ArtifactFinding, make command will prompt
#	an error message. However, if we do not have the second rule ArtifactFinding.o , it will still works if
#	the make command tries to implement, its compiling command that is going to find .c file and compile it.
#	However, it is good practice to write the ArtifactFinding.o type ruls in a make file.
#
#	At the last, clean rule, it removes the ArtifactFinding file and ArtifactFinding.o from the system.
#	we need to use -f for safety purpose. so, if the file is not on the directory, it just ignore and do
#	not give us error messages.
#
#

