#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#ifndef COMPILERS_H_
#include "Compilers.h"
#endif

#ifndef CODER_H_
#include "Step1Coder.h"
#endif

#ifndef WRITER_H_
#include "Step5Writer.h"
#endif

/*
************************************************************
* Main function from Buffer
* Parameters:
*   argc / argv = Parameters from command prompt
* Return value:
*	Success operation.
************************************************************
*/

intK main5Writer(intK argc, stringK* argv) {
	
	if (argc >= 3) {
		stringK source = argv[2];

		printf("Reading file %s ....Please wait\n", source);
		intK size = getSizeOfFile(source);
		if (size <= 0) {
			printf("The input file %s is empty.\n", source);   
			return EXIT_SUCCESS;
		}

		stringK content = vigenereMem(source, STR_LANGNAME, DECYPHER);
		if (content == NULL || content[0] == EOS) {
			printf("The input file %s is empty.\n", source);   
			return EXIT_SUCCESS;
		}

		process_content(content);
	}
	else {
		printf("\nMissing input file.\n"); 
	}
	return EXIT_SUCCESS;
}
