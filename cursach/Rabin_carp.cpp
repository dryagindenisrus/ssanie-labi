#include "Rabin_carp.h"
// http://mindhalls.ru/rabin-karp-search/   01.03.2017  Автор: Кузьминых Кирилл


// method for throwing exceptions
void Rabin_carp::at(const FindException e) {
    std::cerr << "\n\x1B[31mListException:\033[0m\t" << e.what() << std::endl;
}


// inputing substring from code as method
void Rabin_carp::set_pattern(const std::string& pattern)
{
    this->pattern = pattern;
    this->pattern_size = pattern.size();
}


// inputing substring from keyboard
void Rabin_carp::input_pattern()
{
    std::string pattern_keyboard;
    std::cin >> pattern_keyboard;
    this->pattern = pattern_keyboard;
    this->pattern_size = pattern.size();
};


// set text from file
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
    std::cout << "\x1B[32mString loaded!\033[0m\t" << std::endl;
};


// output for text
void Rabin_carp::out_text()
{
    std::cout << this->text << std::endl;
};


// rolling hash for string
int Rabin_carp::rolling_hash(const std::string& pattern)
{
    int hash = 0;
    int str_size = this->pattern_size;

    for (int i = 0; i < str_size; i++)
    {   
        // c - value of char in ASCII
        int char_code = pattern[i];
        // d - const in ".h" file
        hash = (hash * d + char_code) % prime_const;
        // std::cout << hash_result << std::endl;
    }

    return hash;
};


// rehash function for text
int Rabin_carp::rolling_rehash(int prev_hash, const std::string& prev_pattern, const std::string& new_pattern)
{
    int hash = prev_hash;
    int multiplier = 1;
    int base = d;

    for (int i = 1; i < this->pattern_size; i++) 
    {
        multiplier *= base;
        multiplier %= prime_const;
    } 

    int char_code = prev_pattern[0];
    int new_char_code = new_pattern[this->pattern_size-1];

    hash += prime_const;
    hash -= (multiplier * char_code) % prime_const;
    hash *= base;
    hash += new_char_code;
    hash %= prime_const;

    return hash;
};


// classic find method
int Rabin_carp::find()
{
    this->pattern_hash = this->rolling_hash(this->pattern);
    int text_substring_hash = this->rolling_hash(this->text.substr(0, this->pattern_size));

    // std::cout << text_substring_hash << std::endl;

    for (int i = 0; i < this->text.size(); i++)
    {
        // std::cout << "p(" << this->pattern_hash << ") - s(" << this->rolling_hash(this->text.substr(i, this->pattern_size)) << ")" << std::endl;
        if (this->pattern_hash == text_substring_hash)
        {
            if (this->symbol_by_symbol(this->text.substr(i, this->pattern_size), this->pattern))
            {
                this->first_finded_index = i;
                this->finded_indexes.push_back(i);     
            }           
        }
        text_substring_hash = this->rolling_rehash(
            text_substring_hash, 
            this->text.substr(i, this->pattern_size),  
            this->text.substr(i + 1, this->pattern_size + 1)
        );

    }

    return 0;
};


// find sustring and return index of finded pattern
std::vector<long long int> Rabin_carp::find_by_index()
{
    return this->finded_indexes;
}


void Rabin_carp::find_in_text()
{
    this->find();

    for (int i = 0; i < this->finded_indexes.size(); i++)
    {
        int prev_diaposone = this->text_diaposone;
        int post_diaposone = this->text_diaposone / 2;

        if (this->finded_indexes[i] < this->text_diaposone)
        {
            prev_diaposone = this->finded_indexes[i];
        }
        if (this->finded_indexes[i] + pattern_size + this->text_diaposone > this->text.size())
        {
            post_diaposone = this->text.size() - this->finded_indexes[i];
        }

        std::cout << "..." << this->text.substr(finded_indexes[i]-prev_diaposone, prev_diaposone) 
        << "\x1B[33m" << this->text.substr(finded_indexes[i], this->pattern_size) << "\033[0m" 
        << this->text.substr(finded_indexes[i]+pattern_size, this->pattern_size + post_diaposone) 
        << "..." << std::endl;
    }
    
}


// set diaposone of text for finded_in_text()
// DEFAULT VALUE: 64
void Rabin_carp::set_text_diaposone(const unsigned int new_diaposone)
{
    this->text_diaposone = new_diaposone;
}


// method for comparison symbol by symbol
bool Rabin_carp::symbol_by_symbol(const std::string& pattern1, const std::string& pattern2)
{
    // if (pattern1 == pattern2) { return true; }
    // else { return false; }

    for (int i = 0; i < this->pattern_size; i++)
    {
        if (pattern1[i] != pattern2[i])
        {
            return false;
        }
        
    }
    return true;
};


Rabin_carp::Rabin_carp()
{
};


Rabin_carp::~Rabin_carp()
{  
};
