#include "Person_db.h"
#include <fstream>


Person::Person(
    std::string first_name, 
    std::string last_name, 
    unsigned int year_of_birth, 
    bool sex,
    std::string number_of_passport 
) {
    this->first_name = first_name;
    this->last_name = last_name;
    this->year_of_birth = year_of_birth;
    this->sex = sex;
    this->number_of_passport = number_of_passport;
};


Person::~Person() {};


DataBase::~DataBase() {
    this->items.clear();
};


void DataBase::add(const Person& p) {
    if (this->items.empty()) {
        this->items.push_back(p);
    } else {
        Person person1 = p;
        Person current_person = this->items.front();
        std::list<Person>::iterator current = this->items.end();

        while (current != this->items.begin()) {
            --current;
            Person current_person = *current;
            if (current_person == person1) {
                return;
            }
        }
        this->items.push_back(p);
    } 
};


void DataBase::save(const std::string& filename) {
    std::ofstream out;
    Person current_person = this->items.front();
    out.open(filename);
    if (out.is_open()) {
        std::list<Person>::iterator current = this->items.end();
        while (current != this->items.begin()) {
            --current;
            Person current_person = *current;
            out << "{\n" << current_person.first_name << "\n" << current_person.last_name
            << "\n" << current_person.year_of_birth << "\n" << current_person.sex << "\n" 
            << current_person.number_of_passport << "\n}," << std::endl;
        }
    }
    out.close();
    std::cout << "\x1B[32mDataBase writed!\033[0m\t" << std::endl;
};


void DataBase::load(const std::string& filename) {
    std::string s;
    std::ifstream input;    
    int str_counter;

    std::string first_name;
    std::string last_name; 
    unsigned int year_of_birth; 
    bool sex;
    std::string number_of_passport; 

    input.open(filename);
    std::cout << "\x1B[33mLoading...\033[0m\t" << std::endl;

    if (input.is_open()) {
        while(getline(input, s)) { 
            if (s == "{") { 
                str_counter = 0; 
            }
            switch (str_counter) {
                case 0:
                    if (s != "{") {
                        std::cout << "\x1B[31mDataBase FAILED!!!\033[0m" << std::endl;
                        return;
                    };
                    break;
                case 1:
                    first_name = s;
                    break;
                case 2:
                    last_name = s;
                    break;
                case 3:
                    year_of_birth = std::stoi(s);
                    break;
                case 4:
                    sex = std::stoi(s);
                    break;
                case 5:
                    number_of_passport = s;
                    break;
                case 6:
                    if (s != "},") {
                        std::cout << "\x1B[31mDataBase FAILED!!!\033[0m" << std::endl;
                        return;
                    };
                    break;
                default:
                    break;
            }
            if (str_counter == 6) {
                const Person person = Person(
                    first_name, 
                    last_name, 
                    year_of_birth, 
                    sex, 
                    number_of_passport
                );
                this->add(person);
            }
            str_counter++;
        }
    }
    std::cout << "\x1B[32mDataBase Loaded!\033[0m" << std::endl;
};


void DataBase::info() {
    if (this->items.empty()) {
            std::cout << "Database is empty (\x1B[33m0\033[0m entities)" << std::endl;
    } else {
        std::cout << "DataBase (\x1B[33m" << this->items.size() << "\033[0m entities),\n" << "Size: \x1B[33m" 
        << sizeof(DataBase) + sizeof(Person)*this->items.size() << " bytes\033[0m.\n" << std::endl;        
    }
};


Person DataBase::erase(const Person& p) {
    Person person = p;
    std::ofstream out;
    Person current_person = this->items.front();
    std::list<Person>::iterator current = this->items.end();

    while (current != this->items.begin()) {
            --current;
            Person current_person = *current;

            if (current_person == person) {
                this->items.remove(current_person);
                return current_person;
            }
        }
    return p;
}
