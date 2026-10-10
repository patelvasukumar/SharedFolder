#include <stdio.h>

int main(){

    char *fruits[] = {"Apple","Banana","Cherry","Date","Elderberry"};
    int i;

    for(i = 0; i < 5; i++){
        printf("%s\n",fruits[i]);
    }

    return 0;
}