#ifndef PARSER_H_
#define PARSER_H_

#define _CRT_SECURE_NO_WARNINGS

/* Inclusion section */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>

#ifndef COMPILERS_H_
#include "Compilers.h"
#endif
#ifndef READER_H_
#include "Step2Reader.h"
#endif
#ifndef SCANNER_H_
#include "Step3Scanner.h"
#endif

#define LANG_WRTE		"printK"
#define LANG_READ		"inputK"
#define LANG_MAIN		"mainK"
#define LANG_MOKSHA 	"moksha"

/* TO_DO: Create ALL constants for keywords (sequence given in table.h) */

/* Constants */ /* changed for karma */
enum KEYWORDS {
	NO_ATTR = -1,
	KW_data,
	KW_code,
	KW_int,
	KW_real,
	KW_string,
	KW_if,
	KW_then,
	KW_else,
	KW_while,
	KW_do,
	KW_return,
	KW_intK,
	KW_realK,
	KW_stringK,
	KW_printK,
	KW_moksha,
	KW_floatK,
	KW_whileK
};

/* TO_DO: Define the number of BNF rules */
#define NUM_BNF_RULES 25 /* changed for karma */

/* Parser */
typedef struct parserData {
	intK parsHistogram[NUM_BNF_RULES];	/* Number of BNF Statements */
} ParserData, * pParsData;

/* changed for Karma */
extern Token			lookahead;
extern BufferPointer	stringLiteralTable;
extern intK				line;
extern Token			tokenizer(voidK);
extern stringK			keywordTable[KWT_SIZE];
extern intK				syntaxErrorNumber;
extern intK				numParserErrors;
extern ParserData psData;

/* Function definitions */
voidK startParser(voidK);
voidK matchToken(intK tokenCode, intK tokenAttribute);
voidK syncErrorHandler(intK syncTokenCode);
voidK printError(voidK);
voidK printBNFData(ParserData psData);

/* List of BNF statements */ /* changed for KARMA */
enum BNF_RULES {
	BNF_error = 0,
	BNF_program,
	BNF_comment,
	BNF_mainHeader,
	BNF_optVarListDeclarations,
	BNF_varDeclaration,
	BNF_typeSpec,
	BNF_optionalStatements,
	BNF_statements,
	BNF_statementsPrime,
	BNF_statement,
	BNF_assignmentStatement,
	BNF_printStatement,
	BNF_ifStatement,
	BNF_whileStatement,
	BNF_expression,
	BNF_expressionPrime,
	BNF_term,
	BNF_termPrime,
	BNF_factor,
	BNF_condition,
	BNF_returnStatement,
	BNF_outputVariableList,
	BNF_optParams,
	BNF_paramList
};

extern stringK BNFStrTable[NUM_BNF_RULES];


/* TO_DO: Place ALL non-terminal function declarations */
voidK comment(voidK);
voidK program(voidK);
voidK mainHeader(voidK);
voidK optVarListDeclarations(voidK);
voidK varDeclaration(voidK);
voidK typeSpec(voidK);
voidK optionalStatements(voidK);
voidK statements(voidK);
voidK statementsPrime(voidK);
voidK statement(voidK);
voidK assignmentStatement(voidK);
voidK printStatement(voidK);
voidK ifStatement(voidK);
voidK whileStatement(voidK);
voidK returnStatement(voidK);
voidK outputVariableList(voidK);
voidK condition(voidK);
voidK expression(voidK);
voidK expressionPrime(voidK);
voidK term(voidK);
voidK termPrime(voidK);
voidK factor(voidK);
voidK optParams(voidK);
voidK paramList(voidK);


#endif
