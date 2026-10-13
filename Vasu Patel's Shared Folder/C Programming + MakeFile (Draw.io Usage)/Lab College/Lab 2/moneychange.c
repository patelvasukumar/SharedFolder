/*
Student Name / Programmer : Vasukumar Patel
Student Number : 041153940
Program description : this program calculates money for different currencies with their exchange rate
C programming - Lab 2
Lab Professor : Surbhi Bahri
Due Date : 15th June 2025
*/

#include <stdio.h>  /* [ standard input/output ], and this library contain those functions which deals with  input, output and file. */

#include <stdlib.h> /* [ standard library ], and contains many functions like atoi(), atof(), malloc(), calloc(), free() */

#include <string.h> /* [ String library ], and contains those functions which manipulate String like strcmp() which compare the string by using acsi values */

#include <ctype.h>  /* [ Character Type Library ], and it contains mostly functions for checking the character and numbers */

/* The programmer has decided to make two extra methods for the program */
void printHelp(); /* function declaration as per C89 standards. To print the help menu */
void calculationExchange(); /* function declaration as per C89 standards. To do the calculation and print on the console  */

/* global variable declaration */
    float userValue;
    float firstRate;
    float secondRate;
    float result;
    char *syambol = NULL; /* null pointer  */
    int index = 1;  /* cannot start from zero because it is reserved for the program name */
    int values = 0;
    long longFormat = 0;

/* in this method, we return 1 when the user did not enter the values according to the expectation and
return 0 if everything is okay. */
int main(int arguments, char *pointers[]) /* Array decay happens by passing array to the parameters */

{

    if (arguments == 1) { /* if there is only the name of the program  */
        printf("\nTry 'moneychange --help' for more information.\n\n");
        return 1;
    }

    while (index < arguments) { /* while loop to loop through every arguments */

        if (
        
            strcmp(pointers[index], "--help") == 0
        
        ) {   /* call the function and print the help menu */
           
            printHelp();
            return 0;
        
        } else if (
            
            strcmp(pointers[index], "-l") == 0 || strcmp(pointers[index], "--long") == 0
        
        ) { /* this in-build function compare the string with command line argument and returns 0 if successful */
        /*
         when String is same, returns acsi value zero

         when String are not same :
         when first string acsi is bigger than second, returns positive acsi value
         when second string acsi is smaller than first, returns negative acsi value
        
        */
            longFormat = 1; /* set long to 1. So, it becomes true in the if condition */
            index++; /* increasing the index for other arguments */
        
        } else if (    
            
            strcmp(pointers[index], "-s") == 0 
                || strcmp(pointers[index], "--symbol") == 0 
                || strcmp(pointers[index], "--SYMBOL") == 0
           /* comparing command line arguments with String  */
        ) {
           
            index++; /* increase the index for upcoming arguments */

                if (index >= arguments)  
                {
                    printf("\nTry 'moneychange --help' for more information.\n\n");
                    return 1;
                }

                if (
                    strlen(pointers[index]) == 3 &&
                    isupper( (unsigned) pointers[index][0]) &&
                    isupper( (unsigned) pointers[index][1]) &&
                    isupper( (unsigned) pointers[index][2])
                    ) /* checking String length, it must be three after -s, --syambol/SYAMBOL */
                    {
                            
                            syambol = pointers[index]; /* storing the address of the pointer */
            
                    } else  /* else statement */
                    {
                
                        printf("\nmoneychange: invalid currency symbol\n");
                        printf("Try 'moneychange --help' for more information.\n\n");
                        return 1;
                        
                    }   

            index++;
            
        } else if ( 
            
            isdigit( (unsigned) pointers[index][0] ) || pointers[index][0] == '.' 
        
        ) {  /* checking the digits and the dot [ . ] charatcter */

            switch (values) {
                    
                case 0:   
                        
                        userValue = atof(pointers[index]);  /* adding currency value */
                        break;
                
                case 1:
                        
                        firstRate = atof(pointers[index]); /* first conversion rate */
                        break;
    
                case 2:
        
                        secondRate = atof(pointers[index]); /* second converion rate */
                        break;
    
                default:
                        
                        /* Error handling */
                        printf("\nTry 'moneychange --help' for more information.\n\n");
                        return 1;

                    }

                values++; /* increasing all values by one */
                index++;
            
        } else {

        /* handling errors  */
        printf("\nTry 'moneychange --help' for more information.\n\n");
        return 1;
    
    }

    }

    calculationExchange(); /* calling  the calculation() method for the calculation phase of the development */

    return 0;
}




void printHelp() /* printing the menu */
{
    printf("\nUsage: moneychange [OPTION]... AMOUNT EXCHANGE [EXCHANGE]\n");
    printf("\nConvert from one currency to another (default to USD).\n");
    printf("\nOptions:\n");
    printf("  -l, --long             use long format output\n");
    printf("  -s, --symbol SYMBOL    add three-letter currency code\n");
    printf("\nExit status:\n");
    printf("  0  if OK,\n");
    printf("  1  if problem encountered.\n");
    printf("\nthe moneychange program is made by Vasukumar Patel.\n\n");
}

void calculationExchange() /* do the calculation. If error happens, it uses the exit(1) method to shut down the prgram where 1 means error. it will return 1 to the code status. */
{

    /* if the user is using options and the user does not enter currecny and its conversion rate */
    if (values < 2) { 

        printf("\nTry 'moneychange --help' for more information.\n\n");
        exit(1);
    }

    result = userValue * firstRate;

    /* if the third value is provided by the user and it is divided by zero */
    if (values == 3) {
        
        if (secondRate == 0) {
            printf("\nYou cannot devide by zero\n\n");
            exit(1);
        }
       
        result = result / secondRate;
    
    }

    /* checking whether the variable is null or not  */
    if (syambol != NULL) {
        
        if (longFormat) { /* first block : if the format is long , second block : if it normal */
        
            printf("\n%.2f becomes %.2f %s\n\n", userValue, result, syambol);
        
        } else {
        
            printf("\n%.2f %s\n\n", result, syambol);
        
        }
    
    } 
    else
    {
        if (longFormat) { /* first block : if the format is long , second block : if it normal */
         
            printf("\n%.2f becomes %.2f USD\n\n", userValue, result);
        
        } else {
         
            printf("\n%.2f USD\n\n", result);
        
        }
    
    }


}


/*

The programmer used these sources as a reference and knowledge source to develop the program. 

The list of references :
1. https://www.geeksforgeeks.org/command-line-arguments-in-c-cpp/
2. https://www.geeksforgeeks.org/ctype-hcctype-library-in-c-c-with-examples/
3. https://www.geeksforgeeks.org/c/c-library-string-h/
4. https://www.geeksforgeeks.org/c/atol-atoll-and-atof-functions-in-c-c/
5. https://www.geeksforgeeks.org/c/strcmp-in-c/
6. https://www.geeksforgeeks.org/c/strlen-function-in-c/
7. https://www.geeksforgeeks.org/c/convert-string-to-int-in-c/

1. https://en.cppreference.com/w/cpp/string/byte/toupper [ why do we need to cast to unsigned char for ctype.h ]
2. https://www.sanfoundry.com/c-tutorials-subscript-context-arrays/ [ Array subscript ]


*/