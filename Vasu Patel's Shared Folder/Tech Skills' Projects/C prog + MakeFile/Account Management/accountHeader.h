/*
Student Name : Vasukumar Patel
Student Number : 041153940
Lab Professor : Professor Veda Vasavi Erukulla
Due Date : 03th August at 11:59 PM (Sunday)
Total files submitted (7) : program file (.c), header file (.h) , windows execution file(.exe), 
                            object file (.o), makefile (without extension), Linux file (without extension), text file (.txt)
*/

/* header file with prototypes and struct */

#ifndef accountHeader
#define accountHeader

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <ctype.h>

#define Vasu main 

/* in my mind, I am thinking this one as a class */
typedef struct node {
    
    char name[20];
    int field1;
    int field2;
    int field3;
    struct node *next;
       
}Node; /* Node : second name of class which help to make objects [I know C is not OOP], it is similar a little bit. */

/* prototyes */
void openfile(FILE **source, char fileName[]);
void insertDataFromFile(FILE *source);
void listFood(char name[20], int field1, int field2, int field3);
void listDrink(char name[20], int field1, int field2, int field3);
void listShow(char name[20], int field1, int field2, int field3);
Node* createNode(char name[20], int field1, int field2, int field3);
void insertData(Node **head ,char name[20], int field1, int field2, int field3);
void printOverall();
void printFood();
void printDrink();
void printShow();
void printShowFlag();
void trimWhiteSpace(char line[]);
void freeAllNodes(Node *freeNode);
void freeAllThree(Node *listFoodVar,Node *listDrinkVar,Node *listShowVar);

#endif
