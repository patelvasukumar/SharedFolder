#include <stdio.h>

int main(){

    
    int x;

    printf("Enter the array size : ");
    scanf("%d",&x);

    int arr[] = x;

    arr[2] = 10;

    printf("%d",arr[2]);


    return 0;
}