#include <iostream>
using namespace std;

int par(const int a[],const int n) {
    int min = -1;
    
    for (int i=0; i<n; i++) {
        int brojac = 0;
        for(int j=0; j<n; j++) {
            if (a[i] == a[j]){
                brojac++;
            }
        }
        if (brojac%2==0 && brojac>0) {
            if (min == -1 || a[i]<min) {
                min = a[i];
            }
        }
    }
    return min;
}
int main() {
    int n;
    cin>>n;
    int a[n];
    for (int i=0; i<n; i++) {
        cin>>a[i];
    }
    int rezultat = par(a,n);
    if (rezultat!=-1) {
        cout<<"Najmaliot element koj se pojavuva paren broj pati e "<<rezultat<<endl;
    }else {
        cout<<"Nitu eden element ne se pojavuva paren broj pati!"<<endl;
    }
    return 0;
}