/* Step 2 Reader.c */

#define _CRT_SECURE_NO_WARNINGS


#include <ctype.h>
#include <string.h>

#ifndef COMPILERS_H_
#include "Compilers.h"
#endif

#ifndef CODER_H_
#include "Step1Coder.h"
#endif

#ifndef READER_H_
#include "Step2Reader.h"
#endif

BufferPointer readerCreate(intK size, realK factor) {

	BufferPointer readerPointer = NULL;

	if (size <= 0)
		size = READER_DEFAULT_SIZE;

	if (size > READER_MAX_SIZE)
		size = READER_MAX_SIZE;

	if (factor <= 0)
		factor = READER_DEFAULT_FACTOR;

	readerPointer = (BufferPointer)calloc(1, sizeof(Buffer));
	if (readerPointer == NULL)
		return NULL;

	readerPointer->content = (stringK)malloc(size * sizeof(charK));
	if (readerPointer->content == NULL) {
		free(readerPointer);
		return NULL;
	}

	readerPointer->size = size;
	readerPointer->factor = factor;

	readerPointer->position.wrte = 0;
	readerPointer->position.read = 0;
	readerPointer->position.mark = 0;

	readerPointer->flags.isEmpty = KARMA_TRUE;
	readerPointer->flags.isFull = KARMA_FALSE;
	readerPointer->flags.isRead = KARMA_FALSE;
	readerPointer->flags.isMoved = KARMA_FALSE;

	memset(readerPointer->histogram, 0, sizeof(readerPointer->histogram));

	readerPointer->numReaderErrors = 0;
	readerPointer->checkSum = 0;

	return readerPointer;
}


BufferPointer readerAddChar(BufferPointer const readerPointer, charK ch) {

	stringK tempReader = NULL;
	intK newSize = 0;
	realK ratio;

	if (!readerPointer || !readerPointer->content)
		return NULL;

	if ((unsigned char)ch >= NCHAR) {
		readerPointer->numReaderErrors++;
		return NULL;
	}

	if (readerPointer->position.wrte >= readerPointer->size) {

		if (readerPointer->size >= READER_MAX_SIZE) {
			readerPointer->numReaderErrors++;
			return NULL;
		}

		ratio = (realK)(1.0 + readerPointer->factor);
		newSize = (intK)(readerPointer->size * ratio);

		if (newSize <= readerPointer->size || newSize > READER_MAX_SIZE) {
			readerPointer->numReaderErrors++;
			return NULL;
		}

		tempReader = (stringK)realloc(readerPointer->content, newSize * sizeof(charK));
		if (tempReader == NULL) {
			readerPointer->numReaderErrors++;
			return NULL;
		}

		if (tempReader != readerPointer->content)
			readerPointer->flags.isMoved = KARMA_TRUE;

		readerPointer->content = tempReader;
		readerPointer->size = newSize;
	}

	readerPointer->content[readerPointer->position.wrte++] = ch;

	readerPointer->histogram[(unsigned char)ch]++;
	readerPointer->checkSum = (readerPointer->checkSum + (unsigned char)ch) & 0xFF;

	readerPointer->flags.isEmpty = KARMA_FALSE;
	readerPointer->flags.isFull =
		(readerPointer->position.wrte == readerPointer->size) ? KARMA_TRUE : KARMA_FALSE;

	return readerPointer;
}


bolnK readerClear(BufferPointer const readerPointer) {

	if (!readerPointer || !readerPointer->content)
		return KARMA_FALSE;

	readerPointer->position.wrte = 0;
	readerPointer->position.read = 0;
	readerPointer->position.mark = 0;

	readerPointer->flags.isEmpty = KARMA_TRUE;
	readerPointer->flags.isFull = KARMA_FALSE;
	readerPointer->flags.isRead = KARMA_FALSE;
	readerPointer->flags.isMoved = KARMA_FALSE;

	memset(readerPointer->histogram, 0, sizeof(readerPointer->histogram));

	readerPointer->numReaderErrors = 0;
	readerPointer->checkSum = 0;

	return KARMA_TRUE;
}


