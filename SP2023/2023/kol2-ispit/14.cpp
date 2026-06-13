#include <iostream>
#include <cmath>
#include <cstring>
using namespace std;

int main() {
    int n;
    cin >> n;
    int m = 2 * n;
    int a[n][m];

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++)
            cin >> a[i][j];
    }
    int b[m][n];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            b[i][j] = a[i][j];
        }
    }
    for (int i = 0; i < n; i++) {
        for (int j = n; j < m; j++)
            b[n + i][j - n] = a[i][j];

    }
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++)
            cout << b[i][j] << " ";
        cout << endl;

    }
}