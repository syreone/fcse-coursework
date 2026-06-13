// od sv se vnesuvaat 17 clenovi na niza, sumiraj gi parnite i neparnite clenovi

#include<iostream>
using namespace std;

int main() {
    int a[100];

    for (int i = 0; i < 17; i++) {
        cin >> a[i];
    }
    int sumaParni = 0, sumaNeparni = 0;
    for (int i = 0; i < 17; i++) {
        if (a[i] % 2 == 0) {
            sumaParni += a[i];
        } else {
            sumaNeparni += a[i];
        }
    }
    cout << "Suma parni: " << sumaParni << endl;
    cout << "Suma neparni: " << sumaNeparni << endl;
    return 0;
}