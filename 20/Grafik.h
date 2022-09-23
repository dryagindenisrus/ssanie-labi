#ifndef GRAFIK_H
#define GRAFIK_H

#include <list>


class Grafik
{
private:
    int matrix_size;
    int** matrix;
public:
    Grafik(int matrix_size, int** matrixa);
    void print_matrix();
    ~Grafik();
};


#endif