#include <iostream>
using namespace std;

int main() {
    int n, m;
    cin>>n>>m;
    char matrica[n][m];

    for (int i=0; i<n; i++) {
        for (int j=0; j<m; j++) {
            cin>>matrica[i][j];
        }
    }
    int redici = 0; int koloni = 0;

    for (int i=0; i<n; i++) {
        int posledovatelni = 0;
        for (int j=0; j<m; j++) {
            if (matrica[i][j] == '1') {
                posledovatelni++;
                if (posledovatelni>=3) {
                    redici++;
                    break;
                }
            }else {
                posledovatelni = 0;
            }
        }
    }
    for (int j=0; j<m; j++) {
        int posledovatelni = 0;
        for (int i=0; i<n; i++) {
            if (matrica[i][j] == '1') {
                posledovatelni++;
                if (posledovatelni>=3) {
                    koloni++;
                    break;
                }
            }else {
                posledovatelni = 0;
            }
        }
    }

    cout<<redici+koloni<<endl;
}