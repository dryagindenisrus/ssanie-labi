#include "Graph.h"
#include <iostream>


Graph::Graph(int matrix_size, int** matrixa)
{   
    for (int i = 0; i < matrix_size-1; i++)
    {
        for (int j = i+1; j < matrix_size; j++)
        {
            if (matrixa[i][j] == 1) {
                this->edges.push_back({i, j});
            }
        }
    }
    this->count_of_point = matrix_size;
    generate(this->edges, matrix_size);
};


void Graph::generate(std::vector<Edge> const &edges, int n)
{
    this->adjList.resize(n);
        for (auto &edge: edges)
        {
            this->adjList[edge.src].push_back(edge.dest);
            // adjList[edge.dest].push_back(edge.src);
        }
};


void Graph::out_graph()
{
    for (int i = 0; i < this->count_of_point; i++)
    {
        std::cout << i << " --> ";
        if (this->adjList[i].size() == 0)
            {
                std::cout << "NULL";
            } else 
            {
                for (int v: this->adjList[i]) 
                {
                    std::cout << v << " ";
                }
            } 
        
        std::cout << std::endl;
    }
}
