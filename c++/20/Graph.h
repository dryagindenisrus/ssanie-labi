#ifndef GRAFIK_H
#define GRAFIK_H

#include <vector>
#include <fstream>



class Graph
{
    private:
        int start;
        int count_of_point;
        std::vector<int> matrix;
        std::vector<int> lenght_of_pathes;

    public:
        Graph(int matrix_size);
        void bfs();
        void out_path();
        void out_matrix();
        void set_start(const std::string& filename);
        void set_matrix(const std::string& filename);
        void draw_graph(const std::string& filename);
};


#endif