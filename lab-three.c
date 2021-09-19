#include <stdio.h>
// task 2, varinat 02
// https://github.com/Danzo0l
// dryagindenisrus@gmail.com
int blizh(int arr[], int pat);
int testing(void);
int abs(int input);

int main() {
    testing();

    return 0;
}

int testing() {
    int input_data[][2][10] = {
        {{223,676,132,1234,123,12,214,655,45,238}, {8, 12}},
        {{9,56,124,675,345,647,234,575,233,657}, {790, 675}},
        {{456,436,787,523,634,340,123,213,433,54}, {56, 54}},
        {{1,4,5,23,67,34,123,23,43,213}, {3, 4}},
    };

    for (int i = 0; i < 4; i++) {
        char status;
        status = 'F';
        int res;
        res = blizh(input_data[i][0], input_data[i][1][0]);
        if (input_data[i][1][1] == res) { 
            status = 'T';
        }
        printf("input: [");
        for (int j = 0; j<10; j++) {
            printf("%d,", input_data[i][0][j]);
        }
        printf("]  %d   output: %d %c\n", input_data[i][1][0], res, status);
    }

}

int blizh(int arr[], int pat) {
    int n = 10, i, j;
    j = 10000;
    for (i = 0; i<10; i++) {
        if (abs(pat - arr[i]) < abs(j) ) {
            j = pat - arr[i];

        }
    }
    return pat - j;
}

int abs(int input) {
    return input < 0 ? input * -1 : input;
}
