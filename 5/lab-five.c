#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
// task 5, varinat 02
// https://github.com/Danzo0l
// dryagindenisrus@gmail.com

int generate(int leng);

int main() {
    int g_symb = 8;
	int g_str = 8;
    int j, i, n;
	int count_symb, count_str;
	count_str = 1;
	count_symb = 1;
    char c;
  
  	int wordInd, symbInd;
  	wordInd = 0;
  	symbInd = 1;
  
	char **str_words = malloc(sizeof(*str_words) * g_str);
	for (int i = 0; i < g_str; i++) {
		str_words[i] = malloc(sizeof(**str_words) * g_symb);
	}

	for (i = 0; i < n; i++) {
		free(str_words[i]);
	}
  	free(str_words);

    while ((c = getchar()) != '\n') {
		if (count_str > g_str-1) {
			int old_str = g_str;
			g_str += 8;
			str_words = realloc(str_words, g_str * sizeof(*str_words));
			for (int i=old_str; i<g_str; i++) {
            	str_words[i] = malloc(sizeof(**str_words) * g_symb);
        	}
			for (i = 0; i < n; i++) {
				free(str_words[i]);
			}
			// free(str_words);
		}
		if (count_symb > g_str-1) {
			g_symb += 8;
			str_words[g_str] = realloc(str_words[wordInd], g_symb * sizeof(**str_words));
			//free(str_words[g_str]);
		}
		count_symb ++;
    	if (c == ' ') {
			count_symb = 0;
			count_str ++;
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
