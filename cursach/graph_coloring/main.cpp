#include "Graph_painting.h"

using namespace std;


// Driver program to test above function
// dot -Tsvg -O file.dot
// ./file.dot.svg
int main()
{
    Graph test_graph(5);
    test_graph.set_matrix(".matrix");
    // test_graph.addEdge(0, 1);
    // test_graph.addEdge(0, 2);
    // test_graph.addEdge(1, 2);
    // test_graph.addEdge(1, 3);
    // test_graph.addEdge(2, 3);
    // test_graph.addEdge(3, 4);
    cout << "Coloring of graph" << endl;
    // test_graph.rlf_coloring();
    test_graph.greedy_сoloring();
    test_graph.print_matrix();
    test_graph.draw_graph("file.dot");
    test_graph.chromatic_number();
    test_graph.print_chromatic_number();
  
    return 0;
}