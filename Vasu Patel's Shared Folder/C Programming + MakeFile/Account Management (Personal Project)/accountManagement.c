/*
 *
Student Name : Vasukumar Patel
Student Number : 041153940
Lab Professor : Professor Veda Vasavi Erukulla
Due Date : 03th August at 11:59 PM (Sunday)
Total files submitted (7) : program file (.c), header file (.h) , windows execution file(.exe), 
                            object file (.o), makefile (without extension), Linux file (without extension), text file (.txt)
*/

/*

Introduction : in this project, the program is going to read the file and going to store all the data into a linked list and then accessing those data through pointers. It is going to do arithmatic operations on those data. 

Here, the programmer prefer to make one data struct because three different types of data type has same format. In his mind, one thing came that we can use different pointers to store the data and the same stract type. 

Also, the programmer does not prefer to store type for meal, drink and show [0,1,2] into linked list because they are not useful after sorting the types. This how it will save memory and linkedlist will work fast and use less memeory. 

In makefile, the programmer used -lm flag because on Linux system, it requires -lm [library math] flag to call the library. On windows, it is automatically fix. 

The programmer also used -O2 [recommended flag for optimization]. [for more details -> makefile on vim or down of the code after references]

Also, programmer here prefer to use fgets() + strtok() over fscanf because fscanf ignores the character from white space. So, the program was storing only half word from the file after white space, it was stopping.

*/

/* header file declaration in order to use struct and everything */
#include "accountHeader.h"

/* Global pointers */
FILE *source = NULL;
char *fileName = "sc_totals.txt"; /* here I prefer to make it pointer because if someone wants to change the file name, he or she does not have to look through code and find it. */
Node *listFoodVar = NULL;
Node *listDrinkVar = NULL;
Node *listShowVar = NULL;

/* calling functions and making pointer null after usage. So, it does not contain garbage values */
int Vasu(){

    openfile(&source,fileName);
    fclose(source);
    printOverall();
    freeAllThree(listFoodVar,listDrinkVar,listShowVar);
    
    listFoodVar = NULL;
    listDrinkVar = NULL;
    listShowVar = NULL;

    return 0;
}

/* open file */
void openfile(FILE **source, char fileName[]){

    *source = fopen(fileName,"r");

    if(*source == NULL){
        printf("\nError: File is not open yet ! \n\n");
        exit(1);
    }

    insertDataFromFile(*source);

}

/* after reading file insterting the data into three different linked list, but the same struct type */
void insertDataFromFile(FILE *source){

    char line[100];
    char name[20];
    int type; 
    int field1;
    int field2;
    int field3;
    char *stringPointer;
   
    while( fgets(line,sizeof(line),source) != NULL ){

            /* First I used strcpy to copy the string. Then, I see the output has garbage values. I need to terminate the string. So, then, I used strncpy in order to terminate the string. which copy the string from one to another variable of a fix size */

            /*strtok : read line upto "," like split() in java */

            stringPointer = strtok(line,",");
            strncpy(name,stringPointer,sizeof(name)-1);
            name[sizeof(name)-1] = '\0';
            trimWhiteSpace(name);

            stringPointer = strtok(NULL,",");
            type = atoi(stringPointer);

            stringPointer = strtok(NULL,",");
            field1 = atoi(stringPointer);

            stringPointer = strtok(NULL,",");
            field2 = atoi(stringPointer);

            stringPointer = strtok(NULL,",");
            field3 = atoi(stringPointer);

            switch (type)
            {
            
                case 0:
                listFood(name,field1,field2,field3);
                break;
                
                case 1:
                listDrink(name,field1,field2,field3);
                break;

                case 2:
                listShow(name,field1,field2,field3);
                break;

            }

        }
     
}



/* inserting all data to make nodes and linked them with the next nodes */
void listFood(char name[20], int field1, int field2, int field3){

    insertData(&listFoodVar,name,field1,field2,field3);

}

void listDrink(char name[20], int field1, int field2, int field3){

    insertData(&listDrinkVar,name,field1,field2,field3);

}

