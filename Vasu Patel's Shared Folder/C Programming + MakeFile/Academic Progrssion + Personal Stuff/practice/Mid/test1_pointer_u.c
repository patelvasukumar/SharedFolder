#include <stdio.h>
#include <stdlib.h>

int main() {
    int *p;

    p = (int *)malloc(5 * sizeof(int));

    if (p == NULL) {
        printf("Memory allocation failed!\n");
        return 1;
    }

    p[0] = 10;
    p[1] = 20;

    free(p);

    printf("Pointer after free: %p\n",(void*) p);

    return 0;
}
