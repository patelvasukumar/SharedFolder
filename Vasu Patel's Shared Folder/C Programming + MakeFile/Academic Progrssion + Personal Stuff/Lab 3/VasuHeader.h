/* Note :
    1. If declare a variable, method in header file, then those names must not come again in the .c file otherwise, compile time error happens.
    2. prototype is allow to write again in the .c and header file. If a prototype is in both file, we are not going to get any compile time error.

    in short, declaration are not allowed in both file, but prototypes are allowed. 

*/

/*
declare guards to prevent the same prototype declaration again and again 
*/
#ifndef VASUHEADER_H /* start of ifndef */
#define VASUHEADER_H /* must be unique because if it is already in use by other header file, then preprocessor is going to ignore the header file which is using someone else's define header name */

#define VasuPatel main /* just for fun */

/* cannot instantiate in the prototype */
/* used typedef insted of only struct to use an aliase name. Three int data types  */
typedef struct rgb {

    int red;
    int green;
    int blue;

}Pixel; /*an aliase of struct, but Personally, I feel this one as java's object because it works similar to that one */

/* two prototype declaration */

Pixel averagePixel(Pixel **matrix, int x, int y,int xi, int yi);

void freeMemory(int yi, int y);


#endif /* end */
