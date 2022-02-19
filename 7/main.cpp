#include "my_lab.h"
#include <iostream>
 
#include <iostream>
#include <cstdio>
#include "my_lab.h"

using namespace std;

int main() {    
    while(1){
    
    long a = 1000;
    scanf("%ld",&a);
    char b[15];

    int count = sizeof(b)/sizeof(char);

    ltoab(a, b);
    for (int i=0; i<ltoab(a, b); i++) {
        cout << "\x1B[32m" << b[i] << "\033[0m";
        //cout << b[i]; 
        //printf("\x1B[32m%c\033[0m", b[i]);
    }
    printf("\nreal val: %o\n", a);
    }
}