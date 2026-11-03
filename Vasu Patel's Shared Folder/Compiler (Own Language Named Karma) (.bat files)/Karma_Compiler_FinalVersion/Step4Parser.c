#define _CRT_SECURE_NO_WARNINGS

#ifndef COMPILERS_H_
#include "Compilers.h"
#endif

#ifndef SCANNER_H_
#include "Step3Scanner.h"
#endif

#ifndef PARSER_H_
#include "Step4Parser.h"
#endif


Token lookahead;
intK syntaxErrorNumber = 0;
intK numParserErrors = 0;
ParserData psData;


stringK BNFStrTable[NUM_BNF_RULES] = {
	"BNF_error",
	"BNF_program",
	"BNF_comment",
	"BNF_mainHeader",
	"BNF_optVarListDeclarations",
	"BNF_varDeclaration",
	"BNF_typeSpec",
	"BNF_optionalStatements",
	"BNF_statements",
	"BNF_statementsPrime",
	"BNF_statement",
	"BNF_assignmentStatement",
	"BNF_printStatement",
	"BNF_ifStatement",
	"BNF_whileStatement",
	"BNF_expression",
	"BNF_expressionPrime",
	"BNF_term",
	"BNF_termPrime",
	"BNF_factor",
	"BNF_condition",
	"BNF_returnStatement",
	"BNF_outputVariableList",
	"BNF_optParams",
	"BNF_paramList"
};


/* CHANGED: small helpers for clean parsing */
static voidK advanceToken(voidK);
static voidK skipComments(voidK);
static intK isTypeKeyword(voidK);
static intK isStatementStart(voidK);
static intK isExpressionStart(voidK);
static intK isWhileKeyword(voidK);
static voidK matchMainIdentifier(voidK);


static voidK advanceToken(voidK) {
	lookahead = tokenizer();

	while (lookahead.code == ERR_T) {
		printError();
		syntaxErrorNumber++;
		numParserErrors++;
		lookahead = tokenizer();
	}
}

static voidK skipComments(voidK) {
	while (lookahead.code == CMT_T) {
		comment();
	}
}

static intK isTypeKeyword(voidK) {
	return (lookahead.code == KW_T &&
		(lookahead.attribute.keywordIndex == KW_intK ||
			lookahead.attribute.keywordIndex == KW_realK ||
			lookahead.attribute.keywordIndex == KW_stringK ||
			lookahead.attribute.keywordIndex == KW_floatK));
}

static intK isWhileKeyword(voidK) {
	return (lookahead.code == KW_T &&
		(lookahead.attribute.keywordIndex == KW_whileK ||
			lookahead.attribute.keywordIndex == KW_while));
}

static intK isStatementStart(voidK) {
	return (lookahead.code == KW_T &&
		(lookahead.attribute.keywordIndex == KW_if ||
			lookahead.attribute.keywordIndex == KW_printK ||
			isWhileKeyword())) ||
		(lookahead.code == MNID_T);
}

static intK isExpressionStart(voidK) {
	return (lookahead.code == MNID_T ||
		lookahead.code == INL_T ||
		lookahead.code == FPL_T ||
		lookahead.code == LPR_T);
}

static voidK matchMainIdentifier(voidK) {
	if (lookahead.code == MNID_T && strcmp(lookahead.attribute.idLexeme, LANG_MAIN) == 0) {
		advanceToken();
		skipComments();
		return;
	}

	printError();
	syntaxErrorNumber++;
	numParserErrors++;

	while (lookahead.code != LPR_T && lookahead.code != SEOF_T) {
		advanceToken();
		skipComments();
	}
}
















/* changed for karma */
voidK startParser() {
	
	intK i;

	for (i = 0; i < NUM_BNF_RULES; i++) {
		psData.parsHistogram[i] = 0;
	}

	syntaxErrorNumber = 0;
	numParserErrors = 0;

	lookahead = tokenizer();
	while (lookahead.code == CMT_T) {
		comment();
	}

	program();
	matchToken(SEOF_T, NO_ATTR);

	printf("%s%s\n", STR_LANGNAME, ": Source file parsed");

}

