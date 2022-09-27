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
    
    int current_index = 1;

    int pattern_hash;
    int h;

    std::vector<int> finded_indexes;
    int first_finded_index;
    void at(const FindException e);
    bool symbol_by_symbol(const std::string& pattern1, const std::string& pattern2);

    
    
public:
    int pattern_size;
    Rabin_carp();
    ~Rabin_carp();
    void set_pattern(const std::string& pattern);
    void input_pattern();
    void set_string(const std::string& filename);
    int rolling_hash(const std::string& pattern);
    int rolling_rehash(int hash_result, char new_symb);
    std::vector<int> find_by_index();
    std::string find_in_text();
    void out_text();
    int find();
    int hInit(int str_lenght);
    int ring_hash(std::string str, int prev_hash);
};


#endif