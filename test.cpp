#include <iostream>
using namespace std;
// task 5, varinat 02
// https://github.com/Danzo0l
// dryagindenisrus@gmail.com

void elem2 (char a[], int v) {
  a[1] = 'h';
}

int main () {
  char a[10];

  elem2(a,10);

  return printf("elem[2] = %c\n",a[1]) == EOF;
}
