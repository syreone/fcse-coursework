#include <iostream>
#include <cmath>
using namespace std;

int main() {
    int n,m;
    cin>>n>>m;
    int matrica[n][m];

    for (int i=0; i<n; i++) {
        for (int j=0; j<m; j++) {
            cin>>matrica[i][j];
        }
    }

    int rezultat[n];

    for (int i=0; i<n; i++) {
        int suma = 0;
        for (int j=0; j<m; j++){
            suma += matrica[i][j];
        }
        double ars = (double)suma/m;

        double maxRazlika = -1;
        int najoddalecen = matrica[i][0];

        for (int j = 0; j < m; j++) {
            double razlika = fabs(matrica[i][j] - ars);
            if (razlika > maxRazlika) {
                maxRazlika = razlika;
                najoddalecen = matrica[i][j];
            }
        }
        rezultat[i] = najoddalecen;
    }
    for (int i = 0; i < n; i++) {
        cout << rezultat[i] << " ";
    }
    cout << endl;

    return 0;
}