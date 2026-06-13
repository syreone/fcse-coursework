#include <iostream>
using namespace std;

int main() {
    int m;
    cin >> m;

    int prazni = 0;

    for (int i = 0; i < m; i++) {
        int n;
        cin >> n;
        int arr[n];
        for (int j = 0; j < n; j++) {
            cin >> arr[j];
        }

        int arr2[n] = {0};

        if (arr[0] > 0) {
            for (int j = n - 1; j >= arr[0]; j--) {
                arr2[j] = arr[j - arr[0]];
            }
        }
        else if (arr[0] < 0) {
            for (int j = 0; j < n + arr[0]; j++) {
                arr2[j] = arr[j - arr[0]];
            }
        }
        else {
            for (int j = 0; j < n; j++) {
                arr2[j] = arr[j];
            }
        }

        bool isPrazna = true;
        for (int j = 0; j < n; j++) {
            if (arr2[j] != 0) {
                isPrazna = false;
                break;
            }
        }
        if (isPrazna) {
            prazni++;
        }

        for (int j = 0; j < n; j++) {
            cout << arr2[j] << " ";
        }
        cout << endl;
    }

    cout << prazni << endl;
}
