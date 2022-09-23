#include "Graph.h"
#include <iostream>
#include <vector>

using namespace std;

int main() {

    int size_matrix = 4;
    int** matrix;

    
    matrix = new int*[size_matrix];
    for (int i = 0; i < size_matrix; i++) 
    {
        matrix[i] = new int[size_matrix] {0};
    }
    // for (int i = 0; i < size_matrix; i++)
    // {
    //     for (int j = i+1; j < size_matrix; j++)
    //     {
    //         matrix[i][j] = 1;
    //     }
    // }
    matrix[0][1] = 1; matrix[0][2] = 1;

    Graph a(4, matrix);
    a.out_graph();
        



    return 0;
}