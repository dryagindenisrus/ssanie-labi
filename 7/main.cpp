#include "my_lab.h"
#include <iostream>

using namespace std;

int main() {    
    while (1) {

        long a;
        scanf("%ld", &a);
        char b[12];
        int count = sizeof(b)/sizeof(char);

        printf("\nval: %o\n", a);
        ltoab(a, b);
        printf("lav: ");

        int i = 0;
        while (b[i] != '\0') {
            std::cout << b[i];
            i++;
        }
        printf("\n");
        
    }
}