/* importing the methods from static library like copy and pasting  */
#include <stdio.h>

/*
According to the provided formula in Lab 1 for fibonacci, the programmer used reverse thinking on the 
equation and then find the solution. Rather than, subtraction or other approaches. addition would be the 
simple and non-complex solution.

Also, zero and one are default values (previous terms) in the fibonacci series.

*/

/*
for this project the programmer has used all local variables.
*/

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
    unsigned int currentTerm;
    unsigned int firstTerm = 0;
    unsigned int secondTerm = 1;
    unsigned int index = 0;
    unsigned int newTerm;

     /* printf() calling the method from static library and passing arguments */
    printf("\nEnter value of N: ");
    /* scanf() calling the method from static library and passing arguments
     used scanf syntax to store the user input into the variable  */
    scanf("%u",&currentTerm);

    /* taking the currentTerm means the number where upto the program has to do the fibonacci. */
    printf("Fibonacci Sequence up to %uth term: ",currentTerm);

    /* used while loop to repeat the code until the condition becomes false.    */
    while (index < currentTerm)
    {   /* if the index is zero, it will print just zero . */
        if (index == 0)
        {
            printf("%u",firstTerm);
        }   /* if the index is one, it will print zero and 1 .  */
        else if (index == 1)
        {
            printf(" %u",secondTerm);
        }
        else /* if it is not zero or one, then it will add first and second term into a newTerm and print it. */
        {
            newTerm = firstTerm + secondTerm;
            printf(" %u", newTerm);
            firstTerm  = secondTerm; /* after printing, all values will be shifted towards right by one. */
            secondTerm = newTerm; /* in the next loop, the second term and new term will be added into again  */
                                  /* new term. */
        }

        index = index + 1; /* increasing index by one */
    }

    /*
    Programer name's for the reference purpose
    */
    printf("\nThe program is made by Vasukumar Patel.\n\n");
    
    /* retuning the number zero from the main() method because of the int return data type by using return
     keyword. */
    return 0;
}