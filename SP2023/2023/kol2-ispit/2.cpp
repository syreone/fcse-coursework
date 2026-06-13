#include <iostream>
#include <cmath>
using namespace std;

int najznacajnaCifra(int broj) {
    broj = abs(broj);
    while (broj>=10) {
        broj /= 10;
    }
    return broj;
}

int main() {
    int n;
    while (cin>>n && n!=0) {
        int broj, maxBroj, maxCifra;
        cin>>broj;
        maxBroj = broj;
        maxCifra = najznacajnaCifra(broj);

        for (int i=1; i<n; i++) {
            cin>>broj;
            int cifra = najznacajnaCifra(broj);

            if (cifra>maxCifra) {
                maxCifra=cifra;
                maxBroj = broj;
            }
        }
        cout<<maxBroj<<endl;
    }
    return 0;
}