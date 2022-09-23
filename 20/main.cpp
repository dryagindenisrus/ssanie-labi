#include "Grafik.h"
#include <iostream>
#include <vector>

using namespace std;

int main() {

    int size_matrix = 4;
    int** matrix;
    matrix = new int*[size_matrix];
    for (int i = 0; i < size_matrix; i++) 
    {
        matrix[i] = new int[size_matrix];
    }
    
    for (int i = 0; i < size_matrix; i++)
    {
        for (int j = 0; j < size_matrix; j++)
        {
            matrix[i][j] = 4;
        }
    }

    
    Grafik a = Grafik(4, matrix);


    for (int i = 0; i < size_matrix; i++)
    {
        for (int j = 0; j < size_matrix; j++)
        {
            std::cout << "(" << matrix[i][j] << ") " ;
        }
        std::cout << std::endl;
    }
    
    a.print_matrix();
    

    return 0;
}