#include "Graph.h"
#include <iostream>
#include <vector>

using namespace std;

int main() {

    int size_matrix = 5;
    int** matrix;

    
    matrix = new int*[size_matrix];
    for (int i = 0; i < size_matrix; i++) 
    {
        matrix[i] = new int[size_matrix] {0};
    }

    matrix[0][1] = 1; matrix[0][2] = 1; matrix[0][4] = 1; matrix[1][3] = 1; 
    matrix[1][0] = 1; matrix[2][0] = 1; matrix[4][0] = 1; matrix[3][1] = 1;

    Graph a(size_matrix, matrix);

    a.set_start("start_point.txt");
    a.out_matrix();
    a.bfs();
    a.out_path();

    return 0;
}