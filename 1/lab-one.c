#include <stdio.h>
// task 1, varinat 02
// https://github.com/Danzo0l
// dryagindenisrus@gmail.com

//for input variables use ENTER
 
int main() {
    
    int unsigned input, dop;
    input = 1;
    dop = 0;

    while (input) {
        scanf("%d", &input);
        if (input > dop) {
            dop = input;
        }
    }
    printf("%d\n", dop);
    return 0;
}