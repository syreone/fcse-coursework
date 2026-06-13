#include <iostream>
#include <cstring>
#include <cmath>
using namespace std;

int main() {
    int m,n;
    cin>>m>>n;

    int arr[m][n];

    for (int i=0; i<m; i++) {
        for (int j = 0; j<n; j++) {
            cin>>arr[i][j];
        }
    }

    for (int i=0; i<m; i++) {
        int sumaPrv = 0;
        int sumaVtor = 0;
        int sredina = n/2;

        if (n % 2 == 0) {
            for (int j=0; j<sredina; j++) {
                sumaPrv+= arr[i][j];
            }
            for (int j = sredina; j<n; j++) {
                sumaVtor+=arr[i][j];
            }
        } else {
            for (int j=0; j<sredina; j++) {
                sumaPrv+=arr[i][j];
            }
            for (int j=sredina+1; j<n; j++) {
                sumaVtor+=arr[i][j];
            }
        }

        int razlika = abs(sumaPrv - sumaVtor);

        if (n%2 == 0) {
            arr[i][sredina - 1]=razlika;
            arr[i][sredina] = razlika;
        } else {
            arr[i][sredina] = razlika;
        }
    }

    for (int i=0; i<m; i++) {
        for (int j=0; j<n; j++) {
            cout<<arr[i][j]<<" ";
        }
        cout<<endl;
    }
    return 0;
}