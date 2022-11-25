#include "Graph_painting.h"


void Graph::set_matrix(const std::string& matrix_filename)
{
    std::string s;
    std::ifstream input; 
    input.open(matrix_filename);
    int row = 0;
    
    if (input.is_open()) 
    {
        while(getline(input, s)) 
        { 
            std::vector<int> row_vector;
            for (int coloumn=0; coloumn<(s.size()); coloumn+=2)
            {
                row_vector.push_back(std::stoi(s.substr(coloumn, 1)));
                if (std::stoi(s.substr(coloumn, 1)) == 1)
                {
                    if (coloumn >= row*2)
                    {
                        this->adjacency[row].push_back(coloumn/2);
                        this->adjacency[coloumn/2].push_back(row);
                    }
                }
            }
            row++;
            this->graph_matrix.push_back(row_vector);
        }
        input.close();
    }
};


void Graph::addEdge(int v, int w)
{
    // addEdge method for undirected graph
    adjacency[v].push_back(w);
    adjacency[w].push_back(v);
};


bool Graph::graph_color_util(int m, int v)
{
    // If all vertices are assigned a color then return true
    if (v == V)
    {
        return true;
    }
        
    // Try different colors for vertex V
    for (int i = 1;i <= m; i++) 
    {
        // check for assignment safety
        if (is_safe_to_color(v, i))
        {
            color[v] =i;
            // recursion for checking other vertices
            if (graph_color_util(m, v + 1))
            {
                return true;
            }
            // if color doesnt lead to solution
            color[v] = 0;
        }
    }
    // If no color can be assigned to  vertex
    return false;
}


bool Graph::is_safe_to_color(int v, int c)
{
    for (int i = 0; i < V; i++)
    {
        if (this->graph_matrix[v][i] == 1 && c == color[i])
        {
            return false;
        }
    }
    return true;
};


void Graph::add_colors_drawing()
{
    for (int i = 0; i < V; i++)
    {
        this->colored_vertices.push_back(color[i]);
    }
};


void Graph::print_chromatic_number()
{
    std::cout << "Chromatic number: " << this->chromatic_num << std::endl;
};

void Graph::chromatic_number()
{
    int result[V];
    // Assign the first color to first vertex
    result[0]  = 0;
    // Initialize remaining V-1 vertices as unassigned
    for (int u = 1; u < V; u++)
    {
        result[u] = -1; 
        // no color is assigned to u
    }
    // A temporary array to store the available colors. True
    // value of available[cr] would mean that the color cr is
    // assigned to one of its adjacent vertices
    bool available[V];
    for (int cr = 0; cr < V; cr++)
    {
        available[cr] = false;
    }
    // Assign colors to remaining V-1 vertices
    for (int u = 1; u < V; u++)
    {
        // Process all adjacent vertices and flag their colors
        // as unavailable
        std::list<int>::iterator i;
        for (i = adjacency[u].begin(); i != adjacency[u].end(); ++i)
        {
            if (result[*i] != -1)
            {
                available[result[*i]] = true;
            }         
        }
                
        // Find the first available color
        int cr;
        for (cr = 0; cr < V; cr++)
        {
            if (available[cr] == false)
            {
                break;
            }
        }
                            
        result[u] = cr; // Assign the found color      
    }
    int max = 0;
    for (int i = 0; i < this->V; i++)
    {
        if (result[i] > max)
        {
            max = result[i];
        }
    }

    this->chromatic_num = max;
};


bool Graph::rlf_coloring()
{
    // Initialize all color values as 0. 
    int m = this->chromatic_num;
    for (int i = 0; i < this->V; i++)
    {
        color.push_back(0);
    }

    // Call graphColorUtil() for vertex 0
    if (!graph_color_util(m, 0)) 
    {
        std::cout << "Color schema not possible" << std::endl;
        return false;
    }

    // Print the color schema of vertices
    add_colors_drawing();
    return true;
}


void Graph::print_matrix()
{   
    for(int i=0; i<this->graph_matrix.size(); i++)
    {
        for(int j=0; j<this->graph_matrix.size(); j++)
        {
            std::cout << this->graph_matrix[i][j] << " ";
        }
        std::cout << std::endl;
    }
};


// Assigns colors (starting from 0) to all vertices
// the assignment of colors
void Graph::greedy_сoloring()
{
    int result[V];
    // Assign the first color to first vertex
    result[0]  = 0;
    // Initialize remaining V-1 vertices as unassigned
    for (int u = 1; u < V; u++)
    {
        result[u] = -1; 
        // no color is assigned to u
    }
    // A temporary array to store the available colors. True
    // value of available[cr] would mean that the color cr is
    // assigned to one of its adjacent vertices
    bool available[V];
    for (int cr = 0; cr < V; cr++)
    {
        available[cr] = false;
    }
    // Assign colors to remaining V-1 vertices
    for (int u = 1; u < V; u++)
    {
        // Process all adjacent vertices and flag their colors
        // as unavailable
        std::list<int>::iterator i;
        for (i = adjacency[u].begin(); i != adjacency[u].end(); ++i)
        {
            if (result[*i] != -1)
            {
                available[result[*i]] = true;
            }         
        }
                
        // Find the first available color
        int cr;
        for (cr = 0; cr < V; cr++)
        {
            if (available[cr] == false)
            {
                break;
            }
        }
                            
        result[u] = cr; // Assign the found color
        // Reset the values back to false for the next iteration
        for (i = adjacency[u].begin(); i != adjacency[u].end(); ++i)
        {
            if (result[*i] != -1)
            {
                available[result[*i]] = false;
            }           
        }         
    }

    for (int u = 0; u < V; u++)
    {
        this->colored_vertices.push_back(result[u]);
    }
                
};


void Graph::draw_graph(const std::string& output_filename) 
{
    std::ofstream out;
    out.open(output_filename);

    if (out.is_open()) {
            out << "graph Graph_сoloring { \n\tnode [shape=circle width=0.66 style=filled]\n";

            for (int u = 0; u < V; u++) {
                out << "\t" << u << " [color = " << this->colors[this->colored_vertices[u]] 
                << std::uppercase  << " label=\"" << u << "\"]\n";
            }

            for (int u = 0; u < V; u++)
            {
                for (int i: this->adjacency[u])
                {   
                    if (i > u)
                    {
                        out << "\t" << u << " -- " << i << ";\n";
                    } 
                }
            }
    }
    out << "}";
    out.close();
};

