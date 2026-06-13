#include <iostream>
using namespace std;

int sum_pos(int *niza, int ind, int n) {
    int suma = 0;
    if (ind>n) {
        return 0;
    }
    for (int i=ind; i<n; i++) {
        suma+=*(niza+i);
    }
    return suma;
}

int main() {
    int n;
    cin>>n;

    int niza[1001];
    for (int i=0; i<n; i++){
        cin>>niza[i];
    }
    int ind;
    cin>>ind;
    int suma = 0;
    suma = sum_pos(niza, ind, n);
    cout<<suma<<endl;
}