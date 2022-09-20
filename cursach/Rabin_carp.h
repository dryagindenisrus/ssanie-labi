#ifndef RABIN_CARP_H
#define RABIN_CARP_H

#include <string>


class Rabin_carp
{
private:
    std::string text;
    std::string pattern;
    int rolling_hash(std::string pattern);
    int finded_index;
    
public:
    Rabin_carp();
    ~Rabin_carp();
};

Rabin_carp::Rabin_carp(/* args */)
{
}

Rabin_carp::~Rabin_carp()
{  
}   


#endif