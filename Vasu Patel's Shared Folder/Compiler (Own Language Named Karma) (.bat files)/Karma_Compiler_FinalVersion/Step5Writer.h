#ifndef COMPILERS_H_
#include "Compilers.h"
#endif

#ifndef WRITER_H_
#define WRITER_H_

#define MAX_VARS 100
#define MAX_EXPR_LEN 256
#define MAX_LINES 100

#define WRITE "printK"

#define EOS '\0'
#define ZERO 0.0
#define STREMPTY ""
#define LPAR '('
#define RPAR ')'
#define PLUS '+'
#define MINUS '-'
#define MULT '*'
#define DIV '/'

#define EQUALS '='
#define MOD '%'
#define SPACE ' '
#define TAB '\t'
#define NEWLINE '\n'
#define RETURN '\r'
#define QUOTES '\''    /* KARMA */
#define DQUOTES '"'    /* KARMA */

#define TRUE "true"
#define FALSE "false"

typedef enum { NUMERIC, STRING, BOOLEAN, CHAR } VarType;

typedef struct {
    charK name[32];
    VarType type;
    union {
        doubleK num_value;
        charK str_value[256];
        intK     bool_value; // For BOOLEAN type (1 for true, 0 for false)
        charK char_value; // For CHAR type
    } value;
} Variable;

intK find_variable(const stringK name);

void assign_boolean_variable(const stringK name, intK value);
void assign_char_variable(const stringK name, charK value);
void assign_numeric_variable(const stringK name, doubleK value);
void assign_string_variable(const stringK name, const stringK value);

intK get_boolean_value(const stringK name);
charK get_char_value(const stringK name);
doubleK get_numeric_value(const stringK name);
const stringK get_string_value(const stringK name);

doubleK parse_term(stringK* expr);
doubleK parse_expression(stringK* expr);
void calculate(stringK expression);
void handle_write(stringK expression);
void process_file(const stringK filename);
void process_content(stringK content);
intK evaluateCondition(stringK expression);
void printNumber(doubleK value);

#endif
