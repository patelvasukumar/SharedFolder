/* My personal recommendation : If you want to test my makefile, use Linux command shell because winodws does not support makefile. We need to download external program to run it on powershell. Also, Valgrind is supported on the linux system, not on winodws. So, use Linux for it. Personally, use WSL (Winodws subsystem for linux) to develop makefile and check memory leacks. */

/*
Student Name : Vasukumar Patel
Student Number : 041153940
Assigment - 1 : An artifact finding game program in which dynamic memory allocation is used
Lab Professor : Professor Veda Vasavi Erukulla
Due Date : 30th June at 5:00 PM (Monday)
Total files submitted (4) : program file (.c), windows execution file(.exe), object file (.o), makefile 
*/

/* Importing standard libraries */
#include <stdio.h> 
#include <stdlib.h> /* for abs() */
#include <string.h> /* for strlen(), strcmp(), strcpy() */
#include <ctype.h> /* for isdigit() , isalpha() */
#include <time.h> /* for time() function */
#include <signal.h> /* for SIGINT (Ctrl + C) and SIGTSTP (Ctrl + z) handling */

/* just for fun */
#define Vasu main

/* prototype declaration */
void mapDimensions();
int artiNumber();
void memoryAllocation();
void freeMemory();
void addCode();
void menuDisplay();
void find();  
void calculateAndMarkDistance(int px, int py);
int manhattanDistance(int x1, int y1, int x2, int y2);
void activateDebugMode();
void displayMap(int debug);
int afterAllFound();
void ctrlC();  
void ctrlZ();  

/*  */
int height;
int width;
int artifactNumber;
int x;
int y;
int debugMode = 0;
int score = 0;

/* Pointers to daynamic arrays */
char **array = NULL;
int *artifactPosX = NULL;
int *artifactPosY = NULL;
int *artifactFound = NULL;
int *artifactPoints = NULL;
char **artifactCodes = NULL;

/* calling functions */
int Vasu() {
     
    /* used builded in function to handle control + C and Z  */
    signal(SIGINT, ctrlC);   
    signal(SIGTSTP, ctrlZ);  
    /* So, here, I used inbuild method and my two customize method. So, when signal happens, the program works accroding to the functions */
    
    mapDimensions();
    
    memoryAllocation();
    
    artifactNumber = artiNumber();
    
    srand((unsigned) time(NULL)); /* If NULL and 0 used, it means the function must not store the time value at a specific location. It must return the value [current time]. */
    
    addCode();
    
    displayMap(0);
    
    menuDisplay();
    
    freeMemory();
    
    return 0;
}

/* the function checks width and height, and ensure it is a number. if wrong user input enter, option repeat and also clear stdin clear buffer. */
void mapDimensions() {
   
    int checkWidth = 1;
    int checkHeight = 1;
    
    while (checkWidth) {
        
        int checkDigit;
        printf("\nEnter map width: ");
        checkDigit = scanf("%d", &width);
        /* scanf returns three values : more than zero : successful, less than zero for EOF and zero if nothing assign. */
        
        /* EOF build-in micro from stdio.h */
        if (checkDigit == EOF) {
            printf("\nEOF detected. Exiting program.\n");
            freeMemory();
            exit(1);
        }
       
        if (width < 2 || width > 40 || checkDigit <= 0) {
            scanf("%*[^\n]"); /* to clear stdin buffer stream */
            printf("\nYou must enter only a number between 2 and 40.\n");
        } else {
            checkWidth = 0;
        }
    }
    
    while (checkHeight) {
        
        int checkDigit;
        printf("\nEnter map height: ");
        checkDigit = scanf("%d", &height);
        
        if (checkDigit == EOF) {
            printf("\nEOF detected. Exiting program.\n");
            freeMemory();
            exit(1);
        }
        
        if (height < 2 || height > 40 || checkDigit <= 0) {
            scanf("%*[^\n]");
            printf("\nYou must enter only a number between 2 and 40.\n");
        } else {
            checkHeight = 0;
        }
    }

}

