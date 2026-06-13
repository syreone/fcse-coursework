#include <iostream>
using namespace std;

int main() {
    int n;
    cin>>n;
    int dimenzija = 1;
    while (dimenzija * dimenzija < n) {
        dimenzija++;
    }
    int matrica[dimenzija][dimenzija];

    for (int i=0; i<dimenzija; i++) {
        for (int j=0; j<dimenzija; j++) {
            matrica[i][j] = 0;
        }
    }

    int broj = 1;
    for (int j=0; j<dimenzija; j++) {
        if (j%2 == 0) {
            for (int i=0; i<dimenzija; i++) {
                if (broj<=n) {
                    matrica[i][j] = broj++;
                }
            }
        }else {
            for (int i=dimenzija-1; i>=0; i--) {
                if (broj<=n) {
                    matrica[i][j] = broj++;
                }
            }
        }
    }

    for (int i=0; i<dimenzija; i++) {
        for (int j=0; j<dimenzija; j++) {
            cout<<matrica[i][j] << " ";
        }
        cout<<endl;
    }
    return 0;
}