/* importing the methods from static library like copy and pasting */
#include <stdio.h>

/*
for this project the programmer has used all local variables.
*/

/*
defining micros which is going to replace during preprocessing with the name
this one has the name and the parameters which are going to replace by variables and it will the the 
following code brackets functions
*/
#define LOWERLIMIT 18.5
#define MIDDLELIMIT 25
#define HIGHERLIMIT 30

/*
 main() method with int return type and returning 0 exit number. it method also calls
 prebuild methods from static library and copy and pasting during the compiling in the object file.
*/
int main()
{

    /*
    Cannot use Signed and unsigned with float. Signed and unsigned are only used for integers in most
    of the programming language.
    */
    float weight = 0;
    float height = 0; 
    float bmi = 0;

    /* printf() calling the method from static library and passing arguments */
    printf("\nEnter the weight in kilograms: ");
    /* scanf() calling the method from static library and passing arguments
       used scanf syntax to store the user input into the variable  */
    scanf("%f",&weight);
    
    if (weight <= 0.0f) {
    printf("\nHeight must be a positive number, and it must not be zero ! \n\n");
    return 1;
    }

    printf("Enter the height in meters: ");
    scanf("%f",&height);

    if (height <= 0.0f) {
    printf("\nWeight must be a positive number, and it must not be zero ! \n\n");
    return 1;
    }    

    bmi = (weight/((height)*(height)));

    /* during preprocessing BMI replaces with the code and then the process happens according to the logic
     the programmer used 5.2 because 5 is total character that is going to use while printing
     and .2 means decimal points.
     for example, if it were 3.1, it would print something like 1.3  */
    printf("BMI = %5.2f",bmi);

    printf("\nCategory : ");

    /* used if and else with && and || operators
     && - both conditions must be true to process the code block
     || - only one condition must be true */

    if(bmi < LOWERLIMIT)
    {
        printf("Underweight\n");

    } else if (LOWERLIMIT <= bmi && bmi < MIDDLELIMIT) 
    {
        printf("Normal Weight\n");

    } else if (MIDDLELIMIT <= bmi && bmi < HIGHERLIMIT)
    {
        printf("Overweight\n");

    } else if (bmi >= HIGHERLIMIT)
    {
        printf("Obese\n");

    }

    /*
    Programer name's for the reference purpose
    */
    printf("\nThe program is made by Vasukumar Patel\n\n");

    /* retuning the number zero from the main() method because of the int return data type by using return
     keyword. */
    return 0;

}