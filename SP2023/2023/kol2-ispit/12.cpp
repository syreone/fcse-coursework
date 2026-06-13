#include <iostream>
#include <cstring>
#include <cmath>
using namespace std;

int BrojPozitivni(int niza[], int n) {
    if (n == 0) {
        return 0;
    }
    if (niza[n-1] > 0) {
        return 1 + BrojPozitivni(niza,  n-1);
    }else{
        return BrojPozitivni(niza, n-1);
    }
}

int main () {
    int n;
    cin>>n;
    int niza[n];

    for (int i=0; i<n; i++) {
        cin>>niza[i];
    }

    int count = BrojPozitivni(niza, n);
    cout<<count<<endl;

    return 0;
}