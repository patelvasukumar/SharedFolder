
/* importing the methods from static library like copy and pasting  */
#include <stdio.h>

/*
for this project the programmer has used all local variables.
*/

/*
defining micros which is going to replace during preprocessing with the name
this one has the name and the parameters which are going to replace by variables and it will the the 
following code brackets functions

!= means it should not be equal to the given result and used && and || operator for the compulsory operations.

// && - both conditions must be true to process the code block
// || - only one condition must be true

*/
#define DIVIDE(year) (((year % 4) == 0) && (((year % 100) != 0) || ((year % 400) == 0)))

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
    unsigned int year;
    unsigned int result;

     /* printf() calling the method from static library and passing arguments   */
    printf("\nEnter the Leap Year: ");
    /* scanf() calling the method from static library and passing arguments
    used scanf syntax to store the user input into the variable  */
    scanf("%u",&year);

    /* storing the code value result in a local variables.
    during preprocessing, it will be replaced by the code and the propre arguments.  */
    result = DIVIDE(year);

    /*
    here, in the printf method I used %u for the unsigned variable. 
    */
    if(result)
    {
        printf("%u is a Leap Year.",year);
    } else 
    {
        printf("%u is not a Leap Year.",year);
    }

    /*
    Programer name's for the reference purpose
    */
    printf("\nThe program is made by Vasukumar Patel\n\n");

    /* retuning the number zero from the main() method because of the int return data type by using return
     keyword. */
    return 0;
}