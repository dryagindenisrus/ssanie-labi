#include <iostream>
#include <string>
#include <list>


class Person {
    public:
        Person(
            std::string first_name, 
            std::string last_name, 
            unsigned int year_of_birth, 
            bool sex,
            unsigned long long number_of_passport
        );
        ~Person();
};


class DataBase {
    private:
        std::list<Person> items;
    public:
        void create(const std::string& filename);
        void save(const std::string& filename);
        void load(const std::string& filename);
        void add(const Person& p);
        void erase(const Person& p);
};


