
/*
***********************************************************
* File name: Reader.c
* Compiler: MS Visual Studio 2022
* Course: CST 8152 – Compilers, Lab Section: [011, 012, 013]
* Assignment: A12.
* Date: Sep 01 2025
* Professor: Paulo Sousa
* Purpose: This file is the main code for Buffer/Reader (A12)
************************************************************
*/

/*
 *.............................................................................
 * MAIN ADVICE:
 * - Please check the "TODO" labels to develop your activity.
 * - Review the functions to use "Defensive Programming".
 *.............................................................................
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#ifndef COMPILERS_H_
#include "Compilers.h"
#endif

#ifndef CODER_H_
#include "Step1Coder.h"
#endif

// Function to perform the Vigenère cipher (encoding or decoding)
void vigenereFile(const stringK inputFileName, const stringK outputFileName, const stringK key, intK encode) {

	if (inputFileName == NULL || outputFileName == NULL || key == NULL) {
		errorPrint("\nError : VigenereFile - Null Parameter Provided.\n");
		return;	// or exit(1);
	}


	FILE* inputFile = fopen(inputFileName, "rb");
	if (inputFile == NULL) {
		errorPrint("\nError : VigenereFile - Unable to open input file '%s'.\n", inputFileName);
		return;
	}


	FILE* outputFile = fopen(outputFileName, "wb");
	if (outputFile == NULL) {
		errorPrint("\nError : VigenereFile - Unable to open output file '%s'.\n", outputFileName);
		fclose(inputFile);
		return;
	}

	intK keyLen = (intK) strlen(key);
	
	if (keyLen == 0) {
		errorPrint("Error : VigenereFile - The key is empty.");
		fclose(inputFile);
		fclose(outputFile);
		return;
	}

	intK c;
	longK keyIndex = 0;
	
	while ((c = fgetc(inputFile)) != EOF) {
		
		unsigned char uc = (unsigned char) c;
		
		if (uc >= ASCII_START && uc <= ASCII_END) {
			
			intK keyChar = (intK) key[keyIndex % keyLen];
			intK keyOffset = keyChar - ASCII_START; 
			intK index = (intK) uc - ASCII_START;
			intK outIndex;
			
			if (encode == CYPHER) {
			
				outIndex = (index + keyOffset) % ASCII_RANGE;
			
			}
			else {
	
				outIndex = (index - keyOffset) % ASCII_RANGE;
				
				if (outIndex < 0) { 
					outIndex += ASCII_RANGE;
					}
			
			}
			
			unsigned char outChar = (unsigned char)(outIndex + ASCII_START);
			fputc(outChar, outputFile);
			keyIndex++;
		
		}
		else {

			fputc(uc, outputFile);
		
		}
	
	}

	fclose(inputFile);
	fclose(outputFile);

}

// Function to perform the Vigenère cipher (encoding or decoding)
stringK vigenereMem(const stringK inputFileName, const stringK key, intK encode) {
	
	stringK output = NULL;
	
	if (inputFileName == NULL || key == NULL) {
		return NULL;
	}

	FILE* fileOpen = fopen(inputFileName, "rb");
	if (fileOpen == NULL) {
		return NULL;
	}

	fseek(fileOpen, 0, SEEK_END);
	intK size = (intK) ftell(fileOpen);
	rewind(fileOpen);

	if (size <= 0) {
		fclose(fileOpen);
		return NULL;
	}

	output = (stringK) malloc(size + 1);
	
	if (output == NULL) {
		fclose(fileOpen);
		return NULL;
	}

	intK keyLen = (intK) strlen(key);
	intK keyIndex = 0;
	intK i;
	int ch;

	for (i = 0; i < size; i++) {
		ch = fgetc(fileOpen);

		if (ch >= ASCII_START && ch <= ASCII_END) {
			
			intK keyOffset = key[keyIndex % keyLen] - ASCII_START;
			intK value = ch - ASCII_START;

			if (encode == CYPHER){
				value = (value + keyOffset) % ASCII_RANGE;
			} else {
				value = (value - keyOffset + ASCII_RANGE) % ASCII_RANGE;
			}
			
			output[i] = (char)(value + ASCII_START);
			keyIndex++;
		
		}
		else {
			
			output[i] = (char)ch;
		
		}
	
	}

	output[i] = '\0';
	fclose(fileOpen);

	return output;
}

// Function to encode (cypher)
void cypher(const stringK inputFileName, const stringK outputFileName, const stringK key) {
    vigenereFile(inputFileName, outputFileName, key, CYPHER);
}

// Function to decode (decypher)
void decypher(const stringK inputFileName, const stringK outputFileName, const stringK key) {
    vigenereFile(inputFileName, outputFileName, key, DECYPHER);
}

// TO_DO: Get file size (util method)
intK getSizeOfFile(const stringK filename) {
	
	intK size = 0;

	FILE* fileOpen = fopen(filename, "rb");

	if (fileOpen == NULL) {
		return -1;  
	}

	fseek(fileOpen, 0, SEEK_END);
	size = (intK)ftell(fileOpen);
	fclose(fileOpen);

    return size;

}
