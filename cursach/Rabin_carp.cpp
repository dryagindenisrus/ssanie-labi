#include "Rabin_carp.h"
#include <string>
#include <iostream>
#include <math.h>


void Rabin_carp::set_pattern(const std::string& pattern)
{
    this->pattern = pattern;
}


long long int Rabin_carp::rolling_hash()
{
    long long int hash_result;
    int str_size = this->pattern.size();

    for (int i=0; i<str_size; i++)
    {   
        // c - value of char in ASCII
        int c = this->pattern[i];
        hash_result += c * pow(h, str_size-1);
        
    }
    std::cout << "c= " << hash_result<< std::endl;
    return hash_result;
};


Rabin_carp::Rabin_carp()
{
};


Rabin_carp::~Rabin_carp()
{  
};