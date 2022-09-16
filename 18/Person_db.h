#ifndef PERSON_DB_H
#define PERSON_DB_H

#include <iostream>
#include <string>
#include <list>


class Person {
    public: 
        std::string first_name;
        std::string last_name;
        std::string number_of_passport;
        unsigned int year_of_birth; 
        bool sex;
        // true - male, false - female
        friend bool operator== (const Person &p1, const Person &p2) { return p1.number_of_passport == p2.number_of_passport; };
        friend bool operator!= (const Person &p1, const Person &p2) {return p1.number_of_passport != p2.number_of_passport; };
        friend std::ostream& operator<< (std::ostream &os, const Person &p) {
            std::string osex;
            p.sex==true? osex = "male":osex = "female";
            return os << "{\n\tfirst_name: \x1B[33m" << p.first_name << "\033[0m,\n\tfirst_name: \x1B[33m" << p.last_name
            << "\033[0m,\n\tyear_of_birth: \x1B[33m" << p.year_of_birth << "\033[0m,\n\tsex: \x1B[33m" << osex 
            << "\033[0m,\n\tnumber_of_passport: \x1B[33m" << p.number_of_passport << "\033[0m\n}";
            // \x1B[32m    \033[0m
        }
        Person(
            std::string first_name, 
            std::string last_name, 
            unsigned int year_of_birth, 
            bool sex,
            std::string number_of_passport
        );
        ~Person();
};


class DataBase {
    private:
        std::list<Person> items;
    public:
        friend std::ostream& operator<< (std::ostream &os, const DataBase &db) {
            DataBase p = db;
            if (p.items.empty()) {
                    return os << "Database is empty (\x1B[33m0\033[0m entities)";
            } else {
                os << "DataBase (\x1B[33m" << p.items.size() << "\033[0m entities),\n" << "Size: \x1B[33m" 
                << sizeof(DataBase) + sizeof(Person)*p.items.size() << " bytes\033[0m.\n[" << std::endl;
                Person current_person = p.items.front();
                std::list<Person>::iterator current = p.items.end();
                
                while (current != p.items.begin()) {
                    --current;
                    Person current_person = *current;
                    os << current_person << ", \n";
                }
            }
            return os << "]" << std::endl;
        }
        void info();
        void create(const std::string& filename);
        void save(const std::string& filename);
        void load(const std::string& filename);
        void add(const Person& p);
        Person erase(const Person& p);
        ~DataBase();
};



#endif