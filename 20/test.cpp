#include <iostream>
#include <vector>
using namespace std;
 
// Структура данных для хранения ребра Graph
struct Edge {
    int src, dest;
};
 
// Класс для представления graphического объекта
class Graph
{
public:
    // вектор векторов для представления списка смежности
    vector<vector<int>> adjList;
 
    // Конструктор Graphа
    Graph(vector<Edge> const &edges, int n)
    {
        // изменить размер вектора, чтобы он содержал `n` элементов типа `vector<int>`
        adjList.resize(n);
 
        // добавляем ребра в ориентированный graph
        for (auto &edge: edges)
        {
            // вставляем в конце
            adjList[edge.src].push_back(edge.dest);
 
            // раскомментируйте следующий код для неориентированного Graph
            // adjList[edge.dest].push_back(edge.src);
        }
    }
};
 
// Функция для печати представления списка смежности Graph
void printGraph(Graph const &graph, int n)
{
    for (int i = 0; i < n; i++)
    {
        // вывести номер текущей вершины
        cout << i << " --> ";
 
        // вывести все соседние вершины вершины `i`
        for (int v: graph.adjList[i]) {
            cout << v << " ";
        }
        cout << endl;
    }
}
 
// Реализация Graph с использованием STL
int main()
{
    // vector ребер Graph согласно схеме выше.
    // Обратите внимание, что vector инициализации в приведенном ниже формате будет
    // нормально работает в C++11, C++14, C++17, но не работает в C++98.
    vector<Edge> edges =
    {
        {0, 1}, {1, 2}, {2, 0}, {2, 1}, {3, 2}, {4, 5}, {5, 4}, {1, 3}
    };
 
    // общее количество узлов в Graph (от 0 до 5)
    int n = 6;
 
    // построить Graph
    Graph graph(edges, n);
 
    // вывести представление списка смежности Graph
    printGraph(graph, n);
 
    return 0;
}