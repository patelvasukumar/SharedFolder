#ifndef WRITER_H_
#include "Step5Writer.h"
#endif

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

/* Global variables */
Variable variables[MAX_VARS];
intK var_count = 0;
intK initial_phase = 1; // Flag to track the initial phase
charK output_buffer[MAX_EXPR_LEN * 10] = { 0 }; // Buffer to store write output

/* Finds variables */
intK find_variable(const stringK name) {
    intK i = 0;
    for (i = 0; i < var_count; ++i) {
        if (strcmp(variables[i].name, name) == 0) {
            return i;
        }
    }
    return -1;
}

/* Assign numerical variable */
void assign_numeric_variable(const stringK name, doubleK value) {
    intK idx = find_variable(name);
    if (idx == -1) {
        idx = var_count++;
        strcpy(variables[idx].name, name);
    }
    variables[idx].type = NUMERIC;
    variables[idx].value.num_value = value;
}

/* Assign string variable */
void assign_string_variable(const stringK name, const stringK value) {
    intK idx = find_variable(name);
    if (idx == -1) {
        idx = var_count++;
        strcpy(variables[idx].name, name);
    }
    variables[idx].type = STRING;
    strncpy(variables[idx].value.str_value, value, sizeof(variables[idx].value.str_value) - 1); 
    variables[idx].value.str_value[sizeof(variables[idx].value.str_value) - 1] = EOS; 
}

/* Assign boolean variable */
void assign_boolean_variable(const stringK name, intK value) {
    intK idx = find_variable(name);
    if (idx == -1) {
        idx = var_count++;
        strcpy(variables[idx].name, name);
    }
    variables[idx].type = BOOLEAN;
    variables[idx].value.bool_value = value;
}

intK get_boolean_value(const stringK name) {
    intK idx = find_variable(name);
    if (idx != -1 && variables[idx].type == BOOLEAN) {
        return variables[idx].value.bool_value;
    }
    return 0; // Default to false if variable not found
}

/* Assign char variable */
void assign_char_variable(const stringK name, charK value) {
    intK idx = find_variable(name);
    if (idx == -1) {
        idx = var_count++;
        strcpy(variables[idx].name, name);
    }
    variables[idx].type = CHAR;
    variables[idx].value.char_value = value;
}

/* Gets char variable */
charK get_char_value(const stringK name) {
    intK idx = find_variable(name);
    if (idx != -1 && variables[idx].type == CHAR) {
        return variables[idx].value.char_value;
    }
    return EOS; // Default to null character if variable not found
}

/* Gets numerical value */
doubleK get_numeric_value(const stringK name) {
    intK idx = find_variable(name);
    if (idx != -1 && variables[idx].type == NUMERIC) {
        return variables[idx].value.num_value;
    }
    return ZERO;
}

/* Gets string variable */
const stringK get_string_value(const stringK name) {
    intK idx = find_variable(name);
    if (idx != -1 && variables[idx].type == STRING) {
        return variables[idx].value.str_value;
    }
    return STREMPTY;
}

/* Parsing for term */
doubleK parse_term(stringK* expr) {
    doubleK value = 0.0;
    while (isspace(**expr)) (*expr)++;
    if (**expr == LPAR) {
        (*expr)++;
        value = parse_expression(expr);
        if (**expr == RPAR) {
            (*expr)++;
        }
    }
    else if (isalpha((unsigned char)**expr)) {
        charK var_name[32] = { 0 };
        intK i = 0;

        while (isalnum((unsigned char)**expr) || **expr == '%') {
            if (i < 31) {
                var_name[i++] = *(*expr)++;
            }
            else {
                (*expr)++;
            }
        }
        var_name[i] = EOS;
        value = get_numeric_value(var_name);
    }
    else {
        value = strtod(*expr, expr);
    }
    while (isspace(**expr)) (*expr)++;
    while (**expr == MULT || **expr == DIV) {
        charK op = *(*expr)++;
        doubleK next_value = parse_term(expr);
        if (op == MULT) {
            value *= next_value;
        }
        else {
            if (next_value != 0)
                value /= next_value;
            else {
                printf("Error: division by zero! ");
                value = 0;
            }
        }
    }
    return value;
}

/* Parsing for expression */
doubleK parse_expression(stringK* expr) {
    doubleK value = parse_term(expr);
    while (isspace(**expr)) (*expr)++;
    while (**expr == PLUS || **expr == MINUS) {
        charK op = *(*expr)++;
        doubleK next_value = parse_term(expr);
        if (op == PLUS) {
            value += next_value;
        }
        else {
            value -= next_value;
        }
    }
    return value;
}

