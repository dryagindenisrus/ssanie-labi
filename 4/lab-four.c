#include <stdio.h>
// task 4, varinat 02
// https://github.com/Danzo0l
// dryagindenisrus@gmail.com

//for input variables use ENTER

int main() {
    int g = 1000;
    int j, i, count = 0, n = 0;
    int arr[g][2];

    int lenght, index;
  
  	short str_words[g][g];       	
    
  	char c;
  
  	short wordInd, symbInd;
  	wordInd = 0;
  	symbInd = 1;
  
    while ((c = getchar()) != '\n') {
    	if (c == ' ') {
        wordInd++;
        str_words[wordInd][symbInd++] = '\0';
        symbInd = 1;
        continue;
    }
        str_words[wordInd][symbInd++] = c;
      	str_words[wordInd][0]++;
      	
    }

    int size = 0;
    
  	for (i = 0; i <= wordInd; i++) {
        char ch = '\0';
        int j = 0;
        arr[i][0] = i;
        size ++;
        do {
            ch = (char)str_words[i][j++];
            arr[i][1]++;
        } while (ch != '\0');
    }

    for (int i = 0; i < size - 1; i++) {
        for (int j = (size - 1); j > i; j--) {
            if (arr[j - 1][1] < arr[j][1]) {
                int temp0, temp1;
                temp0 = arr[j - 1][0];
                temp1 = arr[j - 1][1];
                arr[j - 1][0] = arr[j][0];
                arr[j - 1][1] = arr[j][1];
                arr[j][0] = temp0;
                arr[j][1] = temp1;
            }
        }
    }

    for (i = 0; i <= size-1; i++) {
        char ch = '\0';
        int j = 0;
        do {
            int mog = arr[i][0];
            ch = (char)str_words[mog][j++];
            printf("%c", ch);
        } while (ch != '\0');
        printf(" ");
    }
    printf("\n");

    return 0;
}
