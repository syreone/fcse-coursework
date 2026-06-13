#include <iostream>
using namespace std;

int main() {
    int n, m;
    cin>>n>>m;
    int matrica[n][m];

    for (int i=0; i<n; i++) {
        for (int j=0; j<m; j++) {
            cin>>matrica[i][j];
        }
    }

    int pozI, pozJ;
    cin>>pozI>>pozJ;

    int k1, k2, k3, k4;
    k1 = k2 = k3 = k4 = 0;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (i < pozI) {
                if (j < pozJ)
                    k2 += matrica[i][j];
                else
                    k1 += matrica[i][j];
            } else {
                if (j < pozJ)
                    k3 += matrica[i][j];
                else
                    k4 += matrica[i][j];
            }
        }
    }
    cout << k1 << " " << k2 << " " << k3 << " " << k4 << endl;

    return 0;
}