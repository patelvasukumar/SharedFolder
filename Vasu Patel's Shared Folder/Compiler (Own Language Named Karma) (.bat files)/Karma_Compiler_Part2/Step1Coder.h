#ifndef COMPILERS_H_
#include "Compilers.h"
#endif

#ifndef CODER_H_
#define CODER_H_

 // ASCII range for visible characters
#define ASCII_START 32
#define ASCII_END 126
#define ASCII_RANGE (ASCII_END - ASCII_START + 1)

#define CYPHER 1
#define DECYPHER 0

void vigenereFile(const stringK inputFileName, const stringK outputFileName, const stringK key, intK encode);
stringK vigenereMem(const stringK inputFileName, const stringK key, intK encode);

void cypher(const stringK inputFileName, const stringK outputFileName, const stringK key);
void decypher(const stringK inputFileName, const stringK outputFileName, const stringK key);
intK getSizeOfFile(const stringK filename);

#endif