/* Write output */
voidK handle_write(stringK expression) {
    charK buffer[MAX_EXPR_LEN] = { 0 };

    charK* lpar = strchr(expression, LPAR);  
    charK* rpar = strrchr(expression, RPAR);

    if (lpar != NULL && rpar != NULL && lpar < rpar) {
        charK* start = lpar + 1;
        charK* end = rpar;
        
        charK temp[MAX_EXPR_LEN];

        strncpy(temp, start, end - start);
        temp[end - start] = EOS;

        start = temp;

        while (*start != EOS) {
            if (*start == QUOTES || *start == DQUOTES) {
                charK quote = *start; 
                start++;
                while (*start != quote && *start != EOS) {
                    strncat(buffer, start, 1);
                    start++;
                }
                if (*start == quote) {
                    start++;
                }
            }
            else if (isalnum((unsigned char)*start)) {
                charK var_name[32] = { 0 };
                intK i = 0;

                while (isalnum((unsigned char)*start) || *start == '%') {
                    if (i < 31) {
                        var_name[i++] = *start++;
                    }
                    else {
                        start++;
                    }
                }
                var_name[i] = EOS;

                intK var_idx = find_variable(var_name);

                if (var_idx != -1) {
                    if (variables[var_idx].type == STRING) {
                        strcat(buffer, get_string_value(var_name));
                    }
                    else if (variables[var_idx].type == NUMERIC) {
                        charK num_str[64];

                        doubleK num = get_numeric_value(var_name);
                        if (num == (intK)num) {
                            snprintf(num_str, sizeof(num_str), "%d", (intK)num);
                        }
                        else {
                            snprintf(num_str, sizeof(num_str), "%.2lf", num);
                        }

                        strcat(buffer, num_str);
                    }
                    else if (variables[var_idx].type == BOOLEAN) {
                        strcat(buffer, get_boolean_value(var_name) ? TRUE : FALSE);
                    }
                    else if (variables[var_idx].type == CHAR) {
                        charK char_str[2] = { get_char_value(var_name), EOS };
                        strcat(buffer, char_str);
                    }
                }
                else {
                    strcat(buffer, "[Undefined]");
                }
            }
            else if (isspace((unsigned char)*start)) {
                strncat(buffer, start, 1); 
                start++;
            }
            else {
                start++;
            }
        }
    }

    if (initial_phase) {
        strcat(output_buffer, buffer); 
        if (strlen(buffer) == 0) {
            strcat(output_buffer, "[Undefined]");
        }
        strcat(output_buffer, "\n");
    }
    else {
        printf("%s\n", buffer);
    }
}

/* Calculate expression */
voidK calculate(stringK expression) {
    if (expression == NULL) {
        return;
    }

    while (isspace((unsigned char)*expression)) {
        expression++;
    }

    if (*expression == EOS || *expression == NEWLINE || *expression == RETURN) {
        return;
    }


    if (strncmp(expression, "data", 4) == 0 ||
        strncmp(expression, "code", 4) == 0 ||
        strncmp(expression, "whileK", 6) == 0 ||  
        strncmp(expression, "moksha", 6) == 0 ||
        *expression == '{' ||
        *expression == '}') {
        return;
    }

    charK var_name[32] = { 0 };

    if (strchr(expression, EQUALS)) {
       
        sscanf(expression, "%31s =", var_name);

        stringK expr = strchr(expression, EQUALS) + 1;
        while (isspace((unsigned char)*expr)) expr++;

        if (*expr == DQUOTES) {
            expr++;
            charK str_value[256] = { 0 };
            intK i = 0;
            while (*expr != DQUOTES && *expr != EOS) {
                if (i < 255) {
                    str_value[i++] = *expr++;
                }
                else {
                    expr++;
                }
            }
            assign_string_variable(var_name, str_value);
            if (!initial_phase) {
                printf("%s = \"%s\"\n", var_name, str_value);
            }
        }
        else if (strncmp(expr, TRUE, 4) == 0 || strncmp(expr, FALSE, 5) == 0) {
            intK bool_value = (strncmp(expr, TRUE, 4) == 0) ? 1 : 0;
            assign_boolean_variable(var_name, bool_value);
            if (!initial_phase) {
                printf("%s = %s\n", var_name, bool_value ? TRUE : FALSE);
            }
        }
        else if (*expr == QUOTES) {
            expr++;
            charK char_value = *expr;
            assign_char_variable(var_name, char_value);
            if (!initial_phase) {
                printf("%s = '%c'\n", var_name, char_value);
            }
        }
        else {
            doubleK value = parse_expression(&expr);
            assign_numeric_variable(var_name, value);
            if (!initial_phase) {
                if (value == (intK)value) {
                    printf("%s = %d\n", var_name, (intK)value);   
                }
                else {
                    printf("%s = %.2lf\n", var_name, value);      
                }
            }
        }
    }
    else if (strstr(expression, WRITE)) {
        handle_write(expression);
    }
    else {
        stringK expr = expression;
        doubleK result = parse_expression(&expr);
        if (!initial_phase) {
            printf("Result: %.2lf\n", result);
        }
    }
}

