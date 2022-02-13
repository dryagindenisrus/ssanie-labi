#include <stdio.h>
// task 2, varinat 02
// https://github.com/Danzo0l
// dryagindenisrus@gmail.com

//for input variables use ENTER
char str[10000];
char fnd[10000];
 
int search(char * s, char * t);
 
int main()
{
    scanf("%s",str);
    scanf("%s",fnd);
    int pos = search(str, fnd);
    printf("------------\n%d\n",pos);

    return 0;
}

int search(char * s, char * t) {
    int i = -1, j, k;
    if (s != NULL && t != NULL) {
        for (j = 0; s[j] != '\0'; j++){
            if(s[j] == t[0]) {
                for(k = 0; t[k] != '\0'; j++,k++) {
                    if(s[j] == '\0')
                        break;
                    if((s[j] != t[k]))
                        break;
                }
                if(t[k] == '\0')
                {
                    i = j - k;
                    break;
                }
                else
                    j--;
            }
        }
    }
    return i;
}