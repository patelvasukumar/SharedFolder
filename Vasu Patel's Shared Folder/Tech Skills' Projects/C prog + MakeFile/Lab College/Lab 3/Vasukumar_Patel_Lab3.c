/*

Introduction :

1. the programmer used Header file and makefile to run the program. In makefile, the programmer need to use headerfile name besides .c file in order to make the object file. Also, in the header file, programmer used define guard to prevent same prototype to copy and paste on the .c file by the preprocessor.

2. in this program, the programmer used square2.txt file to read the rgb values, then find the average of all values and then print the hexdecimal values on the console. Also, apart from square2.txt file, there is another file named test.txt which is made by programmer (Vasu) to test the same code on a different file.

Note : in header file, the programmer used ifndef and endif, but there is also one directive #pargma once which is not supported on c89, but it is supported by some operating system and modern C and C++ language. the programmer did not use #pargma once because it is not supported by C89.

-- comment from programmer's makefile --
# -> Note : Since here we are using a header file, we need to put header file name besides .c file name in the prerequirement. It is a good  
#  practice. It is running on programmer's system, but it might not run on other's system.
--  --  --  --  --  --  --  --  --  --  --

Lab-3 Details : 

Student Name : Vasukumar Patel
Student Number : 041153940
Lab Professor : Professor Veda Vasavi Erukulla
Total files submitted (8) : program file (.c), windows execution file(.exe), object file (.o), makefile, square2.txt, test.txt, VasuHeader.h (header file), Vasukumar_Patel_Linux (liunx executable file [made by WSL] ) 

*/

/* including supported and personal header files */
#include <stdio.h> /* fopen(), fclose() etc ... */
#include <stdlib.h> /* malloc() */
#include <string.h> /* for string manipulation  */
#include "VasuHeader.h"



/* this method reads one line from the file. Also, handle errors and since we are declaring it before main() method. So, no need of prototype in c89. */
int readLine(FILE* source)
{
    char line[15];
    int val=0;
    if (fgets(line,15,source)) /* want to read empty space. So, fgets needed */
    {
        val=strtol(line,NULL,10); /* String to long then int  */
    }
    else
    {
        
        printf("Unexpected end of file!"); 
        return -1;
    }
    return val;
}

/* global pointers */
Pixel **pixels = NULL;
Pixel **result = NULL;

int VasuPatel()
{
    FILE* stream = fopen("square2.txt", "r"); 
    int x=0; 
    int y=0;
    int xi = 0;
    int yi = 0; 

    /* file loading failure error handling */
    if(stream == NULL){
        printf("\nError: Can not open the file.\n\n");
        return 1;
    }

    x = readLine(stream);
    y = readLine(stream);

    /* error handling */
    if(y <= 0 || x <= 0){
        printf("\nError : Zero or negative value detected.\n\n");
        fclose(stream);
        return 1;
    }

    /* 1-D Array */
    pixels = malloc(y * sizeof(Pixel*));
    result = malloc(y * sizeof(Pixel*));

     /* error handling */
    if(pixels == NULL || result == NULL){
        printf("\nError : Memory allocation failure.\n\n");
        fclose(stream);
        return 1;
    }

    for (yi = 0; yi < y; yi++){
        
        /* 2-D Array */
        pixels[yi] = malloc(x*sizeof(Pixel));
        result[yi] = malloc(x*sizeof(Pixel));

         /* error handling */
        if(pixels[yi] == NULL || result[yi] == NULL){
            printf("\nError : Memory allocation failure.\n\n");
            fclose(stream);
            return 1;
        }

    }

    /* putting all values into an array which is the type of struct */
    for ( yi = 0; yi < y; yi++)
    {
        
        for (xi = 0; xi < x; xi++)
        {
            
        pixels[yi][xi].red = readLine(stream);
        pixels[yi][xi].green = readLine(stream);
        pixels[yi][xi].blue = readLine(stream);
        
        }
    
    }

    /* finding average value */
    for (yi = 0; yi < y; yi++) {

        for(xi = 0; xi < x; xi++){
            result[yi][xi] = averagePixel(pixels,x,y,xi,yi);
        }

    }

    /* printing all the HEX values */
    for (yi = 0; yi < y; yi++) {

        for(xi = 0; xi < x; xi++){

            printf("%02X%02X%02X ",result[yi][xi].red, result[yi][xi].green, result[yi][xi].blue);
            /* x for HEX Values, 0 for printing 0 instead of space, 2 for minimum width if there is any single digit */
        }

        printf("\n");

    }
    
    /* freeing memory and closing the file */
    fclose(stream);
    freeMemory(yi,y);
    return 0;

}

/* method to calculate average of rgb and return data type is Pixel because of the struct. It is similar to java's object and class data type declaration */
Pixel averagePixel(Pixel **matrix, int x, int y,int xi, int yi){

    int i = 0;
    int j = 0;
    int count = 0;
    int redSum = 0;
    int greenSum = 0;
    int blueSum = 0;
    Pixel result;

    /* using loops and then finding the total count and then finding average. 
       Also, returning the struct (in my mind it is similar to java's object concept). I mean
       storing all data into object and then returning it. I know C is not object oriented programming.
    */
    for( i = yi - 1; i <= yi + 1; i++){

        for(j = xi - 1; j <= xi + 1; j++){

            if(i >= 0 && i < y && j >=0 && j < x){

                redSum = redSum + matrix[i][j].red;
                greenSum = greenSum + matrix[i][j].green;
                blueSum = blueSum + matrix[i][j].blue;
                count++;

            }


        }

    }

    result.red = (redSum/count);
    result.green = (greenSum/count);
    result.blue = (blueSum/count);

    return result;

}

/* free method to free every memory bloack */
void freeMemory(int yi, int y){

    for(yi = 0; yi < y; yi++){
        free(pixels[yi]);
        free(result[yi]);
    }

    free(pixels);
    pixels = NULL;
    free(result);
    result = NULL;

} 

/* Reference : 1. https://www.geeksforgeeks.org/c/c-file-io/
                2. https://www.geeksforgeeks.org/c/structures-c/
                3. https://www.geeksforgeeks.org/c/write-header-file-c/
       [video]  4. https://www.youtube.com/watch?v=pF1_fXz5zN0
                5. Pragma once : https://en.wikipedia.org/wiki/Pragma_once#:~:text=The%20correct%20title%20of%20this,being%20handled%20by%20the%20preprocessor.
                6. https://www.tutorialspoint.com/c_standard_library/c_function_strtol.htm
                7. https://www.geeksforgeeks.org/c/fgets-function-in-c/
       [video]  8. https://www.youtube.com/watch?v=7k1cUbjnCf0&t=90s 
*/
