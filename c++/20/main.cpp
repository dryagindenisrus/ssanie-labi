#include "Graph.h"
#include <iostream>
#include <vector>

using namespace std;

int main() {

    int size_matrix = 6;

    Graph a(size_matrix);

    a.set_start("start_point.txt");
    a.set_matrix("matrix.txt");
    a.out_matrix();
    a.bfs();
    a.out_path();
    a.draw_graph("graph.dot");

    return 0;
}