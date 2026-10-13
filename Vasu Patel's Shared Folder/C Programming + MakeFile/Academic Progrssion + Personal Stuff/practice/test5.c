#include <stdio.h>

void printArray(int *p, int size){

    int i;

    for(i=0; i<size; i++){
        printf("%d",*(p + i));
    }
    printf("\n");
}

int main(){

    int arr[5] = {10,20,30,40,50};
    int *p = arr;

    printArray(p,5);

    *(p + 1) = 25;

    printf("Modified array: ");
    printArray(p,5);

    return 0;

}