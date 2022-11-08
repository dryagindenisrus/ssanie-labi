#include <iostream>

int x, y, fx, fy;
int dx[4] = {0, 1, 0, -1};
int dy[4] = {-1, 0, 1, 0};
const int size = 5;
int count = 0;

void foo(int n, int a[][size], int x, int y){
    if (count == 1) return;
    a[x][y] = n;
    std::cout << "(" << x << ", " << y << "), ";
    for (int i = 0; i < 4; i++){
        int nextX = x + dx[i], nextY = y + dy[i];
        if (( nextX >= size) || (nextX < 0)) continue;
        if (( nextY >= size) || (nextY + dy[i] < 0)) continue;
        if (a[nextX][nextY] == -3) {
            count++;
        }
        if (a[nextX][nextY] != 0) continue;
        foo(n+1, a, x + dx[i], y + dy[i]);
    }
    a[x][y] = 0;
}

int main() {
    int data[size][size] = {
        {0,  0,  0,  0, 0}, 
        {0, -1, -3, -1, 0}, 
        {0, -1, -1,  0, 0}, 
        {0,  0,  0,  0, 0}, 
        {-2, 0,  -1, 0, 0}};
    x = 3, y = 0;
    fx = 1, fy = 2;

    foo(1, data, x, y);
}