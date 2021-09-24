#include <stdio.h>
// task 4, varinat 02
// https://github.com/Danzo0l
// dryagindenisrus@gmail.com

//for input variables use ENTER

int stlen(char * input_str);

int main() {
    int g = 1000;
    int j, i;
    char str_input[g], str_words[g][g];       	
    fgets(str_input, 1000, stdin);
    

    for (i=0; i<g; i++) {
        j = 0;
        if (str_input[i] != ' ') {
            str_words[j][i] = str_input[i];
        } else {
            j ++;
        }
    }

    //printf("%d\n", stlen(str_words, 0));

    return 0;
}


void sort(char *arr_input, int size) {
    int a, b;

    for (int i = 0; i < size - 1; i++) {
        for (int j = (size - 1); j > i; j--) {
            a = stlen(arr_input[j - 1]);
            b = stlen(arr_input[j]);

            if (a > b) {
                int temp = arr_input[j - 1];
                arr_input[j - 1] = arr_input[j];
                arr_input[j] = temp;
            }
        }
    }
}


int stlen(char * input_str) {
    int output = 0;
    char obr;
    for (int i = 0; i < 1000; i++) {
        if (input_str[i] != obr) {
            output ++;
        } else { break;}
    }
    return output;
}


