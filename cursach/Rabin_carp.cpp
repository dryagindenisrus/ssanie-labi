#include "Rabin_carp.h"
#include <string>
#include <iostream>


void Rabin_carp::set_pattern(const std::string& pattern)
{
    this->pattern = pattern;
    this->pattern_size = pattern.size();
}


int Rabin_carp::init_rolling_hash()
{
    int hash_result = 1;
    for (unsigned int i=1; i < pattern_size; i++) 
    {
        hash_result = (hash_result*d) % prime_const;
    }
    return hash_result;
};


// http://mindhalls.ru/rabin-karp-search/   01.03.2017  Автор: Кузьминых Кирилл
int Rabin_carp::rolling_hash()
{
    long long int hash_result;
    int str_size = this->pattern.size();

    for (int i=0; i<str_size; i++)
    {   
        // prevHash += (d*prevHash + (int)str[i]) % p;
        // c - value of char in ASCII
        int c = this->pattern[i];
        // h - const in ".h" file
        hash_result += (h * hash_result + c) % prime_const;
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