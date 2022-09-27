#include "Rabin_carp.h"
#include <iostream>
#include <string>

int main()
{
    std::string str = "Py";
    Rabin_carp a;
    std::string text;
    a.set_string("text.txt");
    // a.input_pattern();
    a.set_pattern(str);
    a.pattern_size = 4;
    std::cout << a.pattern_size <<std::endl;
    // a.out_text();
    // a.find();
    // std::cout << a.rolling_hash("qw234545645756i8ighjmnfgn b") << std::endl;
    std::cout << a.rolling_hash("qwhf") << std::endl;
    std::cout << a.ring_hash("qwhf", 0) << std::endl;
    std::cout << a.ring_hash("qwhf", 0) << std::endl;
    
    return 0;
}