/* checking the number and clearing the stdin buffer stream */
int artiNumber() {
    
    int checkArti = 1;
    
    while (checkArti) {
    
        int checkDigit;
        printf("\nEnter number of artifacts: ");
        checkDigit = scanf("%d", &artifactNumber);
        
        if (checkDigit == EOF) {
            printf("\nEOF detected. Exiting program.\n");
            freeMemory();
            exit(1);
        }
        
        if (artifactNumber <= 0 || checkDigit <= 0) {
            scanf("%*[^\n]");
            printf("\nDo not enter a String or a Negative number or a Zero\n");
        } else {
            checkArti = 0;
        }
    
    }
    
    return artifactNumber;

}

/* used malloc to allocate memory and initialized all the index with null terminators */
void memoryAllocation() {
    
    int i;
    int matrix = height * width;
    
    array = (char**) malloc(matrix * sizeof(char*)); /* need to cast to char** because malloc returns a void pointer  */
    
    /* handling memory allocation faliure */
    if (array == NULL) {
        printf("\nMemory allocation failed.\n\n");
        freeMemory();
        exit(1);
    }
    
    for (i = 0; i < matrix; i++) {
    
        array[i] = (char*) malloc(5 * sizeof(char));
    
        if (array[i] == NULL) {
            printf("\nMemory allocation failed.\n\n");
            freeMemory();
            exit(1);
        }
    
        array[i][0] = '\0';
    
    }

}

/* freeing all allocated memory. Freeing means also dereferencing. So, now, pointer does not point to anywhere. So, it is a good practice to make it null.*/
void freeMemory() {
    
    int i;

    /* freeing 2-d array's memory */
    if (array) {
        
        for (i = 0; i < height * width; i++) {
            free(array[i]);
        }
        
        free(array);
        array = NULL;
    
    }

    /* for pointers. */
    free(artifactPosX);    
    artifactPosX = NULL;
    free(artifactPosY);    
    artifactPosY = NULL;
    free(artifactFound);  
    artifactFound = NULL;
    free(artifactPoints);  
    artifactPoints = NULL;

    /* for artufact codes */
    if (artifactCodes) {
       
        for (i = 0; i < artifactNumber; i++) {
            free(artifactCodes[i]);
        }
       
        free(artifactCodes);
        artifactCodes = NULL;
    
    }

}

