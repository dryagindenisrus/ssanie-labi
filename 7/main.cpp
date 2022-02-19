#include "my_lab.h"
#include <iostream>

using namespace std;

int main() {
    long a;
    char b[12];

    
    cout << "\033[3;44;30mRUNNED...\033[0m\n>>> ";

    while (1) {
        // eto ne moy kod
        scanf("%ld", &a);
        printf("\n\x1B[33mtesting:\033[0m\t%o\n", a);

        ltoab(a, b);
        cout << "\x1B[32mmy_val:\033[0m \t";

        int i = 0;
        while (b[i] != '\0') {
            cout << b[i];
            i++;
        }
        printf("\n");
        
    }
}