/* Process input file */
voidK process_file(const stringK filename) {
    FILE* file = fopen(filename, "r");
    if (file == NULL) {
        perror("Error opening file");
        exit(EXIT_FAILURE);
    }
    charK line[MAX_EXPR_LEN];
    while (fgets(line, sizeof(line), file)) {
        if (line[0] == NEWLINE || line[0] == RETURN) {
            continue; // Skip empty lines
        }
        line[strcspn(line, "\n")] = 0; // Remove newline character
        calculate(line);
    }
    fclose(file);
    initial_phase = 0; // End of initial phase
    printf("%s", output_buffer); // Print the buffered write output
    printf("\nVariable values:\n");
	intK i = 0;
    for (i = 0; i < var_count; i++) {
        if (variables[i].type == STRING) {
            printf("%s = \"%s\"\n", variables[i].name, variables[i].value.str_value);
        }
        else if (variables[i].type == NUMERIC) {
            
            if (variables[i].value.num_value == (intK)variables[i].value.num_value) {
                printf("%s = %d\n", variables[i].name, (intK)variables[i].value.num_value);   
            }
            else {
                printf("%s = %.2lf\n", variables[i].name, variables[i].value.num_value);      
            }


        }
        else if (variables[i].type == BOOLEAN) {
            printf("%s = %s\n", variables[i].name, variables[i].value.bool_value ? "true" : "false");
        }
        else if (variables[i].type == CHAR) {
            printf("%s = '%c'\n", variables[i].name, variables[i].value.char_value);
        }
    }
}

/* Split lines into components */
stringK* splitIntoLines(const stringK content, intK* lineCount) {
    stringK* lines = malloc(MAX_LINES * sizeof(char*));
    if (!lines) {
        perror("Error allocating memory");
        return NULL;
    }
    *lineCount = 0;
    const charK* start = content;
    const charK* end;
    while ((end = strchr(start, NEWLINE)) != NULL) {
        if (*lineCount >= MAX_LINES) {
            fprintf(stderr, "Exceeded maximum number of lines\n");
            break;
        }
        intK lineLength = (intK) (end - start);
        lines[*lineCount] = malloc(lineLength + 1);
        if (!lines[*lineCount]) {
            perror("Error allocating memory for line");
            break;
        }
        // Use strncpy_s for safer copying
        strncpy(lines[*lineCount], start, lineLength); 
        lines[*lineCount][lineLength] = EOS; 
        
        (*lineCount)++;
        start = end + 1;
    }
    // Handle the last line if it doesn't end with a newline
    if (*start != EOS && *lineCount < MAX_LINES) {
        size_t lineLength = strlen(start);
        lines[*lineCount] = malloc(lineLength + 1);
        if (!lines[*lineCount]) {
            perror("Error allocating memory for line");
        }
        else {
            
            strcpy(lines[*lineCount], start); 
            (*lineCount)++;

        }
    }
    return lines;
}

/* Free lines */
voidK freeLines(stringK* lines, intK lineCount) {
    intK i = 0;
    for (i = 0; i < lineCount; i++) {
        free(lines[i]);
    }
    free(lines);
}