/* adding code and validating it */
void addCode() {
    
    char code[5]; /* 1 letter + 3 digits + null terminator */
    int i; 
    int j;
    int valid;
    int length;
    int eofChecker;
    int duplicate;
    int matrix = height * width;
    int randomPosition;

    for (i = 0; i < artifactNumber; i++) {
        
        valid = 1;
        
        while (valid) {
            
            duplicate = 0;
            
            printf("\nEnter artifact code #%d (1 letter + 3 digits): ", i + 1);
            eofChecker = scanf("%s", code);
            
            if (eofChecker == EOF) {
                printf("\nEOF detected. Exiting program.\n");
                freeMemory();
                exit(1);
            }
            
            length = strlen(code); /* counting string length, it does not count '/0' */
            
            /* need to cast to unsigned, It is good practice and also it helps to save function from unexpected behaviours */
            if ( length == 4 && 
                 isalpha((unsigned)code[0]) && 
                 isdigit((unsigned)code[1]) &&
                 isdigit((unsigned)code[2]) && 
                 isdigit((unsigned)code[3]) ) {
                
                    for (j = 0; j < matrix; j++) {
                    
                        /* comparing the string and if duplicate, repeat again */
                        if (array[j][0] != '\0' && strcmp(array[j], code) == 0) {
                        
                            duplicate = 1;
    
                        }
                
                }
                
                if (duplicate) {
                    
                    printf("\nYou must not enter duplicate values !\n");
                    scanf("%*[^\n]%*c");
                
                } else {
                    
                    do {
                      
                        /* using random function to generate values  */
                        randomPosition = rand() % matrix;
                        /* devide by matrix becuase we need single digit number*/
                    
                    } while (array[randomPosition][0] != '\0'); /* if it generates one random number again, loop happens. it is for safety */
                    
                    /* copying string */
                    strcpy(array[randomPosition], code);
                    valid = 0;
                
                }

            } else {
                
                printf("\nIncorrect entry. Codes must be a letter and three digits.\n");
                scanf("%*[^\n]%*c");
            
            }
        }
        
        /* resize arrays to store values */
        artifactCodes = (char **) realloc(artifactCodes, (i + 1) * sizeof(char *));
        artifactPosX    = (int *) realloc(artifactPosX,    (i + 1) * sizeof(int));
        artifactPosY    = (int *) realloc(artifactPosY,    (i + 1) * sizeof(int));
        artifactFound   = (int *) realloc(artifactFound,   (i + 1) * sizeof(int));
        artifactPoints  = (int *) realloc(artifactPoints,  (i + 1) * sizeof(int));

        /* allocate and store code string */
        artifactCodes[i] = (char *) malloc(length + 1);
        
        /* storing into arrays of artifactcodes on different index */
        strcpy(artifactCodes[i], code);
        
        /* same here, but storing index values of map : X and Y coordinates */
        artifactPosX[i] = randomPosition % width; /* column index */
        artifactPosY[i] = randomPosition / width; /* row index */
        artifactFound[i] = 0; /* not found */
        
        /* giving points. 2 if capital. 1 if small. */
        if (code[0] >= 'A' && code[0] <= 'Z') {
            artifactPoints[i] = 2;
        } else {
            artifactPoints[i] = 1;
        }
    
    }

}

/* displaying menu with different features */
void menuDisplay() {
    
    char menu;
    
    while (1) {
        
        printf("\nMenu:\n");
        printf("   1: Dig at a spot\n");
        printf("   2: Exit\n");
        printf("   3: Activate debug mode.\n");
        printf("->");
        scanf(" %c", &menu);
        
        switch (menu) {
            
            case '1':
                
                find();
                break;
            
            case '2':
                
                printf("\nYour score: %d\n", score);
                printf("\nThe program is made by Vasukumar Patel\n\n");
                freeMemory();
                exit(0);
            
            case '3':
                
                activateDebugMode();
                break;
            
            default:
                
                printf("\nYou selected the wrong option. Please, try again ! \n");
                break;
        
        }

    }

}

/* finding artifact, checking x and y and boundaries and also, calculating distance and dispaying on map */
void find() {
    
    int checkX = 1;
    int checkY = 1;   
    int statusX;
    int statusY;
    int i;

    printf("\nWhere would you like to dig ? ");
    
    while (checkX) {
        
        printf("\nX = ");
        statusX = scanf("%d", &x);
        
        if (statusX == EOF) {
            printf("\nEOF detected. Exiting program.\n");
            freeMemory();
            exit(1);
        }
        
        if (statusX <= 0) {
            scanf("%*[^\n]");
            printf("\nDo not enter a String\n");
        } else {
            checkX = 0;
        }
    
    }
    
    while (checkY) {
        
        printf("\nY = ");
        statusY = scanf("%d", &y);
        
        if (statusY == EOF) {
            printf("\nEOF detected. Exiting program.\n");
            freeMemory();
            exit(1);
        }
        
        if (statusY <= 0) {
            scanf("%*[^\n]");
            printf("\nDo not enter a String\n");
        } else {
            checkY = 0;
        }
    }
    
    if (x < 0 || x >= width || y < 0 || y >= height) {
        printf("Not in the range. Please, Try again ! \n");
        return;
    }

    for (i = 0; i < artifactNumber; i++) {
        
        /* if artifact is not found and x and y match */
        if (!artifactFound[i] && artifactPosX[i] == x && artifactPosY[i] == y) {
            
            artifactFound[i] = 1; /* now make it found */
            array[y * width + x][0] = '!'; /* display on map */
            score = score + artifactPoints[i];
            /* add points */

            printf( "\nYou have found an artifact ! (Artifact code : %s, %d points)\n", artifactCodes[i], artifactPoints[i] );

            /* displaying maps and after all artifact found funcion */
            displayMap(debugMode);
            afterAllFound();

            return; /* return from the function */
        
        }
    
    }

    /* no found, calculate distance and display on map */
    calculateAndMarkDistance(x, y);
    displayMap(debugMode);

}

