#include <iostream>
using namespace std;

void premesti(int *a, int n){
    int b[n], indeks = 0;
    for(int i = 0; i < n; i++) {
        if(a[i] >= 0)
            b[indeks++] = a[i];
    }
    for(int i = 0; i < n; i++) {
        if(a[i] < 0)
            b[indeks++] = a[i];
    }
    for(int i = 0; i < n; i++) {
        a[i] = b[i];
    }
}

int main(){
    int n;
    cin>>n;
    int arr[n];

    for(int i=0; i<n; i++){
        cin>>arr[i];
    }
    premesti(arr, n);
    for(int i = 0; i<n; i++)
        cout<<arr[i]<<" ";
}