/* Process content */
voidK process_content(stringK fileContent) {
    if (fileContent == NULL || *fileContent == EOS) {
        printf("Empty file.\n");  
        return;
    }

    intK lineCount = 0;
    stringK* lines = splitIntoLines(fileContent, &lineCount);
   

    stringK line = NULL;

    if (!lines) {
        return;
    }

    printf("\nLines from content:\n");

    intK execute = 1;
    intK ifResult = 0;
    intK waitingElse = 0;
    intK inElse = 0;

    intK i = 0;
    for (i = 0; i < lineCount; i++) {
        line = lines[i];
        charK* p = line;

        while (isspace((unsigned char)*p)) p++;

        if (*p == EOS) {
            continue;
        }

        if (*p == '@') {
            continue;
        }

        if (strncmp(p, "whileK", 6) == 0) {
            intK bodyStart = i + 1;
            intK bodyEnd = i;
            intK depth = 1;
            intK j = i + 1;

            while (j < lineCount) {
                charK* t = lines[j];
                while (isspace((unsigned char)*t)) t++;

                if (strchr(t, '{')) depth++;
                if (strchr(t, '}')) {
                    depth--;
                    if (depth == 0) {
                        bodyEnd = j - 1;
                        break;
                    }
                }
                j++;
            }

            while (1) {
                charK tempLine[MAX_EXPR_LEN];
                strcpy(tempLine, p);

                if (!evaluateCondition(tempLine)) break;

                for (intK k = bodyStart; k <= bodyEnd; k++) {
                    charK* b = lines[k];
                    while (isspace((unsigned char)*b)) b++;

                    if (*b == EOS || *b == '@' || *b == '{' || *b == '}') continue;

                    if (strncmp(b, "data", 4) == 0 ||
                        strncmp(b, "code", 4) == 0 ||
                        strncmp(b, "moksha", 6) == 0 ||
                        strncmp(b, "whileK", 6) == 0) {
                        continue;
                    }

                    calculate(b);
                }
            }

            i = j;
            continue;
        }

        if (strncmp(p, "if", 2) == 0) {
            ifResult = evaluateCondition(p);
            execute = ifResult;
            waitingElse = 1;
            inElse = 0;
            continue;
        }

        if (strncmp(p, "else", 4) == 0) {
            execute = !ifResult;
            inElse = 1;
            continue;
        }

        if (*p == '{' || *p == '}') {
            if (*p == '}' && inElse) {
                execute = 1;
                waitingElse = 0;
                inElse = 0;
            }
            continue;
        }

        if (strncmp(p, "data", 4) == 0 ||
            strncmp(p, "code", 4) == 0 ||
            strncmp(p, "moksha", 6) == 0) {
            continue;
        }

        if (execute) {
            calculate(p);
        }
    }

    initial_phase = 0;
    printf("%s", output_buffer);
    printf("\nVariable values:\n");

    for (i = 0; i < var_count; i++) {
        if (variables[i].type == STRING) {
            printf("%s = \"%s\"\n", variables[i].name, variables[i].value.str_value);
        }
        else if (variables[i].type == NUMERIC) {
            if (variables[i].value.num_value == (intK)variables[i].value.num_value) {
                printf("%s = %d\n", variables[i].name, (intK)variables[i].value.num_value);
            }
            else {
                printf("%s = %.2lf\n", variables[i].name, variables[i].value.num_value);
            }
        }
        else if (variables[i].type == BOOLEAN) {
            printf("%s = %s\n", variables[i].name, variables[i].value.bool_value ? "true" : "false");
        }
        else if (variables[i].type == CHAR) {
            printf("%s = '%c'\n", variables[i].name, variables[i].value.char_value);
        }
    }
}


intK evaluateCondition(stringK expression) {
    charK* start = strchr(expression, '(');
    charK* end = strrchr(expression, ')');

    if (start && end && start < end) {
        *end = EOS;
        start++;

        if (strstr(start, "<=")) {
            charK left[64], right[64];
            sscanf(start, "%[^<]<=%s", left, right);

            stringK l = left;
            stringK r = right;

            doubleK lv = parse_expression(&l);
            doubleK rv = parse_expression(&r);

            return lv <= rv;
        }

        if (strchr(start, '<')) {
            charK left[64], right[64];
            sscanf(start, "%[^<]<%s", left, right);

            stringK l = left;
            stringK r = right;

            doubleK lv = parse_expression(&l);
            doubleK rv = parse_expression(&r);

            return lv < rv;
        }

        if (strstr(start, ">=")) {
            charK left[64], right[64];
            sscanf(start, "%[^>]>=%s", left, right);

            stringK l = left;
            stringK r = right;

            doubleK lv = parse_expression(&l);
            doubleK rv = parse_expression(&r);

            return lv >= rv;
        }

        if (strchr(start, '>')) {
            charK left[64], right[64];
            sscanf(start, "%[^>]>%s", left, right);

            stringK l = left;
            stringK r = right;

            doubleK lv = parse_expression(&l);
            doubleK rv = parse_expression(&r);

            return lv > rv;
        }

        doubleK result = parse_expression(&start);
        return (result != 0);
    }

    return 0;
}

void printNumber(doubleK value) {
    if (value == (intK)value) {
        printf("%d", (intK)value);   
    }
    else {
        printf("%.2lf", value);      
    }
}
