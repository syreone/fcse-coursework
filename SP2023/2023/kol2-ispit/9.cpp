#include <iostream>
#include <cstring>
#include <cmath>

using namespace std;

double pecati(int arr[], int n, int i){
    if(i==n-1)
        return arr[i];
    return arr[i]+(1/pecati(arr, n, i+1));
}

int main(){
    int n;
    cin>>n;
    int arr[101];
    for (int i=0; i<n; i++){
        cin>>arr[i];
    }
    double suma = pecati(arr, n, 0);
    cout<<suma;
}