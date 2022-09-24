#ifndef RABIN_CARP_H
#define RABIN_CARP_H

#include <string>

const int d = 52;
const int prime_const = 65713;


class Rabin_carp
{
private:
    std::string text;
    std::string pattern;
    int pattern_size;
    
    int finded_index;
    
public:
    Rabin_carp();
    ~Rabin_carp();
    void set_pattern(const std::string& pattern);
    int rolling_hash();
    int init_rolling_hash();
};


#endif