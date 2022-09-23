#ifndef GRAFIK_H
#define GRAFIK_H

#include <vector>


struct Edge {
    int src, dest;
};


struct Lenght
{
    int point;
    int lenght;
};


class Graph
{
    private:   
        int count_of_point;
        std::vector<std::vector<int>> adjList;
        std::vector<Edge> edges;
        std::vector<Lenght> lenght_of_pathes;
        void generate(std::vector<Edge> const &edges, int n);

    public:
        Graph(int matrix_size, int** matrixa);
        void bfs();
        void out_graph();
};
 

#endif