#include <stdio.h>

int todec(char input) {
    char table[] = ".,01234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    for (int i = 0; i < 65; i++) {
        if (input == table[i]) {
            return i;
        }
    }
    return -1;
}


int upp(int inp, int step) {
    if (step == 0) {
        inp = 1;
    } else {
        for (int i = 0; i < step-1; i++) {
        inp *= inp;
    }
    }
    
    return inp;
}


int hash(char arr[], int raz) {
    int h = 0;
    for (int i = 0; i < raz; i++) {
        h += todec(arr[i]) * upp(10, raz - (i+1));
    }
    return h;
}

int main() {
    printf("%d\n", hash("vc", 3));
    char gg[] = {'d', 's',};
    printf("%d\n", hash(gg, 3));
    return 0;
}