#include "Rabin_carp.h"


void Rabin_carp::at(const FindException e) {
    std::cerr << "\n\x1B[31mListException:\033[0m\t" << e.what() << std::endl;
}


void Rabin_carp::set_pattern(const std::string& pattern)
{
    this->pattern = pattern;
    this->pattern_size = pattern.size();
}


int Rabin_carp::init_rolling_hash(const std::string& pattern)
{
    int hash_result = 1;
    for (unsigned int i = 1; i < pattern_size; i++) 
    {
        hash_result = (hash_result * d) % prime_const;
    }
    return hash_result;
};


// http://mindhalls.ru/rabin-karp-search/   01.03.2017  Автор: Кузьминых Кирилл
int Rabin_carp::rolling_hash(const std::string& pattern)
{
    int hash_result;
    int str_size = this->pattern.size();

    for (int i = 0; i < str_size; i++)
    {   
        // prevHash += (d*prevHash + (int)str[i]) % p;
        // c - value of char in ASCII
        int c = pattern[i];
        // h - const in ".h" file
        int prev_hash = hash_result;
        hash_result = (hash_result + (d * hash_result + c)) % prime_const;
    }
    return hash_result;
};


void Rabin_carp::set_string(const std::string& filename)
{
    std::string s;
    std::ifstream input;
    int str_counter;

    input.open(filename);
    std::cout << "\x1B[33mLoading...\033[0m\t" << std::endl;

    try
    {
        if (input.is_open()) 
        {
            while(getline(input, s)) 
            { 
                this->text.append(s);
            }
        }
        else {
            throw FindException("Cannot load file");
        }
    } 
    catch (FindException &e)
    {
        this->at(e);
    }
};


void Rabin_carp::out_text()
{
    std::cout << this->text << std::endl;
};


int Rabin_carp::find()
{
    for (int i = 0; i < this->text.size(); i++)
    {
        if (this->text.substr(i, this->pattern_size) == pattern)
        {
            this->finded_index = i;
            std::cout << "..." << this->text.substr(i-64, 64) 
            << "\x1B[33m" << this->text.substr(i, this->pattern_size) << "\033[0m" 
            << this->text.substr(i+pattern_size, this->pattern_size+32) 
            << "..." << std::endl;
        }
    }
    return this->finded_index;
};


int Rabin_carp::find_by_index()
{
    this->find();
    return this->finded_index;
}


bool Rabin_carp::symbol_by_symbol(const std::string& pattern1, const std::string& pattern2)
{
    if (pattern1 == pattern2) { return true; }
    else { return false; }
};


Rabin_carp::Rabin_carp()
{
};


Rabin_carp::~Rabin_carp()
{  
};