/* changed for karma */
voidK matchToken(intK tokenCode, intK tokenAttribute) {
	intK matchFlag = 1;

	if (lookahead.code != tokenCode) {
		matchFlag = 0;
	}
	else if (tokenCode == KW_T && tokenAttribute != NO_ATTR &&
		lookahead.attribute.keywordIndex != tokenAttribute) {
		matchFlag = 0;
	}

	if (matchFlag) {
		if (lookahead.code == SEOF_T) {
			return;
		}

		advanceToken();
		skipComments();
	}
	else {
		syncErrorHandler(tokenCode);
	}
}

/* changed for karma */
voidK syncErrorHandler(intK syncTokenCode) {
	printError();
	syntaxErrorNumber++;
	numParserErrors++;

	while (lookahead.code != syncTokenCode && lookahead.code != SEOF_T) {
		advanceToken();
		skipComments();
	}

	if (lookahead.code != SEOF_T) {
		advanceToken();
		skipComments();
	}
}

/* changed for karma */
voidK printError(voidK) {
	Token t = lookahead;

	printf("%s%s%3d\n", STR_LANGNAME, ": Syntax error:  Line:", line);
	printf("*****  Token code:%3d Attribute: ", t.code);

	switch (t.code) {
	case ERR_T:
		printf("*ERROR*: %s\n", t.attribute.errLexeme);
		break;
	case SEOF_T:
		printf("SEOF_T\t\t%d\t\n", t.attribute.seofType);
		break;
	case MNID_T:
		printf("MNID_T:\t\t%s\n", t.attribute.idLexeme);
		break;
	case INL_T:
		printf("INL_T:\t\t%d\n", t.attribute.intValue);
		break;
	case FPL_T:
		printf("FPL_T:\t\t%g\n", t.attribute.floatValue);
		break;
	case STR_T:
		printf("STR_T:\t\t%s\n", readerGetContent(stringLiteralTable, t.attribute.contentString));
		break;
	case KW_T:
		printf("KW_T:\t\t%s\n", keywordTable[t.attribute.keywordIndex]);
		break;
	case LPR_T:
		printf("LPR_T\n");
		break;
	case RPR_T:
		printf("RPR_T\n");
		break;
	case LBR_T:
		printf("LBR_T\n");
		break;
	case RBR_T:
		printf("RBR_T\n");
		break;
	case EOS_T:
		printf("EOS_T\n");
		break;
	case REL_T:
		printf("REL_T\n");
		break;
	case ART_T:
		printf("ART_T\n");
		break;
	case ASS_T:
		printf("ASS_T\n");
		break;
	default:
		printf("%s%s%d\n", STR_LANGNAME, ": Scanner error: invalid token code: ", t.code);
		break;
	}
}


/* changed for karma */
voidK program(voidK) {
	psData.parsHistogram[BNF_program]++;

	skipComments();

	if (lookahead.code == SEOF_T) {
		printf("%s%s\n", STR_LANGNAME, ": Program parsed");
		return;
	}

	mainHeader();
	optVarListDeclarations();
	optionalStatements();
	returnStatement();
	matchToken(RBR_T, NO_ATTR);

	printf("%s%s\n", STR_LANGNAME, ": Program parsed");
}


/* changed for karma */
voidK mainHeader(voidK) {
	psData.parsHistogram[BNF_mainHeader]++;

	matchToken(KW_T, KW_intK);
	matchMainIdentifier();
	matchToken(LPR_T, NO_ATTR);
	matchToken(RPR_T, NO_ATTR);
	matchToken(LBR_T, NO_ATTR);

	printf("%s%s\n", STR_LANGNAME, ": Main header parsed");
}

/* changed for karma */
voidK comment(voidK) {
	psData.parsHistogram[BNF_comment]++;
	printf("%s%s\n", STR_LANGNAME, ": Comment parsed");
	advanceToken();
}

/* changed for karma */
voidK optParams(voidK) {
	psData.parsHistogram[BNF_optParams]++;
	printf("%s%s\n", STR_LANGNAME, ": Optional param list parsed");
}


/* changed for karma */
voidK paramList(voidK) {
	psData.parsHistogram[BNF_paramList]++;
	printf("%s%s\n", STR_LANGNAME, ": Param list parsed");
}


