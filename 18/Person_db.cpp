#include "Person_db.h"


Person::Person(
    std::string first_name, 
    std::string last_name, 
    unsigned int year_of_birth, 
    bool sex,
    unsigned long long number_of_passport 
) {
    this->first_name = first_name;
    this->last_name = last_name;
    this->year_of_birth = year_of_birth;
    this->sex = sex;
};


Person::~Person() {

};


