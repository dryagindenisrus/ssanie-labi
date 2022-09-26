#include "Rabin_carp.h"
#include <iostream>
#include <string>

int main()
{
    std::string str = "abcdefgef ffsdfsafsafsfsaf5675678 6jjkghkhjdkkkkkmonpr";
    Rabin_carp a;
    std::string text;
    std::cout << 272257 - prime_const << std::endl;
    a.set_pattern(str);
    std::cout << a.rolling_hash() << std::endl;

    return 0;
}