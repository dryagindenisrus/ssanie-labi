#ifndef RABIN_CARP_H
#define RABIN_CARP_H

#include <string>
#include <vector>
#include <iostream>
#include <fstream>
#include <stdexcept>


const int d = 52;
const int prime_const = 65713;


class FindException: public std::exception {
    private:
    	std::string m_error;
    public:
    	FindException(std::string error)
    		: m_error(error) {}
    	const char* what() const noexcept { 
            return m_error.c_str(); 
        }
};


class Rabin_carp
{
private:
    std::string text;
    std::string pattern;
    int pattern_size;

    std::vector<int> hash_collision;
    int finded_index;

    void at(const FindException e);
    bool symbol_by_symbol(const std::string& pattern1, const std::string& pattern2);
    
    
public:
    Rabin_carp();
    ~Rabin_carp();
    void set_pattern(const std::string& pattern);
    void set_string(const std::string& filename);
    int rolling_hash(const std::string& pattern);
    int init_rolling_hash(const std::string& pattern);
    int find_by_index();
    std::string find_in_text();
    void out_text();
    int find();
};


#endif