/* calculate the distance */
void calculateAndMarkDistance(int px, int py) {
    
    int index;
    int distance;
    int minDistance;
    int first = 1;  

    for (index = 0; index < artifactNumber; index++) {
        
        /* only for unfound artifact */
        if (!artifactFound[index]) {
            
            /* calculate the distance : manhattan distance */
            distance = manhattanDistance(px, py, artifactPosX[index], artifactPosY[index]);
            
            /* if a first unfound or near unfound artifact is found, update minDistance*/
            if (first || distance < minDistance) {
                minDistance = distance;
                first = 0; /* change and mark */
            }
        
        }
    }

    /* near unfound one artifact */
    if (!first && minDistance <= 9) {
         
        array[py * width + px][0] = '0' + minDistance; /* update array with distance */
    
    } else { /* if near is no unfound artifact */
        
        array[py * width + px][0] = '#';
        /* update array with sign */
    
    }

}

/* simple formula */
int manhattanDistance(int x1, int y1, int x2, int y2) {

    int x = abs(x1 -x2);
    int y = abs(y1 - y2);
    int result = x + y;

    return result;

}

/* simple activation of debug mode */
void activateDebugMode() {
    
    debugMode = !debugMode;

    if (debugMode) {
    
        printf("\nDebug mode activated\n");
    
    } else {
    
        printf("\nDebug mode deactivated\n");
    
    }
    
    displayMap(debugMode);

}

/* displaying map accoding to the user interaction */
void displayMap(int debug) {
    int row; 
    int column;
    int oneD;   
    int loop;
    char printChar;
    char content;
    int unfound;

    printf("\n   ");
    for (column = 0; column < width; column++) {
        printf("%d", column % 10);
    }
    printf("\n   ");
    for (column = 0; column < width; column++) {
        printf("-");
    }

    printf("\n");

    for (row = 0; row < height; row++) {
       
        printf("%02d|", row); /* minimum 2 decimal and replace white space with zero */
        
        for (column = 0; column < width; column++) {
            
            oneD = row * width + column;
            content = array[oneD][0];
            printChar = ' ';
            unfound = 0;

            /* if denug mode is on reveal anu hidden artifact */
            if (debug) {
                for (loop = 0; loop < artifactNumber; loop++) {
                    if (!artifactFound[loop] && artifactPosX[loop] == column && artifactPosY[loop] == row) {
                        printChar = '?'; /* mark ? */
                        unfound = 1;
                        break;
                    }
                }
            }

            /* use map content , if no artifact mark above */
            if (!unfound) {
              
                /* if condition */
                if ( content == '!' || content == '?' || (content >= '0' && content <= '9') || content == '#') {
                    printChar = content;
                }
           
            }
            /* print on the cell  */
            printf("%c", printChar);
        }
        printf("\n");
    }
}

/* winning method */
int afterAllFound() {
    
    int i; 
    
    /* if any unfound artifact, it returns with 0 exit code */
    for (i = 0; i < artifactNumber; i++) {
        
        if (!artifactFound[i]) {
            return 0;
        }
    
    }
    
    /* print score, free the meomory and exit  */
    printf("\nCongratulation ! You have found all artifacts, and Your total score is %d\n\n", score);
    freeMemory();
    exit(0);
    return 0;

}

