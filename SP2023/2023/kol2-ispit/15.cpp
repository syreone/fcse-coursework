#include <iostream>
#include <cstring>
using namespace std;

void swap(int &a, int &b){
    int temp = a;
    a=b;
    b=temp;
}
void sort(int arr[], int size){
    bool swapped = true;
    while(swapped){
        swapped=false;
        for(int i = 0; i<size-1; i++){
            if(arr[i]>arr[i+1])
            {
                swap(arr[i], arr[i+1]);
                swapped=true;
            }
        }
    }
}
int poramnet(int a){
    if(a<9)
        return a;
    if(a%10==9)
        return poramnet(a/10) * 10 + 7;
    return a%10 + poramnet(a/10) * 10;

}
int main() {
    int n=100;
    int arr[n];
    int j =0, br=0;
    while(cin>>arr[j]){
        arr[j]=poramnet(arr[j]);
        j++;
    }
    sort(arr, j);
    if(j<5) {
        for (int i = 0; i < j; i++) {
            cout << arr[i] << " ";
        }
    }
    else
        for (int i = 0; i < 5; i++) {
            cout << arr[i] << " ";
        }

    return 0;
}