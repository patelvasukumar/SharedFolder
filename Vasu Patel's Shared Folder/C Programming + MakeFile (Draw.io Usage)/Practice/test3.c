#include <stdio.h>

void doubleArray(int *arr, int size){

    int i;

    for(i = 0; i < size; i++){
        arr[i] = arr[i] * 2;
    }

}

int main (){

    int arr[] = {1,2,3,4,5};
    int size = sizeof(arr)/sizeof(arr[0]);
    int i;

    doubleArray(arr,size);

    for(i = 0; i < size; i++){
        printf("%d",arr[i]);
    }

    return 0;
}