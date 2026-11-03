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

#ifndef READER_H_
#include "Step2Reader.h"
#endif

#define ANSI_C 0
#if defined(__STDC__)
#undef ANSI_C
#define ANSI_C 1
#endif

voidK displayBuffer(BufferPointer ptr_Buffer);
longK getFileSize(stringK fname);
intK isNumber(const stringK ns);
voidK startReader(stringK program, stringK input, intK size, realK factor);

intK main2Reader(intK argc, stringK* argv) {

	stringK program = argv[0];
	stringK input = argv[2];
	intK size = 0;
	realK factor = (realK)0.0f;
	
	if (argc <= 2) {
		errorPrint("\nDate: %s  Time: %s", __DATE__, __TIME__);
		errorPrint("\nRuntime error at line %d in file %s\n", __LINE__, __FILE__);
		errorPrint("%s\b\b\b\b%s%s", argv[0], ": ", "Missing parameters.");
		errorPrint("Usage: <Option=2> <SourceFile>");
		exit(EXIT_FAILURE);
	}

	startReader(program, input, size, factor);

	return (EXIT_SUCCESS);

}

voidK startReader(stringK program, stringK input, intK size, realK factor) {

	BufferPointer bufferp;	
	intK loadSize = 0;	

	bufferp = readerCreate(READER_DEFAULT_SIZE, READER_DEFAULT_FACTOR);

	if (bufferp == NULL) {
		errorPrint("%s%s", program, ": Cannot allocate buffer - Use: buffer <input>.");
		errorPrint("Filename: %s \n", input);
		exit(1);
	}

	printf("Reading file %s ....Please wait\n", input);
	loadSize = readerLoad(bufferp, input);

	readerChecksum(bufferp);

	if (loadSize == READER_ERROR) {
		printf("The input file %s %s\n", input, "has not been completely loaded.");
		printf("Current size of buffer: %d.\n", readerGetSize(bufferp));
		printf("Input file size: %ld\n", getFileSize(input));
	}

	if ((loadSize != READER_ERROR) && (loadSize != 0)) {
		if (!readerAddChar(bufferp, READER_TERMINATOR)) {
			errorPrint("%s%s%s", program, ": ", "Error in compacting buffer.");
		}
	}

	displayBuffer(bufferp);

	readerFree(bufferp);
	bufferp = NULL;
}

longK getFileSize(stringK fname) {
	
	FILE* input;
	longK flength;
	
	input = fopen(fname, "r");
	
	if (input == NULL) {
		errorPrint("%s%s", "Cannot open file: ", fname);
		return 0;
	}
	
	fseek(input, 0L, SEEK_END);
	flength = ftell(input);
	fclose(input);
	
	return flength;

}

intK isNumber(const stringK ns) {
	charK c; intK i = 0;
	if (ns == NULL) return 0;
	while ((c = ns[i++]) == 0) {
		if (!isdigit(c)) return 0;
	}
	return 1;
}

voidK displayBuffer(BufferPointer ptr_Buffer) {
	
	printf("\nPrinting buffer parameters:\n\n");
	printf("The size of the buffer is:  %d\n",
		readerGetSize(ptr_Buffer));
	
	printf("The current size of the buffer is:  %d\n",
		readerGetPosWrte(ptr_Buffer));
	
	printf("The first symbol in the buffer is:  %c\n",
		readerGetPosWrte(ptr_Buffer) ? *readerGetContent(ptr_Buffer, 0) : ' ');
	
	printf("The value of the flags:\n");
	readerPrintFlags(ptr_Buffer);
	
	printf("Checksum: %d\n", readerChecksum(ptr_Buffer));
	
	printf("%s", "Reader statistics : \n");
	readerPrintStat(ptr_Buffer);
	
	printf("Number of errors: %d\n",
		readerNumErrors(ptr_Buffer));
	
	printf("\nPrinting buffer contents:\n\n");
	readerRecover(ptr_Buffer);
	
	if (!readerPrint(ptr_Buffer))
		printf("Empty buffer\n");

}