/* handling memory leack due to CTRL + C on termianl */

void ctrlC() {
    printf("\nThe program is stop by Control + C, and allocated memory is released ! \n\n");
    printf("\nTotal score: %d\n\n", score);
    freeMemory();
    exit(1);
}

/* handling memory leack due to CTRL + Z on termianl */
void ctrlZ() {
    printf("\nThe program is stop by Control + Z, and allocated memory is released ! \n\n");
    printf("\nTotal score: %d\n\n", score);
    freeMemory();
    exit(1);
}

/*

References : 

1. How to handle infinite loop if user enters a character or a String (an array of character).
link : https://www.youtube.com/watch?v=_i2faEmp2lo

2. Valgrind
link : https://www.youtube.com/watch?v=DyqstSE470s

3. EOF
1-link : https://www.geeksforgeeks.org/c/eof-and-feof-in-c/ 
2-link : https://www.geeksforgeeks.org/c/scanf-in-c/ 

4. Buffer-overflow
1-link : https://youtu.be/N7-MueK2CX8 
2- link : https://www.quora.com/What-is-meaning-of-*-n-*c-while-using-scanf-in-c [2nd post by Amit Amit Upadhyay]

5. putting elements on random locations 
1 : https://www.geeksforgeeks.org/c/c-rand-function/
2 : https://www.geeksforgeeks.org/dsa/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/
3 : https://www.geeksforgeeks.org/cpp/rand-and-srand-in-ccpp/ 
4 : https://stackoverflow.com/questions/1730961/convert-a-2d-array-index-into-a-1d-index

6. Srand()
1 : https://www.youtube.com/watch?v=oXEDMNXzuo4 

7. MakeFile (main website for the knowledge : https://www.gnu.org/software/make/manual/make.html )
1 : https://www.youtube.com/watch?v=yWLkyN_Satk
2 : https://www.youtube.com/watch?v=sqtyOkCgqJg

what we need :
Linux shell or if you are using windows Powershell, need to downlaod a software.
link of it : https://answers.microsoft.com/en-us/windows/forum/all/powershell-terminal-in-vs-code-make-the-term-make/74d69621-c91e-4929-83c2-2252f0371397 

 1. https://pastorini.net/blog/make-missing-separator-problem/

2.  https://unix.stackexchange.com/questions/125757/make-complains-missing-separator-did-you-mean-tab#:~:text=Means%20that%20the%20,rule%20stanzas%20within%20the%20file

3. https://askubuntu.com/questions/1122651/makefile1-missing-separator

Linux : 
1. download make file command : sudo apt install make
2. download dos2unix command : sudo apt install dos2unix (because we need to remove /r which is windows doc system)   In, windows  : every line ends with \r\n, but linux need just \n. ) this commands remove \r. Also, in linux, all command must start with real tab. not a space. 

/r - back to the beginning of the line 
/n - new line

then, we can use run make run command to run the file. 

comments in makefile : https://www.gnu.org/software/make/manual/html_node/Makefile-Contents.html#:~:text='%20%23%20'%20in%20a%20line%20of,effectively%20blank%2C%20and%20is%20ignored.

by default : make GNUmakefile, makefile, Makefile (three names are acceptable)
otherwise need to use : make -f fileName [with -f option which means file ]

7. Distance formula : 
1 - https://www.datacamp.com/tutorial/manhattan-distance
2- abs() - https://www.w3schools.com/c/ref_stdlib_abs.php 
3- https://www.chessprogramming.org/Manhattan-Distance#:~:text=Manhattan%2DDistance%20and%20Distance%20are,early%20in%20the%2020th%20century.

8. How to catch control + C [Memory leack]
https://cplusplus.com/forum/beginner/1501/
https://www.geeksforgeeks.org/c/signals-c-language/

*/