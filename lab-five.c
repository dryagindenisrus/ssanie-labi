#include <stdio.h>
// task 5, varinat 02
// https://github.com/Danzo0l
// dryagindenisrus@gmail.com

//for input variables use ENTER

void space(int razmer) {
    for (int i = 0; i<razmer; i++) {
        printf(" ");
    }
}
int main() {
    int g = 1000;
    int j, i, n;
  
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
        int j = 1;
        size = 0;
        do {
      	    ch = (char)str_words[i][j++];
            printf("%c", ch);
            size ++;
        } while (ch != '\0');
		printf(" ");
		for (n = 0; n < size-2; n++) {
			j = 1;
			do {
      	    	ch = (char)str_words[i][j++];
            	printf("%c", ch);
        	} while (ch != '\0');
			printf(" ");
		}
    }
    printf("\n");

    return 0;
}