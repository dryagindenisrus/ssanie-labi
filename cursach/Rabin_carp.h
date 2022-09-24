#ifndef RABIN_CARP_H
#define RABIN_CARP_H

#include <string>

const int h = 3;


class Rabin_carp
{
private:
    std::string text;
    std::string pattern;
    
    int finded_index;
    
public:
    Rabin_carp();
    ~Rabin_carp();
    void set_pattern(const std::string& pattern);
    long long int rolling_hash();
};


#endif