bolnK readerFree(BufferPointer const readerPointer) {

	if (!readerPointer)
		return KARMA_FALSE;

	if (readerPointer->content) {
		free(readerPointer->content);
		readerPointer->content = NULL;
	}

	free(readerPointer);

	return KARMA_TRUE;
}


bolnK readerIsFull(BufferPointer const readerPointer) {
	
	if (readerPointer == NULL) {
		return KARMA_FALSE;
	}

	if (readerPointer->content == NULL) {
		return KARMA_FALSE;
	}

	return readerPointer->flags.isFull;

}


bolnK readerIsEmpty(BufferPointer const readerPointer) {
	
	if (readerPointer == NULL) {
		return KARMA_FALSE;
	}

	if (readerPointer->content == NULL) {
		return KARMA_FALSE;
	}

	return readerPointer->flags.isEmpty;

}


bolnK readerSetMark(BufferPointer const readerPointer, intK mark) {
	
	if (readerPointer == NULL) {
		return KARMA_FALSE;
	}

	if (readerPointer->content == NULL) {
		return KARMA_FALSE;
	}

	if (mark < 0 || mark > readerPointer->position.wrte) {
		return KARMA_FALSE;
	}

	readerPointer->position.mark = mark;

	return KARMA_TRUE;

}


intK readerPrint(BufferPointer const readerPointer) {
	
	charK ch;
	intK count = 0;

	if (readerPointer == NULL) {
		return READER_ERROR;
	}
	

	if (readerPointer->content == NULL) {
		return READER_ERROR;
	}

	readerPointer->position.read = 0;
	readerPointer->flags.isRead = KARMA_FALSE;

	while (!readerPointer->flags.isRead) {

		ch = readerGetChar(readerPointer);

		if (readerPointer->flags.isRead) {
			break;
		}

		if ((unsigned char)ch < NCHAR) {
			printf("%c", ch);
			count++;
		}
		else {
			readerPointer->numReaderErrors++;
		}
	}

	return count;


}


bolnK readerRecover(BufferPointer const readerPointer) {
	
	if (readerPointer == NULL) {
			return KARMA_FALSE;
	}

	readerPointer->position.read = 0;
	readerPointer->position.mark = 0;

	readerPointer->flags.isRead = KARMA_FALSE;

	return KARMA_TRUE;

}

intK readerLoad(BufferPointer const readerPointer, stringK fileName) {

	intK count = 0;
	FILE* fi = NULL;
	stringK decoded = NULL;
	intK i = 0;

	if (readerPointer == NULL) return 0;
	if (readerPointer->content == NULL) return 0;
	if (fileName == NULL) return 0;

	fi = fopen(fileName, "r");
	if (fi == NULL) return 0;

	decoded = vigenereMem(fileName, STR_LANGNAME, DECYPHER);
	if (decoded == NULL) {
		fclose(fi);
		return 0;
	}

	while (decoded[i] != '\0') {

		if (decoded[i] == '\r') {
			i++;
			continue;
		}

		if (readerAddChar(readerPointer, (charK)decoded[i]) == NULL)
			break;

		count++;
		i++;
	}

	readerPointer->flags.isMoved = KARMA_FALSE;


	free(decoded);
	fclose(fi);

	return count;
}

bolnK readerRetract(BufferPointer const readerPointer) {
	if (readerPointer == NULL) {
		return KARMA_FALSE;
	}

	if (readerPointer->content == NULL) {
		return KARMA_FALSE;
	}

	if (readerPointer->position.read <= 0) {
		return KARMA_FALSE;
	}

	readerPointer->position.read--;

	readerPointer->flags.isRead = KARMA_FALSE;

	return KARMA_TRUE;
}