void listShow(char name[20], int field1, int field2, int field3){

    insertData(&listShowVar,name,field1,field2,field3);

}


/* creating node and returning them to the calling fucntion, and storing all data into the node type struct */
Node* createNode(char name[20], int field1, int field2, int field3){

    Node *newNode = (Node*) malloc(sizeof(Node));

     if(newNode == NULL){
        printf("\nError: Memory allocation failure ! \n\n");
        fclose(source); 
        freeAllThree(listFoodVar,listDrinkVar,listShowVar);
        exit(1);
    }

    strncpy(newNode->name, name, sizeof(newNode->name)-1);
    newNode->name[sizeof(newNode->name)-1] = '\0';
    newNode->field1=field1;
    newNode->field2=field2;
    newNode->field3=field3;
    newNode->next=NULL;

    return newNode;
}

/* if head is already there, it adds head first, and if it is not there it adds after head */
void insertData(Node **head ,char name[20], int field1, int field2, int field3){

    Node *newNode = createNode(name,field1,field2,field3);

     if(newNode == NULL){
        printf("\nError: Memory allocation failure ! \n\n");
        fclose(source); 
        freeAllThree(listFoodVar,listDrinkVar,listShowVar);
        exit(1);
    }
    
        if(*head == NULL){

            newNode->next = *head;
            *head = newNode;

        } else {

            Node *temp = *head;
            while(temp->next != NULL){
                temp = temp->next;
            }
            temp->next = newNode;

        }

}


/* it calls the other print functions and access the value through pointers from its appropriate struct type and variable type. Also, used ceil [ceiling function] function from math.h library */
void printOverall(){

    printf("\nFinancial Report:\n\n");
    printFood();
    printDrink();
    printShow();

}

void printFood(){

    int meals = 0;
    int revenue = 0;
    int chefTimeMin = 0;
    float chefHours = 0;
    int cost = 0;
    int netProfit = 0;
    Node *temp = listFoodVar;

    while (temp != NULL)
    {
        meals = meals + temp->field1;
        revenue = revenue + (temp->field1 * temp->field2);
        chefTimeMin = chefTimeMin + (temp->field1 * temp->field3);
        temp = temp->next;
    }
    
    chefHours = ceil( (float) chefTimeMin/60);
    cost = (int) (chefHours * 20); 
    netProfit = (revenue - cost);

    printf("%d food items, generating $%d revenue.",meals,revenue);
    printf("\nchef time is %.1f hours, costing $%.2f",  (float) chefHours, (float) cost);
    printf("\nNet profit from the kitchen is $%.2f", (float) netProfit);

    if(netProfit < 0){
        printf("\nKitchen is running at a loss.");
    } else if (netProfit > 0) {
        printf("\nKitchen is profitable.");
    } else if (netProfit == 0) {
        printf("\nKitchen is breaking even."); /* Revenue = Costs (no profit, no loss). */
    }

}

void printDrink(){

    int drinkCount = 0;
    float taxDue = 0;
    Node *temp = listDrinkVar;

    while (temp != NULL)
    {
        drinkCount = drinkCount + temp->field2;

        switch (temp->field3)
        {
            case 0:
            break;
        
            case 1:

            taxDue = taxDue + ( (temp->field1) * 0.10 );

            break;

            case 2:

            taxDue = taxDue + ( (temp->field1) * (temp->field2) * 0.05 );

            break;
        
        }

        temp = temp->next;

    }
    
    taxDue = ceil(taxDue);
    printf("\n\n%d drinks served, accumulating $%.2f in taxes due.\n",drinkCount,taxDue);

}

void printShow(){

    int totalRevenue = 0;
    int revenue = 0;
    Node *temp = listShowVar;

    while(temp != NULL){

        revenue = (temp->field1 * temp->field2);

        switch (temp->field3)
        {
            case 0:

            printf("\nBand %s brought in $%d revenue.",temp->name, revenue);

            break;
        
            case 1:

            printf("\nBand %s brought in $%d revenue. (Noise violation)",temp->name, (temp->field1*temp->field2 ));
            revenue = revenue - 500;

            break;
        }

        totalRevenue = totalRevenue + revenue;
        temp = temp->next;

    }

    printShowFlag();
    printf("\n\nTotal band revenue: $%d\n\n",totalRevenue);

}

