#include <iostream>
#include <cmath>
#include <cstring>
using namespace std;

int main() {
    int X;
    cin>>X;
    int n, m;
    cin>>n>>m;
    int matrica[n][m];

    for (int i=0; i<n; i++) {
        for (int j=0; j<m; j++) {
            cin>>matrica[i][j];
        }
    }

    for (int i=0; i<n; i++) {
        int suma = 0;
        for (int j=0; j<m; j++) {
            suma +=matrica[i][j];
        }

        
        if (suma>X) {
            for (int j = 0; j < m; j++) {
                matrica[i][j] = 1;
            }
        } else if (suma<X) {
            for (int j = 0; j < m; j++) {
                matrica[i][j] = -1;
            }
        } else {
            for (int j = 0; j < m; j++) {
                matrica[i][j] = 0;
            }
        }
    }

    for (int i=0; i<n; i++) {
        for (int j=0; j<m; j++) {
            cout<<matrica[i][j]<<" ";
        }
        cout<<endl;
    }
    return 0;
}