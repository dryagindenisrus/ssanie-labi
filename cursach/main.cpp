#include "Rabin_carp.h"
#include <iostream>
#include <string>

int main()
{
    std::string str = "abcdefgef ghijkmonpr";
    Rabin_carp a;
    std::string text;
    std::cout << text.max_size() << std::endl;
    a.set_pattern(str);
    std::cout << a.rolling_hash() << std::endl;

    return 0;
}