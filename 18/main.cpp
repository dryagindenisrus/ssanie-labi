#include "Person_db.h"
#include <iostream>

int main() {

    DataBase a;
    const Person pool0 = Person("Bugai", "Gaga", 2010, true, "1234123456");
    const Person pool1 = Person("GGG", "Gaga", 2011, true, "1234123455");
    const Person pool2 = Person("OOO", "AAAA", 2012, false, "001203123123");
    const Person pool3 = Person("Misha", "Ezheliy", 2030, true, "4017123456");
    const Person pool4 = Person("Vasilina", "Mihailova", 2002, false, "401712343412");
    // a.add(pool0);
    // a.add(pool1);
    // a.add(pool2);
    // a.add(pool3);
    // a.add(pool4);
    
    // a.add(pool);
    // a.add(pool1);
    
    (pool0 == pool2);
    a.load("db.db");
    std::cout << a << std::endl;
    a.erase(pool0);
    a.save("mull.db");
    // a.save("db.db");

    a.info();

    

    return 0;
}