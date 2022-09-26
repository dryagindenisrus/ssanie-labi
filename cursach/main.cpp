#include "Rabin_carp.h"
#include <iostream>
#include <string>

int main()
{
    std::string str = "a science and m";
    Rabin_carp a;
    std::string text;
    a.set_string("text.txt");
    a.set_pattern(str);
    std::cout << a.rolling_hash(str) << std::endl;
    // a.out_text();
    a.find_by_index();
    std::cout << a.find_by_index() << std::endl;
    
    return 0;
}