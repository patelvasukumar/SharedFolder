#include <stdio.h>
#include <stdlib.h>

int main(){

    char **p = malloc(3*sizeof(char*));
    int i;

    p[0] = malloc(6*sizeof(char));
    p[1] = malloc(6*sizeof(char));
    p[2] = malloc(2*sizeof(char));

    sprintf(p[0],"Hello");
    sprintf(p[1],"world");
    sprintf(p[2],"!");

    for(i=0; i < 3; i++){
        printf("%s\n",p[i]);
    }

    for(i = 0; i < 3; i++){
        free(p[i]);
    }

    free(p);

    return 0;
}