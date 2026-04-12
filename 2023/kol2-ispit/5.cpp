#include <iostream>
#include <string.h>
using namespace std;

int main() {
    int suma;
    cin>>suma;
    char shifra[10];
    char maxShifra[10];
    int tip;
    double koef;
    double maxKoef = 0;
    int maxDobivka = 0;
    int maxTip = 0;
    double proizvod = 1.0;

    while(true) {
        cin>>shifra;
        if (strcmp(shifra, "#") == 0) {
            break;
        }
        cin>>tip;
        cin>>koef;

        if(koef>maxKoef) {
            maxKoef = koef;
            maxTip = tip;
            strcpy(maxShifra, shifra);
        }
        proizvod = proizvod * koef;
    }

    double dobivka = proizvod * suma;
    cout<<maxShifra<<" "<<maxTip<<" "<<maxKoef<<endl;
    cout<<dobivka<<endl;
    return 0;
}