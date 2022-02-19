#include <iostream>
#define MAXL 4294967296

using namespace std;


char ltoab(long num, char s[]) {
    int basis = 8;
    int mod = 0;
    char strk[8] = {'0', '1', '2', '3', '4', '5', '6', '7'};
    char swap[13];
    long long nam = num;
    int i = 0;
    int count = 0;


    if (num == 0) {
        s[0] = strk[0];
        s[1] = '\0';
    } else {

        if (num < 0) {
            nam = MAXL + num;
        }
        while (nam > 0) {
            swap[i] = strk[nam % 8];
            nam /= 8;
            i++;
        }

        int j;
        for (j=0; j<i; j++) {
            s[i-j-1] = swap[j];
        }
        s[j] = '\0';
    }

    return 0;
}