#include "Rabin_carp.h"
#include <iostream>
#include <string>

int main()
{
    std::string str = "What Python";
    Rabin_carp a;
    std::string text;

    a.set_string("text.txt");

    // a.input_pattern();
    a.set_pattern(str);

    // a.out_text();

    a.find_in_text();
    // a.find_by_index();
    
    return 0;
}