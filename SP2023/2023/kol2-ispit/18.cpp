#include <iostream>
using namespace std;

int main() {
    int n;
    cin >> n;

    if (n < 2) {
        return 0;
    }

    float arr[n][n];
    float sumaGlavna = 0.0;
    float sumaSporedna = 0.0;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> arr[i][j];
        }
    }

    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            sumaGlavna += arr[i][j];
        }
    }

    for (int i = 1; i < n; i++) {
        for (int j = n - 1; j > n - 1 - i; j--) {
            sumaSporedna += arr[i][j];
        }
    }

    float arr2[n][n];

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (i == j) {
                arr2[i][j] = sumaGlavna;
            }
            else if (i + j == n - 1) {
                arr2[i][j] = sumaSporedna;
            }
            else {
                arr2[i][j] = 0;
            }
        }
    }

    if (n % 2 != 0) {
        int sredina = n / 2;
        arr2[sredina][sredina] = sumaGlavna + sumaSporedna;
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cout << arr2[i][j] << " ";
        }
        cout << endl;
    }

    return 0;
}
