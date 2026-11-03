/* Compilers.h */
#ifndef COMPILERS_H_
#define COMPILERS_H_

#define DEBUG 0

#define STR_LANGNAME	"Karma"	
/*		
* 
*			"Language Name : KARMA"
 * A Sanskrit word, originated in Ancient India.
 * Karma refers to the law of cause and effect based on one’s actions.
 * Programmers Vasu and Kavya chose this name because it reflects how
 * every action in a program leads to a corresponding outcome.
 * 
 */

#define KARMA_TRUE  1
#define KARMA_FALSE 0

#define INVALID NULL
#define EOF_CHAR '\0'

/* Every Data Type must be end with capital K */

typedef char			charK;
typedef char*			stringK;
typedef int				intK;
typedef long			longK;	// Add by Vasu and Kavya
typedef float			floatK;
typedef void			voidK;

typedef unsigned char	bolnK;
typedef unsigned char	byteK;

typedef long			longK;
typedef double			doubleK;

typedef float			realK;

/*
------------------------------------------------------------
Programs:
1: Coder - invokes Main1Coder code
2: Reader - invokes Main2Reader code
3: Scanner - invokes Main3Scanner code
4: Parser - invokes Main4Parser code
5: Writer - invokes Main5Writer code
------------------------------------------------------------
*/
enum PROGRAMS {
	PGM_CDR = '1', // Coder:	05pt
	PGM_RDR = '2', // Reader:	05pt
	PGM_SCN = '3', // Scanner:	15pt
	PGM_PSR = '4', // Parser:	15pt
	PGM_WRT = '5', // Writer:	10pt
};

intK main1Coder	(intK argc, stringK* argv);
intK main2Reader(intK argc, stringK* argv);
intK main3Scanner(intK argc, stringK* argv);
voidK printLogo();
voidK errorPrint(stringK fmt, ...);

#endif

/*
	  "args": [
		1,
		1,
		"README.txt",
		"CODED.txt"
	  ]
	  "args": [
		4,
		"CODED.txt"
	  ]
*/
