#include "Graph.h"
#include <iostream>
#include <queue>
#include <vector>


Graph::Graph(int matrix_size, int** matrixa)
{   
    for (int i = 0; i < matrix_size; i++)
    {
        for (int j = 0; j < matrix_size; j++)
        {   
            this->matrix.push_back(matrixa[i][j]);
        }
    }
    this->count_of_point = matrix_size;
    this->lenght_of_pathes.resize(count_of_point);
};


void Graph::out_matrix()
{
    std::cout << "MATRIX:" << std::endl;
    for (int i = 0; i < this->count_of_point; i++)
    {
        for (int j = 0; j < this->count_of_point; j++)
        {
            std::cout << this->matrix[i*this->count_of_point + j] << " ";
        }
        std::cout << std::endl;
    }
    std::cout << std::endl;
}


void Graph::set_start(const std::string& filename)
{
    std::string s;
    std::ifstream input; 
    input.open(filename);
    if (input.is_open()) 
    {
        while(getline(input, s)) 
        { 
            this->start = std::stoi(s);
        }
    }
};


void Graph::out_path()
{
    std::cout << "LENGTS:" << std::endl;
    for (int i=0; i<this->count_of_point; i++) 
    {
        if (i!=this->start)
        {
            std::cout << start << " --> " << i << " (" << this->lenght_of_pathes[i] << ")" << std::endl;
        }
    }
};


void Graph::bfs()
{   
    int visited[this->count_of_point] = { 0 };
    std::queue<int> q;

    visited[this->start] = 1;
    q.push(start);
    int counter = 1;
    int prev = start;
    while (!q.empty())
    {   
        int current = q.front();
        if (prev != current) { counter++; }
        q.pop();
        for (int i = 0; i < this->count_of_point; i++)
        {   
            if (this->matrix[current*this->count_of_point + i] == 1 && visited[i] == 0)
            {
                visited[i] = 1;
                this->lenght_of_pathes[i] = counter;
                q.push(i);  
            }
        }        
    }
};

