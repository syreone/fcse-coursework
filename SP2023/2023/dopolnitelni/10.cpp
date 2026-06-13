#include <iostream>
#include <cmath>
#include <cstring>
using namespace std;

int spoj(int i, int j){
    return 10*i+j;
}
int kolkuCifren (int n){
    int brojac=0;
    if(n==0)return 1;
    while(n){
        n/=10;
        brojac++;
    }
    return brojac;
}

int main(){
    int n, m;
    cin>>n>>m;
    int br;
    int matrica[n][m];

    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            cin>>matrica[i][j];
        }
    }
    for (int j = 0; j < m; j++) {
        br = 0;
        for (int i = 0; i < n; i++) {
            if((i*(pow(10, kolkuCifren(j)))+j)==matrica[i][j])
                br++;
        }
        cout << br << endl;
    }
}