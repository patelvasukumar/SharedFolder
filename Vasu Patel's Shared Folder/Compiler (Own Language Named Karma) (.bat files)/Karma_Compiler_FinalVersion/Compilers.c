/* Compilers.c */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>
#include <ctype.h>

#ifndef COMPILERS_H_
#include "Compilers.h"
#endif

intK main(int argc, char** argv) {

	intK i;
	printLogo();
	if (DEBUG) {
		for (i = 0; i < argc; ++i)
			printf("argv[%d] = %s\n", i, argv[i]);
	}
	if (argc < 2) {
		printf("%s%c%s%c%s%c%s%c%s%c%s", "OPTIONS:\n* [",
			PGM_CDR, "] - Coder\n* [",
			PGM_RDR, "] - Reader\n* [",
			PGM_SCN, "] - Scanner\n* [",
			PGM_PSR, "] - Parser\n* [",
			PGM_WRT, "] - Writer\n");
		return EXIT_FAILURE;
	}
	charK option = argv[1][0];
	switch (option) {
	
	case PGM_CDR:
		
		printf("%s%c%s", "\n[Option '", PGM_CDR, "': Starting CODER .....]\n\n");
		main1Coder(argc, argv);
		break;

	case PGM_RDR:
		
		printf("%s%c%s", "\n[Option '", PGM_RDR, "': Starting READER .....]\n\n");
		main2Reader(argc, argv);
		break;

	case PGM_SCN:
		
		printf("%s%c%s", "\n[Option '", PGM_SCN, "': Starting SCANNER .....]\n\n");
		main3Scanner(argc, argv);
		break;

	case PGM_PSR:
		
		printf("%s%c%s", "\n[Option '", PGM_PSR, "': Starting PARSER .....]\n\n");
		main4Parser(argc, argv);
		break; 

	case PGM_WRT:

		printf("%s%c%s", "\n[Option '", PGM_WRT, "': Starting WRITER .....]\n\n");
		main5Writer(argc, argv);
		break;
	
	default:
		printf("%s%c%s%c%s%c%s%c%s%c%s", "OPTIONS:\n* [",
			PGM_CDR, "] - Coder\n* [",
			PGM_RDR, "] - Reader\n* [",
			PGM_SCN, "] - Scanner\n* [",
			PGM_PSR, "] - Parser\n* [",
			PGM_WRT, "] - Writer\n");
		break;
	
	}
	
	return EXIT_SUCCESS;

}

voidK printLogo() {
	
 printf("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
		"\t=---------------------------------------=\n",
		"\t|  COMPILERS - ALGONQUIN COLLEGE (W26)  |\n",
		"\t=---------------------------------------=\n",
		"\t    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@     \n",
		"\t    @@                            @@     \n",
		"\t    @@                            @@     \n",
		"\t    @@       @@@@        @@@@     @@     \n",
		"\t    @@       @@@@     @@@@        @@     \n",
		"\t    @@       @@@@  @@@@           @@     \n",
		"\t    @@       @@@@@@@@             @@     \n",
		"\t    @@       @@@@@@@@             @@     \n",
		"\t    @@       @@@@  @@@@           @@     \n",
		"\t    @@       @@@@     @@@@        @@     \n",
		"\t    @@       @@@@        @@@@     @@     \n",
		"\t    @@                            @@     \n",
		"\t    @@                            @@     \n",
		"\t    @@    - - - K A R M A - - -   @@     \n",
		"\t    @@      BY VASU AND KAVYA     @@     \n",
		"\t    @@                            @@     \n",
		"\t    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@     \n",
		"\t                                         \n",
		"\t[COMPILER SCRIPT .......................]\n",
		"\t                                         \n"
	);
}

/*
************************************************************
* Error printing function with variable number of arguments
* Params: Variable arguments, using formats from C language.
*	- Internal vars use list of arguments and types from stdarg.h
*   - NOTE: The format is using signature from C Language
************************************************************
*/

voidK errorPrint(stringK fmt, ...) {
	/* Initialize variable list */
	va_list ap;
	va_start(ap, fmt);

	(voidK) vfprintf(stderr, fmt, ap);
	va_end(ap);

	/* Move to new line */
	if (strchr(fmt, '\n') == NULL){
		fprintf(stderr, "\n");
	}

}
