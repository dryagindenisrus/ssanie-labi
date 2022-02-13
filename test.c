#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include <string.h>
// task 5, varinat 02
// https://github.com/Danzo0l
// dryagindenisrus@gmail.com


int main() {
    char s [] = " Hello , world ! " ;
    char * p1 ;
    char * p2 ;
    p1 = strchr(s , 'o' );
    p2 = strchr( p1 , 'o' );
    return 0;
}
