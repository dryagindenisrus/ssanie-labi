#include <iostream>

int fx;
int fy;
int dx[4] = {0, 1, 0, -1};
int dy[4] = {-1, 0, 1, 0};
const int size = 4;
int count = 0;

void print(std::ostream &os, int a[][size]) {
    for (int i = 0; i < size; i++){
        for (int j = 0; j < size; j++){
            std::cout << (a[i][j]) << " ";
        }
        std::cout << std::endl << std::endl;
    }
}

void foo(int n, int a[][size], int x, int y){
    print(std::cout, a);
    std::cout << x << " ++++ " << y << std::endl;
    // Это активировать если нужно чтобы остановился на первом правильном пути
    // if (count == 1) return;

    // Добавляем то, что мы тут прошли
    a[x][y] = n;

    // Работаем по направлениям
    for (int i = 0; i < 4; i++){
        int nextX = x + dx[i], nextY = y + dy[i];
        if (( nextX >= size) || (nextX < 0)) continue;
        if (( nextY >= size) || (nextY + dy[i] < 0)) continue;
        if (a[nextX][nextY] == -3) {
            print(std::cout, a);
            std::cout << x << " --------- " << y << std::endl;
            count++;
        }
        if (a[nextX][nextY] != 0) continue;
        foo(n+1, a, x + dx[i], y + dy[i]);
    }

    a[x][y] = 0;
}

int main() {
    int data[size][size] = { {0, 0, 0, 0},
                             {0, -1, -3, 0},
                             {0, -1, -1, 0},
                             {-2, 0, 0, 0} };

    // Наши координаты
    int x, y;

    x = 3, y = 0;
    fx = 1, fy = 2;

    foo(1, data, x, y);
}