#include <iostream>

using namespace std;


char ltoab(long num, char s[]) {
    int basis = 8;
    long mod = 0;
    char strk[] = "01234567";
    char swap[11];
    char znak;
    long nam = num;

    int i = 0;
    int count = 0;
    if (nam < 0) {
        num = num * (-1);
        znak = '-';
        //i = 1;
    } else if (nam == 0) {
        s[0] = strk[0];
        return 1;
    }
    
    while (num > 0) {
        mod = num % basis;
        num = num / basis;
        swap[i] = strk[mod];
        i++;
    }

    swap[i] = znak;

    for (int j=0; j<i+1; j++) {
        s[i-j] = swap[j];
    }

    return i+1;
}

