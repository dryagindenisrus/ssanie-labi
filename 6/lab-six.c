#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
// task 6, varinat 02
// https://github.com/Danzo0l
// dryagindenisrus@gmail.com

//for input variables use ENTER
int sort_str(const void *a, const void *b);

extern int strcmp (const char *__s1, const char *__s2)
      __THROW __attribute_pure__ __nonnull ((1, 2));

// {
//     {},
//     {},
// }
int main() {
    int size = 8, buf = 8;
    int i = 0, j = 0, n = 1;
    char c;
    FILE* f1 = fopen("a.txt", "r");
    FILE* f2 = fopen("b.txt", "r");
    char **str_arr0 = NULL, **str_arr1 = NULL;

    str_arr0 = (char**) malloc(size * sizeof(char*));
    str_arr1 = (char**) malloc(size * sizeof(char*));


    for(i = 0; i < size; i++)
        str_arr0[i] = (char*)malloc(sizeof(char*) * size);


    str_arr0 = (char**) realloc(str_arr0, size * sizeof(char*));
    for(i = 0; i < size; i++) {
        str_arr0[i] = (char*) realloc(str_arr0[i], size * sizeof(char*));
    }


    //printf("%d\n", sizeof(str_arr0));
    //printf("%c\n", str_arr0[1][0]);


    int *p = NULL;
    p = (int*) malloc(8 * sizeof(char));

    qsort(str_arr0, 4, sizeof(char*), sort_str);
    qsort(str_arr1, 4, sizeof(char*), sort_str);



    fclose(f1);
    fclose(f2);
    return 0;
}

int sort_str(const void *a, const void *b) {
    char **x = (char**)a;
    char **y = (char**)b;
    return strcmp(*x, *y);
}