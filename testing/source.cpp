#include <iostream>
#include <string>
#include <math.h>
using namespace std;

class Complex {
    private:
        int real;
        int imagine;
    public:
        void help();
        void set(string input);
        int get();
        void out();
};

void Complex::out() {
    int real_o;
    int imagine_o;
    if (this->real != 0) {
        real_o = this-> real;
    }
    if (this->imagine != 0) {
        imagine_o = this-> imagine;
        cout << real_o << "+" << imagine_o << "i" << endl;

    }
    if (this->imagine > 0) {
        cout << real_o << "+" << imagine_o << "i" << endl;

    } else if (this->imagine < 0) {
        cout << real_o << "-" << imagine_o << "i" << endl;

    }
}





int main() {

    string str = "2143 23k423hk423 23m4bkj23b4k23 234234 23423432 fdg ";
    // cin >> str;
    cout << str << endl;

    return 0;
}
