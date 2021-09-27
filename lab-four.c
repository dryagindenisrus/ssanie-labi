#include <stdio.h>
// task 4, varinat 02
// https://github.com/Danzo0l
// dryagindenisrus@gmail.com

//for input variables use ENTER

int main() {
    int g = 1000;
    int j, i, count, n = 0;
    int arr[g][g];
    char str_input[g], str_words[g][g];       	
    fgets(str_input, 1000, stdin);
    

    for (i=0; i<g; i++) {
        count = 0;
        for (j=0; j<g; j++) {
            if (str_input[j + n] == ' ') {
                arr[i][0] = i;
                arr[i][1] = count;
                n += count;
                count = 0;
                break;
            } else {
                str_words[i][j] = str_input[j];
                count ++;
            }
        }
    }

    for (i = 0; i < 10; i++) {
        printf("%d, %d\n", arr[i][0], arr[i][1]);
    }
    return 0;
}
