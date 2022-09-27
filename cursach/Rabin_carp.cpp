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
    int hash_result;
    int str_size = pattern.size();
    this->current_index = 0;

    for (int i = 0; i < str_size; i++)
    {   
        // c - value of char in ASCII
        int c = pattern[i];
        // h - const in ".h" file
        hash_result = (hash_result + (d * hash_result + c)) % prime_const;
        // std::cout << hash_result << std::endl;
    }

    return hash_result;
};


// rehash function for text
int Rabin_carp::rolling_rehash(int hash_result, char new_symb)
{
    int c = this->text[this->current_index + this->pattern_size];
    int b = this->text[current_index];

    int rehash_result = (hash_result + (d * hash_result + c)) % prime_const;

    this->current_index ++;

    return rehash_result;
};


// classic find method
int Rabin_carp::find()
{
    // this->pattern_hash = this->rolling_hash(this->pattern);
    // int text_substring_hash = this->rolling_hash(this->text.substr(0, this->pattern_size));

    // std::cout << text_substring_hash << std::endl;

    for (int i = 0; i < this->text.size(); i++)
    {
        // std::cout << "p(" << this->pattern_hash << ") - s(" << this->rolling_hash(this->text.substr(i, this->pattern_size)) << ")" << std::endl;
        if (this->text.substr(i, this->pattern_size) == pattern)
        {
            this->first_finded_index = i;

            std::cout << "..." << this->text.substr(i-64, 64) 
            << "\x1B[33m" << this->text.substr(i, this->pattern_size) << "\033[0m" 
            << this->text.substr(i+pattern_size, this->pattern_size+32) 
            << "..." << std::endl;
        }
    }

    return 0;
};


// h init mathod
int Rabin_carp::hInit(int str_lenght) {
    int d = 52;
    int    p = 65713;

    int h = 1;
    for(unsigned int i=1; i < str_lenght; i++) {
        h = (h*d) % p;
    }

    return h;
}


// find sustring and return index of finded pattern
std::vector<int> Rabin_carp::find_by_index()
{
    this->find();

    return this->finded_indexes;
}


// method for comparison symbol by symbol
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



int Rabin_carp::ring_hash(std::string str, int prev_hash) {
    // char* str, unsigned int strLen, int prevHash, int *h
    int str_lenght = this->pattern_size;

    int d = 52;
    int p = 65713;

    if(this->h == 0) {
        this->h = this->hInit(str_lenght);
    }

    if(prev_hash == 0) {
        for (unsigned int i = 0; i < str_lenght; i++) 
        {
            prev_hash += (d*prev_hash + (int)str[i]) % p;
        }
        if(prev_hash < 0) {
            prev_hash += p;
        }

        return prev_hash;
    }
    else {
        int hash = (d * (prev_hash - (int)str[0] * this->h) + (int)str[str_lenght]) % p;
        if(hash < 0) {
            hash += p;
        }

        return hash;
    }
}