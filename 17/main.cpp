#include <iostream>
#include "List.h"

int main() {
    List<int> lst;
    lst.push_back(5);
    lst.push_back(3);
    lst.push_back(7);

    std::cout << lst.getSize() << std::endl;

    return 0;
}