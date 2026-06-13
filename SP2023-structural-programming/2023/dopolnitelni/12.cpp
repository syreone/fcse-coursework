



#include <iostream>
using namespace std;

int main() {
    int n, m;
    cin>>n>>m;

    int arr[n][m];
    int brojac = 0;
    int redica = 0;

    for (int i=0; i<n; i++) {
        for (int j=0; j<m; j++) {
            cin>>arr[i][j];
        }
    }

    for (int i=0; i<n; i++) {
        brojac = 0;
        for (int j=0; j<m; j++) {
            if ((arr[i][j] % 2 == 0) && ((i+j) % 2 == 0) || (arr[i][j] % 2 != 0) && ((i+j) % 2 != 0)) {
                redica = i;
                brojac++;
            }
        }
        cout<<i<<": "<<brojac<<endl;
    }

    return 0;
}