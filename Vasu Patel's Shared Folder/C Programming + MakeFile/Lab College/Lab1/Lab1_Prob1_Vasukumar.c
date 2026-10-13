/* importing the methods from static library like copy and pasting
*/
#include <stdio.h>

/* 
for this project the programmer has used all local variables.
*/

/*
defining micros which is going to replace during preprocessing with the name
this one has the name and the parameters which are going to replace by variables and it will the the 
following code brackets functions
*/
#define ADD(a,b) (a+b)
#define MINUS(a,b) (a-b)
#define MULTI(a,b) (a*b)
#define DIVIDE(a,b) (a/b)

/*
 main() method with int return type and returning 0 exit number. it method also calls
 prebuild methods from static library and copy and pasting during the compiling in the object file.
*/
 int main() 
 {

    /*
    signed keyword that tells int can be positive and negative 
    However, unsigned means it (choice variable) only takes positive number including 0. 
    */
    signed int first;
    signed int second;
    unsigned int choice; 
    signed int result;

    /* printf() calling the method from static library and passing arguments
    */
    printf("Enter the first Integer: ");
    /* scanf() calling the method from static library and passing arguments
      used scanf syntax to store the user input into the variable */
    scanf("%d",&first);
    
    printf("Enter the second Integer: ");
    scanf("%d",&second);

    printf("\nEnter your choice");
    printf("\n1 for addition ");
    printf("\n2 for subtraction ");
    printf("\n3 for multiplication ");
    printf("\n4 for division\n");
    printf("> ");
    scanf("%u",&choice);

    /*
    Switch case is useful when the condition is based on a single character, number or single input.
    The programmer used switch case here because there are 4 situations according to the user's input. Then,
    for other, input it is default error message.

    Used micros name with proper parameters to indicate that here during preprocessing the micros name has 
    to replaced by the code. 

    */
    switch (choice) 
    {

    /*
    used micros names with ADD, MINUS, MULTI, DIVIDE. During preprocessing these are going to replace 
    with the code
    */

    case 1:
        result = ADD(first,second);
        printf("\nResult: %d + %d = %d\n",first,second,result);
        break;

    case 2:
        result = MINUS(first,second);
        printf("\nResult: %d - %d = %d\n",first,second,result);
        break;

    case 3:
        result = MULTI(first,second);
        printf("\nResult: %d * %d = %d\n",first,second,result);
        break;

    case 4:
        /*
        there is a condition that if the division is by zero it must print the error message. Then,
        break the case 4, and finish the main method. it is not going to do the division anymore.
        */
        if(second == 0) 
        {
          
            printf("\nError : division by zero\n"); 
        
        } else 
        {

            result = DIVIDE(first,second);
            printf("\nResult: %d / %d = %d\n",first,second,result);
        
        }

        break;

    default: /* used default for any intput rathe than 1, 2, 3 and 4.  */
        printf("\nYou selected the wrong option\n");
        break;

    }

    /*
    Programer name's for the reference purpose
    */
    printf("\nThe program is made by Vasukumar Patel\n\n");

    /* retuning the number zero from the main() method because of the int return data type by using return
     keyword. */
    return 0;

}   