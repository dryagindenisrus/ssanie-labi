#include "Person_db.h"
#include <iostream>

int main() {

    std::string boo = "HGKJHGHG12313";
    std::cout << boo << std::endl;

    DataBase a;
    const Person pool = Person("Bugai", "Gaga", 2010, true, 1234123456);
    std::cout << pool << std::endl;


    return 0;
}