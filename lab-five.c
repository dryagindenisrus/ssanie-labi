#include <stdio.h>

void space(int razmer) {
    for (int i = 0; i<razmer; i++) {
        printf(" ");
    }
}
int main() {
    int g = 1000;
    int j, i;
  
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
        space(size);
    }
    printf("\n");

    return 0;
}