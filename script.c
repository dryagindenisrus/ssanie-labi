#include <stdio.h>

int main() {
    printf("Hello, world\n");

    short var_short; // -32768 ... 32768
    long var_long; // very big diaposon
    unsigned shortvar_unshort; // 0 ... 65536
    int var_int; //
    long long int var_llint;
    // unsigned: var >= 0, for int, long, short, float ...

    char var_char; // for only 1 char

    float var_float; // for example: 1.1234
    double var_double; // for example: 1.12345678
    long double var_ldouble; // for example: 1.123456789...

    int var_int_arr[3];
    char var_char_arr[] = {'1', 's', 'a'};
    char var_word[] = "Hello"; // this string

    printf("%d", var_int); // construction for output vars
    // %d for int, long, long int ...
    // %u for unsigned int
    // %x for int in 16-x system
    // %ld for long int
    // %f for float
    // %df for double
    // %c for char
    // %s for string

    if(12 == 12) {
        printf("1");
    } else if(12==23) {
        printf("2");
    } else {
        printf("0");
    }

    return 0;
}
