#include <iostream>
#include "List.h"

int main() {
    List<char> lst;
    List<char> lst1;
    List<Point> list1;

    lst.push_back('1');
    lst.push_back('2');
    lst.push_back('3');
    lst.push_back('4');
    lst.push_back('5');
    lst.push_back('6');
    lst.push_back('7');
    lst.push_front('0');

    lst1.push_back('0');
    lst1.push_back('0');
    lst1.push_back('0');


    std::cout << "PERESECHENIE: " << lst.intersection_of_lists(lst1)[0] << std::endl;
    std::cout << "PERESECHENIE: " << lst.intersection_of_lists(lst1)[1] << std::endl;



    std::cout << "lst[-2] = " << lst[-2] << std::endl;
    std::cout << "len(lst) = " << lst.getSize() << std::endl;

    lst.find_and_erase('9');
    lst.find_and_erase('2');

    std::cout << "len(lst) = " << lst.getSize() << std::endl;
    // std::cout << "lst[0] = " << lst[12] << std::endl;


    // For Struct Point
    Point a, b;
    a.x = 2; a.y = 4; a.z = 7;
    b.x = 2; b.y = 4; b.z = 7;

    list1.push_back(a);
    list1.push_back(b);
    list1.push_back(b);

    std::cout << "len(list1) = " << list1.getSize() << std::endl;
    
    return 0;
}