/* changed for karma */
voidK optVarListDeclarations(voidK) {
	psData.parsHistogram[BNF_optVarListDeclarations]++;

	while (isTypeKeyword()) {
		varDeclaration();
		skipComments();
	}

	printf("%s%s\n", STR_LANGNAME, ": Optional Variable List Declarations parsed");
}


/* changed for karma */
voidK typeSpec(voidK) {
	psData.parsHistogram[BNF_typeSpec]++;

	if (!isTypeKeyword()) {
		syncErrorHandler(MNID_T);
		return;
	}

	matchToken(KW_T, lookahead.attribute.keywordIndex);
	printf("%s%s\n", STR_LANGNAME, ": Type parsed");
}

/* changed for karma */
voidK varDeclaration(voidK) {
	psData.parsHistogram[BNF_varDeclaration]++;

	typeSpec();
	matchToken(MNID_T, NO_ATTR);

	if (lookahead.code == ASS_T) {
		matchToken(ASS_T, NO_ATTR);
		expression();
	}

	matchToken(EOS_T, NO_ATTR);

	printf("%s%s\n", STR_LANGNAME, ": Variable declaration parsed");
}


/* changed for karma */
voidK optionalStatements(voidK) {
	psData.parsHistogram[BNF_optionalStatements]++;

	while (isStatementStart()) {
		statements();
		skipComments();
	}

	printf("%s%s\n", STR_LANGNAME, ": Optional statements parsed");
}

/* changed for karma */
voidK statements(voidK) {
	psData.parsHistogram[BNF_statements]++;
	statement();
	statementsPrime();
	printf("%s%s\n", STR_LANGNAME, ": Statements parsed");
}

/* changed for karma */
voidK statementsPrime(voidK) {
	psData.parsHistogram[BNF_statementsPrime]++;

	if (isStatementStart()) {
		statement();
		statementsPrime();
	}

	printf("%s%s\n", STR_LANGNAME, ": Statements Prime parsed");
}


voidK statement(voidK) {
	psData.parsHistogram[BNF_statement]++;

	if (lookahead.code == KW_T && lookahead.attribute.keywordIndex == KW_if) {
		ifStatement();
	}
	else if (isWhileKeyword()) {
		whileStatement();
	}
	else if (lookahead.code == KW_T && lookahead.attribute.keywordIndex == KW_printK) {
		printStatement();
	}
	else if (lookahead.code == MNID_T) {
		assignmentStatement();
	}
	else if (lookahead.code == KW_T && lookahead.attribute.keywordIndex == KW_moksha) {
		returnStatement();
	}
	else {
		printError();
		syntaxErrorNumber++;
		numParserErrors++;
		advanceToken();
	}

	printf("%s%s\n", STR_LANGNAME, ": Statement parsed");
}

voidK assignmentStatement(voidK) {
	psData.parsHistogram[BNF_assignmentStatement]++;

	matchToken(MNID_T, NO_ATTR);
	matchToken(ASS_T, NO_ATTR);
	expression();
	matchToken(EOS_T, NO_ATTR);

	printf("%s%s\n", STR_LANGNAME, ": Assignment statement parsed");
}


voidK printStatement(voidK) {
	psData.parsHistogram[BNF_printStatement]++;

	matchToken(KW_T, KW_printK);
	matchToken(LPR_T, NO_ATTR);
	outputVariableList();
	matchToken(RPR_T, NO_ATTR);
	matchToken(EOS_T, NO_ATTR);

	printf("%s%s\n", STR_LANGNAME, ": Output statement parsed");
}

voidK outputVariableList(voidK) {
	psData.parsHistogram[BNF_outputVariableList]++;

	if (lookahead.code == STR_T) {
		matchToken(STR_T, NO_ATTR);
	}
	else if (isExpressionStart()) {
		expression();
	}

	printf("%s%s\n", STR_LANGNAME, ": Output variable list parsed");
}

voidK ifStatement(voidK) {
	psData.parsHistogram[BNF_ifStatement]++;

	matchToken(KW_T, KW_if);
	matchToken(LPR_T, NO_ATTR);      
	condition();
	matchToken(RPR_T, NO_ATTR);      
	matchToken(LBR_T, NO_ATTR);      
	optionalStatements();
	matchToken(RBR_T, NO_ATTR);     

	if (lookahead.code == KW_T && lookahead.attribute.keywordIndex == KW_else) {
		matchToken(KW_T, KW_else);
		matchToken(LBR_T, NO_ATTR);  
		optionalStatements();
		matchToken(RBR_T, NO_ATTR);  
	}

	printf("%s%s\n", STR_LANGNAME, ": If statement parsed");
}

