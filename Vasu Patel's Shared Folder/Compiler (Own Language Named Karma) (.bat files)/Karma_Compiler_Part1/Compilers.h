

/*
************************************************************
* File name: Compilers.h
* Compiler: MS Visual Studio 2022
* Course: CST 8152 – Compilers, Lab Section: [011, 012]
* Assignment: A1-A5.
* Date: Sep 01 2025
* Professor: Paulo Sousa
* Purpose: This file defines the functions called by main function.
* Function list: mainReader(), mainScanner(), mainParser().
*************************************************************/

#ifndef COMPILERS_H_
#define COMPILERS_H_

#define DEBUG 0

/*
 * ............................................................................
 * ADVICE 1:
 * Please check the "TODO" labels to develop your activity.
 *
 * ADVICE 2: This file must be updated according to each assignment
 * (see the progression: reader > scanner > parser).
 * ............................................................................
 */

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

/*
------------------------------------------------------------
Main functions signatures
(Code will be updated during next assignments)
------------------------------------------------------------
*/
intK main1Coder	(intK argc, stringK* argv);

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
