#include <iostream>
using namespace std;

int maksCifra(int n) {
    if (n == 0) {
        return 0;
    }
    int cifra = n%10;
    int cifra1 = maksCifra(n/10);
    if (cifra>cifra1) {
        return cifra;
    }else {
        return cifra1;
    }
}

int main() {
    int n;
    while (cin>>n) {
        cout<<maksCifra(n)<<endl;
    }
    return 0;
}