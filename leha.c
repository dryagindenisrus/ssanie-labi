#include <stdio.h>
int w(char ch){
return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';
}
int len(char ch[])
{
int len = 0,i = 0;
while(ch[i] != '\0')
{
len++;i++;
}
return len;
}
int main()
{
int g = 1000;
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
//printf("%c",str_words[wordInd][symbInd]);
}
char sl[1000];
gets(sl);
int k = 0,ii[1000],p = 0,z = 0;
for(int i = 0;i<=wordInd;i++)
{
for(int j = 1;j<=symbInd;j++)
{
if(w(str_words[i][j])) {
if (str_words[i][j] == sl[z]) {
z++;
k++;
}
}
}
z = 0;
if (k == len(sl))
{
ii[p] = i;
k = 0;
p++;
}
}
for(int i = 0;i<p;i++)
{
for(int j = 1;j<=symbInd;j++)
{
printf("%c",str_words[ii[i]][j]);
}
}
}
