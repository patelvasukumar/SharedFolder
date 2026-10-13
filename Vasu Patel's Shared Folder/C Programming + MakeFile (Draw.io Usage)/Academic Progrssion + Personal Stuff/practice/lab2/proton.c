/**
 * Student Name: Kavya Vasani
 * Student Number: 041163941
 * Course: CST8234 - Foundational Programming in C
 * Lab 2: Currency Exchange Calculator
 * Description: Command-line tool to convert currency using exchange rates.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

void show_help() {
    printf("Usage: moneychange [OPTION]... AMOUNT EXCHANGE [EXCHANGE]\n");
    printf("Convert from one currency to another (by default convert the AMOUNT into USD).\n\n");
    printf("Options:\n");
    printf("  -l, --long             use long format output.\n");
    printf("  -s, --symbol SYMBOL    add three letter currency code to output.\n");
    printf("  --help                 display this help and exit.\n\n");
    printf("Exit status:\n");
    printf("  0    if OK,\n");
    printf("  1    if problem encountered.\n");
    printf("\nmoneychange by Kavya Vasani.\n");
}

int is_valid_symbol(const char *symbol) {
    int i;
    if (strlen(symbol) != 3) return 0;
    for (i = 0; i < 3; i++) {
        if (!isalpha((unsigned char)symbol[i])) return 0;
    }
    return 1;
}

int main(int argc, char *argv[]) {
    int long_format = 0;
    char currency_symbol[4] = "";
    double amount = 0.0, rate1 = 0.0, rate2 = 0.0;
    int arg_index = 1;
    int i;
    
    /* Handle --help */
    for (i = 1; i < argc; i++) {
        if (strcmp(argv[i], "--help") == 0) {
            show_help();
            return 0;
        }
    }

    /* Parse options */
    while (arg_index < argc && argv[arg_index][0] == '-') {
        if (strcmp(argv[arg_index], "-l") == 0 || strcmp(argv[arg_index], "--long") == 0) {
            long_format = 1;
        }
        else if (strcmp(argv[arg_index], "-s") == 0 || strcmp(argv[arg_index], "--symbol") == 0) {
            arg_index++;
            if (arg_index >= argc || argv[arg_index][0] == '-') {
                printf("moneychange: invalid currency symbol\n");
                printf("Try 'moneychange --help' for more information.\n");
                return 1;
            }
            if (!is_valid_symbol(argv[arg_index])) {
                printf("moneychange: invalid currency symbol\n");
                printf("Try 'moneychange --help' for more information.\n");
                return 1;
            }
            strncpy(currency_symbol, argv[arg_index], 3);
            currency_symbol[3] = '\0';
        }
        else {
            printf("Try 'moneychange --help' for more information.\n");
            return 1;
        }
        arg_index++;
    }

    /* Remaining arguments: amount and exchange rates */
    {
        int positional_args = argc - arg_index;
        if (positional_args < 2 || positional_args > 3) {
            printf("Try 'moneychange --help' for more information.\n");
            return 1;
        }

        amount = atof(argv[arg_index]);
        rate1 = atof(argv[arg_index + 1]);
        if (positional_args == 3) {
            rate2 = atof(argv[arg_index + 2]);
        }
    }

    /* Perform conversion */
    {
        double result = 0.0;
        if (rate2 > 0.0) {
            result = amount * rate1 / rate2;
        } else {
            result = amount * rate1;
        }

        /* Output result */
        if (long_format) {
            printf("%.2f becomes %.2f", amount, result);
        } else {
            printf("%.2f", result);
        }

        if (strlen(currency_symbol) > 0) {
            printf("%s", currency_symbol);
        }

        printf("\n");
    }

    return 0;
}
