#include "my_lab.h"
#include <iostream>
 
#include <iostream>
#include "my_lab.h"

using namespace std;

int main() {    
    long a = -1000;
    char b[15];

    int count = sizeof(b)/sizeof(char);

    ltoab(a, b);
    for (int i=0; i<ltoab(a, b); i++) {
        printf("\x1B[32m%c\033[0m", b[i]);
    }
}
