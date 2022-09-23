#include "Grafik.h"
#include <iostream>


Grafik::Grafik(int matrix_size, int** matrixa)
{   
    this->matrix = new int*[matrix_size];

    for (int i = 0; i < matrix_size; i++) 
    {
        this->matrix[i] = new int[matrix_size];
    }
        
    for (int i = 0; i < matrix_size; i++)
    {
        for (int j = 0; j < matrix_size; j++)
        {
            this->matrix[i][j] = matrixa[i][j];
            std::cout << "[" << matrixa[i][j] << "," << this->matrix[i][j] << "]";
        }
        std::cout << std::endl;
    }
};


void Grafik::print_matrix() 
{
    std::cout << "!!!\n";
    for (int i = 0; i < this->matrix_size; i++)
    {
        for (int j = 0; j < this->matrix_size; j++)
        {
            std::cout << "[" << this->matrix[i][j] << "]" <<std::endl;
        }
        std::cout << std::endl;
    }
};


Grafik::~Grafik()
{
    for (int i = 0; i < this->matrix_size; i++)
        delete[] this->matrix[i];

    delete[] this->matrix;
};
