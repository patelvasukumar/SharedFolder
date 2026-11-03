/*
 *.............................................................................
 * ADVICE 1:
 * Please check the "TODO" labels to develop your activity.
 *
 * ADVICE 2: Preprocessor directives
 * The #define _CRT_SECURE_NO_WARNINGS should be used in MS Visual Studio projects
 * to suppress the warnings about using "unsafe" functions like fopen()
 * and standard sting library functions defined in string.h.
 * The define directive does not have any effect on other compiler projects 
 * (Gcc, VSCode, Codeblocks, etc.).
 *.............................................................................
 */

#define _CRT_SECURE_NO_WARNINGS 

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>
#include <ctype.h>

#ifndef COMPILERS_H_
#include "Compilers.h"
#endif

#ifndef CODER_H_
#include "Step1Coder.h"
#endif

 /* Check for ANSI C compliancy */
#define ANSI_C 0
#if defined(__STDC__)
#undef ANSI_C
#define ANSI_C 1
#endif

/*
************************************************************
* Coder starting method
* Params:
*	- Mode: Cypher/decypher
*	- Input: Filename
*   - Key: Encrypt word
*	- Output: Filename
************************************************************
*/

// Main function to handle command-line arguments
intK main1Coder(intK argc, stringK* argv) {
	
	if (argc < 5) {
		printf("Usage: %s [cypher=1|decypher=0] <input_file> <output_file>\n", argv[0]);
		return EXIT_FAILURE;
	}

	stringK operation = "";
	stringK inputFileName = "";
	stringK key = STR_LANGNAME;
	stringK outputFileName = "";
	
	if (argc > 4) {
		
		operation = argv[2];
		inputFileName = argv[3];
		outputFileName = argv[4];
		
		if (atoi(operation) == CYPHER)
			
			cypher(inputFileName, outputFileName, key);
		
		else if (atoi(operation) == DECYPHER)
			
			decypher(inputFileName, outputFileName, key);
		
		else {
			
			errorPrint("%s%s%s", "Error: Unknown operation ", operation, ". Use 'cypher' or 'decypher'.\n");
			return EXIT_FAILURE;
		
		}
		
		printf("Operation '%s' completed successfully.\n", operation);
		
		intK size = getSizeOfFile(outputFileName);
		stringK output;
		
		if (atoi(operation) == CYPHER)
			output = vigenereMem(outputFileName, key, DECYPHER);
		else 
			output = vigenereMem(outputFileName, key, CYPHER);
		
		printf("OUTPUT:\n%s\n", output);
	
	}
	
	return EXIT_SUCCESS;

}

