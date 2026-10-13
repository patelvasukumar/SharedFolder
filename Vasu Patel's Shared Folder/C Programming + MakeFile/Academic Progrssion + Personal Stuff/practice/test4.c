#include <stdio.h>
#include <stdlib.h>

int main(){

    int **arr = malloc(3 * sizeof(int));
    int i;

    for(i = 0; i < 3; i ++){
        arr[i] = malloc(sizeof(int));
        *arr[i] = i + 1;
    }   

    for(i= 0; i < 3; i++){
        printf("%d\n",*arr[i]);     
    }

    for(i=0; i < 3; i++){
        free(arr[i]);
    }

    free(arr);

    return 0;
}
