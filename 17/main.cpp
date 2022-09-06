#include <iostream>
#include "List.h"

int main() {
    List<char> lst;
    lst.push_back('1');
    lst.push_back('2');
    lst.push_back('3');
    lst.push_back('4');
    lst.push_back('5');
    lst.push_back('6');
    lst.push_back('7');
    lst.push_front('0');

    std::cout << "lst[-2] = " << lst[-2] << std::endl;
    std::cout << "len(lst) = " << lst.getSize() << std::endl;

    lst.find_and_erase('0');
    lst.find_and_erase('1');

    std::cout << "len(lst) = " << lst.getSize() << std::endl;
    std::cout << "lst[0] = " << lst[0] << std::endl;

    return 0;
}