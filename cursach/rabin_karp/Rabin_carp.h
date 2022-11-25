#ifndef RABIN_CARP_H
#define RABIN_CARP_H

#include <string>
#include <vector>
#include <iostream>
#include <fstream>
#include <stdexcept>


const int d = 13;
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
    int pattern_hash;
    
    int text_diaposone = 64;
    
    std::vector<long long int> finded_indexes;

    void at(const FindException e);
    bool symbol_by_symbol(const std::string& pattern1, const std::string& pattern2);
    int find();

    
    
public:
    Rabin_carp();
    ~Rabin_carp();

    void set_pattern(const std::string& pattern);
    void set_text_diaposone(const unsigned int new_diaposone);
    void input_pattern();
    void set_string(const std::string& filename);

    int rolling_hash(const std::string& pattern);
    int rolling_rehash(int hash_result, const std::string& prev_pattern, const std::string& new_pattern);
    void out_text();

    std::vector<long long int> find_by_index();
    void find_in_text();
};


#endif