voidK whileStatement(voidK) {
	psData.parsHistogram[BNF_whileStatement]++;

	if (isWhileKeyword()) {
		matchToken(KW_T, lookahead.attribute.keywordIndex);
	}
	else {
		syncErrorHandler(LPR_T);
		return;
	}

	matchToken(LPR_T, NO_ATTR);
	condition();
	matchToken(RPR_T, NO_ATTR);
	matchToken(LBR_T, NO_ATTR);
	optionalStatements();
	matchToken(RBR_T, NO_ATTR);

	printf("%s%s\n", STR_LANGNAME, ": While statement parsed");
}

voidK returnStatement(voidK) {
	psData.parsHistogram[BNF_returnStatement]++;

	matchToken(KW_T, KW_moksha);
	matchToken(EOS_T, NO_ATTR);

	printf("%s%s\n", STR_LANGNAME, ": Return statement parsed");
}

voidK condition(voidK) {
	psData.parsHistogram[BNF_condition]++;

	expression();
	matchToken(REL_T, NO_ATTR);
	expression();

	printf("%s%s\n", STR_LANGNAME, ": Condition parsed");
}

voidK expression(voidK) {
	psData.parsHistogram[BNF_expression]++;
	term();
	expressionPrime();
	printf("%s%s\n", STR_LANGNAME, ": Expression parsed");
}

voidK expressionPrime(voidK) {
	psData.parsHistogram[BNF_expressionPrime]++;

	while (lookahead.code == ART_T &&
		(lookahead.attribute.arithmeticOperator == OP_ADD ||
			lookahead.attribute.arithmeticOperator == OP_SUB)) {
		matchToken(ART_T, NO_ATTR);
		term();
	}

	printf("%s%s\n", STR_LANGNAME, ": Expression Prime parsed");
}

voidK term(voidK) {
	psData.parsHistogram[BNF_term]++;
	factor();
	termPrime();
	printf("%s%s\n", STR_LANGNAME, ": Term parsed");
}

voidK termPrime(voidK) {
	psData.parsHistogram[BNF_termPrime]++;

	while (lookahead.code == ART_T &&
		(lookahead.attribute.arithmeticOperator == OP_MUL ||
			lookahead.attribute.arithmeticOperator == OP_DIV)) {
		matchToken(ART_T, NO_ATTR);
		factor();
	}

	printf("%s%s\n", STR_LANGNAME, ": Term Prime parsed");
}

voidK factor(voidK) {
	psData.parsHistogram[BNF_factor]++;

	switch (lookahead.code) {
	case MNID_T:
		matchToken(MNID_T, NO_ATTR);
		break;
	case INL_T:
		matchToken(INL_T, NO_ATTR);
		break;
	case FPL_T:
		matchToken(FPL_T, NO_ATTR);
		break;
	case LPR_T:
		matchToken(LPR_T, NO_ATTR);
		expression();
		matchToken(RPR_T, NO_ATTR);
		break;
	default:
		printError();
		syntaxErrorNumber++;
		numParserErrors++;
		advanceToken();
		break;
	}

	printf("%s%s\n", STR_LANGNAME, ": Factor parsed");
}

voidK dataSession(voidK) {
	psData.parsHistogram[BNF_error]++;
	printf("%s%s\n", STR_LANGNAME, ": Data Session parsed");
}

voidK codeSession(voidK) {
	psData.parsHistogram[BNF_error]++;
	printf("%s%s\n", STR_LANGNAME, ": Code Session parsed");
}


voidK printBNFData(ParserData psData) {
	/* Print Parser statistics */
	printf("Statistics:\n");
	printf("----------------------------------\n");
	int cont = 0;
	for (cont = 0; cont < NUM_BNF_RULES; cont++) {
		if (psData.parsHistogram[cont] > 0)
			printf("%s%s%s%d%s", "Token[", BNFStrTable[cont], "]=", psData.parsHistogram[cont], "\n");
	}
	printf("----------------------------------\n");
}
