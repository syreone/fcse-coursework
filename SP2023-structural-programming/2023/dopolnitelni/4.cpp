#include <iostream>
using namespace std;

int main() {
    int m,n;
    int r,k;
    cin>>m>>n;
    cin>>r>>k;
    int mat[m][n];

    for(int i=0; i<m; i++) {
        for(int j=0; j<n; j++) {
            cin>>mat[i][j];
        }
    }
    int min = mat[0][0];

    for(int i=0; i<m; i++) {
        for(int j=0; j<n; j++) {
            if (mat[i][j]<min) {
                min = mat[i][j];
            }
        }
    }

    for (int i=0; i<m; i++) {
        for(int j=0; j<n; j++) {
            if (i<r && j<k) {
                mat[i][j] = min;
            }
        }
    }
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            cout << mat[i][j] << " ";
        }
        cout << endl;
    }
    return 0;
}