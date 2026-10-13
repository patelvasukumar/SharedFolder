#include <stdio.h>
#include <stdlib.h>

int main(){

    int *arr;
    int i;

    
    int user;

    printf("Enter the size of the array:");
    scanf("%d",&user);

    /*
     arr = (int *)malloc(5*sizeof(int));
    */

   
    arr = (int *)calloc(user,sizeof(int));
    


    if(arr == NULL){

        printf("Memory allocation failed!\n");
        return 1;

    }

    printf("Values in the array:\n");
    for(i=0; i < user ;i++){
        printf("arr[%d] = %d\n",i,arr[i]);
    }

    for (i = 0; i < user ; i++) {
            arr[i] = (i+1) * 10;
    }

      printf("Value at arr[3] = %d\n", arr[3]);

    printf("Values in the array:\n");
    for(i=0;i< user ;i++){
        printf("arr[%d] = %d\n",i,arr[i]);
    }



    printf(" BEFORE FREE %d",user);

    free(arr);

    printf(" AFTER FREE : %d",user);

    return 0;

}