void printShowFlag(){

    int revenue = 0;
    Node *temp = listShowVar;

    while (temp != NULL)
    {
        revenue = temp->field1 * temp->field2;
        
        switch (temp->field3)
        {
            case 1:

            revenue = (revenue - 500);

            if(revenue < 0){
                printf("\nNB:  %s is not profitable due to noise violations.",temp->name);
            }

            break;

        }

        temp = temp->next;

    }
    

}


/* I need to make the function in order to remove white spaces after characters */
void trimWhiteSpace(char line[]) {
    
    int length = strlen(line);
    
    while (length > 0 && isspace((unsigned char) line[length - 1])) {
        
        --length;
        line[length] = '\0';
    
    }

}


/* through loop, removing all nodes after the usage. So, zero memory leack. */
void freeAllNodes(Node *freeNode){

    Node *node = freeNode;
    Node *next = NULL;

    while (node != NULL)
    {
        next = node->next;
        free(node);
        node = next;
    }

}

void freeAllThree(Node *listFoodVar,Node *listDrinkVar,Node *listShowVar){

    freeAllNodes(listFoodVar);
    freeAllNodes(listDrinkVar);
    freeAllNodes(listShowVar);

}



/*
Reference : 
1. https://www.youtube.com/watch?v=X1Fq59EQaXo [video] [How to read csv file]
2. https://www.geeksforgeeks.org/c/how-to-create-a-linked-list-in-c/
3. https://www.geeksforgeeks.org/c/linked-list-in-c/
4. https://www.youtube.com/watch?v=BZFD-eEkhtw [video] [How to read csv file]
5. https://www.geeksforgeeks.org/cpp/strtok-strtok_r-functions-c-examples/
6. https://www.geeksforgeeks.org/c/c-program-to-implement-singly-linked-list/
7. https://docs.vultr.com/clang/standard-library/math-h/ceil [round-up (ceil) ==  ceiling function ] 

-> [remove trailing whitespace] [2 methods]
8. https://www.youtube.com/watch?v=3ME0l0trRsE [video] 
9. https://cboard.cprogramming.com/c-programming/79103-removing-leading-trailing-whitespace.html [used in the project]

10. https://www.geeksforgeeks.org/c/strncpy-function-in-c/

=> -lm flag on Linux based operating system due to math.h library 
11. https://stackoverflow.com/questions/8266183/linking-with-gcc-and-lm-doesnt-define-ceil-on-ubuntu
12. https://labex.io/tutorials/c-how-to-compile-with-math-library-in-linux-420434

13. https://embeddedprep.com/gcc-command-line-options/#google_vignette [ -O2 flag and other]

14. https://www.geeksforgeeks.org/c/scanf-and-fscanf-in-c/ [why I used fgets over fscanf]


*/

/* 
 -> A comment from makefile [out of syllabus, but I used and learned for extra knowledge] :

#-> Note : -lm [link math library] , library name : libm, it uses double float data type for its functions.
# on Linux system, we need -lm flag to link math library with the binary object file after compilation.

# The programmer used O2 flag for optimization. So, the code can run faster, use less memory and become smaller in size.

#  There are many optimization flags. For example, -O0 [No optimazation] , -O1 [basic] , -O2 [best and safe, but agrresive] , -O3 [Max], -Os [only optimize size] , -Ofast [fastes but unsafe because ingnore standards].

#  Fact :

#  1. optimization enable Dead code elimination Loop unrolling (sometimes), Inlining small functions, Constant folding, Common subexpression elimination

#  2. optimization affects debugging.

#  In order to do debugging best, used -g -O0 flag [default on most system while using gdb], which enables debug variables and more, but not good for code efficency and faster usage and less memeory usage.

#  Other useful debug tools like : strace [Trace system calls], Itrace [Trace library calls]


*/