bolnK readerRestore(BufferPointer const readerPointer) {
	
	if (readerPointer == NULL) {
			return KARMA_FALSE;
	}
	
	if (readerPointer->content == NULL) {
		return KARMA_FALSE;
	}

	if (readerPointer->position.mark < 0 ||
		readerPointer->position.mark > readerPointer->position.wrte) {
		return KARMA_FALSE;
	}
		

	readerPointer->position.read = readerPointer->position.mark;

	readerPointer->flags.isRead = KARMA_FALSE;

	return KARMA_TRUE;

}

charK readerGetChar(BufferPointer const readerPointer) {

	if (!readerPointer || !readerPointer->content)
		return EOF_CHAR;

	if (readerPointer->position.read >= readerPointer->position.wrte) {
		readerPointer->flags.isRead = KARMA_TRUE;
		return READER_TERMINATOR;
	}

	readerPointer->flags.isRead = KARMA_FALSE;
	return readerPointer->content[readerPointer->position.read++];
}

stringK readerGetContent(BufferPointer const readerPointer, intK pos) {
	
	if (readerPointer == NULL) {
		return NULL;
	}

	if (readerPointer->content == NULL) {
		return NULL;
	}

	if (pos < 0 || pos > readerPointer->position.wrte) {
			return NULL;
	}

	return readerPointer->content + pos;
}

intK readerGetPosRead(BufferPointer const readerPointer) {
	
	if (readerPointer == NULL) {
		return READER_ERROR;
	}

	if (readerPointer->content == NULL) {
		return READER_ERROR;
	}

	return readerPointer->position.read;

}


intK readerGetPosWrte(BufferPointer const readerPointer) {
	
	if (readerPointer == NULL) {
		return READER_ERROR;
	}

	return readerPointer->position.wrte;
}


intK readerGetPosMark(BufferPointer const readerPointer) {
	
	if (readerPointer == NULL) {
		return READER_ERROR;
	}
	
	if (readerPointer->content == NULL) {
		return READER_ERROR;
	}

	return readerPointer->position.mark;

}


intK readerGetSize(BufferPointer const readerPointer) {
	
	if (readerPointer == NULL) {
	    return READER_ERROR;
	}

	return readerPointer->size;
}


#define FLAGS_
#undef FLAGS_
#ifndef FLAGS_
voidK readerPrintFlags(BufferPointer const readerPointer) {

	if (readerPointer == NULL){
		return;
	}

	printf("Flag.isEmpty = %d\n", readerPointer->flags.isEmpty);
	printf("Flag.isFull = %d\n", readerPointer->flags.isFull);
	printf("Flag.isMoved = %d\n", readerPointer->flags.isMoved);
	printf("Flag.isRead = %d\n", readerPointer->flags.isRead);

	
}
#else
#define bGetFlags(readerPointer) ((readerPointer)?(readerPointer->flags):(RT_FAIL_1))
#endif


voidK readerPrintStat(BufferPointer const readerPointer) {
	
	intK i;

	if (readerPointer == NULL) {
			return;
	}

	for (i = 0; i < NCHAR; i++) {

		if (isprint(i)) {
			printf("B['%c']=%d\n", i, readerPointer->histogram[i]);
		}
		else {
			switch (i) {
			case '\n':
				printf("B['\\n']=%d\n", readerPointer->histogram[i]);
				break;
			case '\t':
				printf("B['\\t']=%d\n", readerPointer->histogram[i]);
				break;
			case '\r':
				printf("B['\\r']=%d\n", readerPointer->histogram[i]);
				break;
			default:
				printf("B[%d]=%d\n", i, readerPointer->histogram[i]);
			}
		}

	}

}


intK readerNumErrors(BufferPointer const readerPointer) {
	
	if (readerPointer == NULL) {
			return READER_ERROR;
	}

	return readerPointer->numReaderErrors;
}

intK readerChecksum(BufferPointer readerPointer) {

	intK sum = 0;
	intK i;

	if (readerPointer == NULL)
		return READER_ERROR;

	if (readerPointer->content == NULL)
		return READER_ERROR;

	for (i = 0; i < readerPointer->position.wrte; i++)
		sum += (unsigned char)readerPointer->content[i];

	readerPointer->checkSum = sum;

	return sum;
}
