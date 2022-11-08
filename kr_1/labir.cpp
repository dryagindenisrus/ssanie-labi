#include <iostream>
using namespace std;

int a[50];//глобальный массив для хранения множителей разложения

void R(int pos, long int n, int n1) {
    int i;
    for (i= n1; i*i <= n; i++) {
        if (n % i == 0) { 
            //если i - множитель числа n
            a[pos] = i;
            R(pos+1, n/i, i); 
        }
    }
        
    for (i=0;i<=pos-1;i++) {
        cout<< a[i] << "*";
    }
    cout << n << endl;
     
}

int main() {
    long int n = 64;
    R(0,n,2);
} 
