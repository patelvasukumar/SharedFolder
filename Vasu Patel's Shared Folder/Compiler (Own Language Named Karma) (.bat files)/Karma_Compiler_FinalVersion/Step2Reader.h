/* Step 2 Reader.h */

#ifndef COMPILERS_H_
#include "Compilers.h"
#endif

#ifndef READER_H_
#define READER_H_

 /* TIP: Do not change pragmas, unless necessary .......................................*/
 #pragma warning(1:4001)  /*to enforce C89 type comments  - to make //comments an warning */
 #pragma warning(error:4001) /* to enforce C89 comments - to make // comments an error */

#include <stdio.h>  
#include <malloc.h>
#include <limits.h> 

#define READER_ERROR		(-1)		
#define READER_TERMINATOR	'\0'		

#define READER_MAX_SIZE		INT_MAX-1	 
#define READER_DEFAULT_SIZE		100			
#define READER_DEFAULT_FACTOR	0.5f		

/* TO_DO: Add your bit-masks constant definitions here */

// BITS                             (76543210)
#define READER_DEFAULT_FLAG 0x00 	// (0b00000000) = (0x00)_16 = (000)_10

// TO_DO: BIT 3: END = End of buffer flag
#define READER_SET_FLAG_END 0x08	// (0b00001000) = (0x08)_16 = (008)_10

// TO_DO: BIT 2: REL = Rellocation memory flag
#define READER_SET_FLAG_REL 0x04	// (0b00000100) = (0x04)_16 = (004)_10

// TO_DO: BIT 1: EMP = Buffer empty flag
#define READER_SET_FLAG_FUL 0x02	// (0b00000010) = (0x02)_16 = (002)_10

// TO_DO: BIT 0: FUL = Buffer full flag
#define READER_SET_FLAG_EMP 0x01	// (0b00000001) = (0x01)_16 = (001)_10


#define NCHAR				128			/* Chars from 0 to 127 */

#define CHARSEOF			(-1)		/* EOF Code for Reader */

/* STRUCTURES DEFINITION: SUFIXED BY LANGUAGE NAME .................................. */



/* Offset declaration */
typedef struct position {
	intK wrte;					
	intK read;					
	intK mark;					
} Position;

/* Flags declaration */
typedef struct flag {
	bolnK isEmpty;					/* checks if there is no content */
	bolnK isFull;					/* the content is using all size */
	bolnK isRead;					/* all content was read */
	bolnK isMoved;					/* the content was moved in reallocation */
} Flag;

/* Buffer structure */
typedef struct bufferReader {
	stringK		content;			/* pointer to the beginning of character array (character buffer) */
	intK		size;				/* current dynamic memory size (in bytes) allocated to character buffer */
	realK		factor;				/* factor for increase the buffer */
	Flag			flags;				/* contains character array reallocation flag and end-of-buffer flag */
	Position		position;			/* Offset / position field */
	intK		histogram[NCHAR];	/* Statistics of chars */
	intK		numReaderErrors;	/* Number of errors from Reader */
	intK		checkSum;			/* Sum of bytes(chars) */
} Buffer, * BufferPointer;

/* FUNCTIONS DECLARATION:  .................................. */

/* General Operations */
BufferPointer	readerCreate(intK size , realK increment);
BufferPointer	readerAddChar(BufferPointer const, charK);
bolnK			readerClear(BufferPointer const);
bolnK			readerFree(BufferPointer const);
bolnK		readerIsFull(BufferPointer const);
bolnK		readerIsEmpty(BufferPointer const);
bolnK		readerSetMark(BufferPointer const, intK);
intK		readerPrint(BufferPointer const);
intK		readerLoad(BufferPointer const, stringK);
bolnK		readerRecover(BufferPointer const);
bolnK		readerRetract(BufferPointer const);
bolnK		readerRestore(BufferPointer const);
intK		readerChecksum(BufferPointer const);

/* Getters */
charK		readerGetChar(BufferPointer const);
stringK		readerGetContent(BufferPointer const, intK);
intK		readerGetPosRead(BufferPointer const);
intK		readerGetPosWrte(BufferPointer const);
intK		readerGetPosMark(BufferPointer const);
intK		readerGetSize(BufferPointer const);
voidK		readerPrintFlags(BufferPointer const);
voidK		readerPrintStat(BufferPointer const);
intK		readerNumErrors(BufferPointer const);

#endif
