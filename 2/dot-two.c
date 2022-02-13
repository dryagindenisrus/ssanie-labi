#include <stdio.h>
// task 2.1, varinat 02
// https://github.com/Danzo0l
// dryagindenisrus@gmail.com

//for input variables use ENTER
int todec(char input);
int upp(int inp, int step);
int hash(char arr[], int raz);

int main() {
    char str_word[1000], str_one[1000];
    int symb = 0, lenght1 = 0, lenght2 = 0, hashing = 0, hashed;
    char c, b;

    while ((c = getchar()) != '\n') {
        str_one[symb] = c;
        symb ++;
    }
    lenght1 = symb;
    symb = 0;
    while ((b = getchar()) != '\n') {
        str_word[symb] = b;
        symb ++;
    }
    lenght2 = symb;
    

    char kol[lenght2];
    for (int i = 0; i < lenght2; i++) {
        kol[i] = str_word[i];

    }
    hashed = hash(kol, lenght2);

    int count = 0;
    for (int i = lenght2-1; i < lenght1; i++) {
        char sver[lenght2];
        for (int j = 0; j < lenght2; j++) {
            sver[lenght2-1 - j] = str_one[i - j];
        }
        count ++;
        //printf("%c, %c, %c\n", sver[0], sver[1], sver[2]);
        if (hash(sver, lenght2) == hashed) {
            printf("~ %d\n", i-1);
        }
    }


    return 0;
}


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