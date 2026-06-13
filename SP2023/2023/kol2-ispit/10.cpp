#include <iostream>
#include <cstring>
using namespace std;

int main(){
    char z1, z2;
    cin>>z1>>z2;
    char str[81];
    

    while(cin.getline(str, 81)) {
        if (strcmp(str, "#") == 0) {
            break;
        }

        int len = strlen(str);
        int pocetok = -1, kraj = -1;

        for (int i=0; i<len; i++) {
            if (str[i] == z1) {
                pocetok = i;
                break;
            }
        }

        for (int i=0; i<len; i++) {
            if (str[i] == z2) {
                kraj = i;
                break;
            }
        }

        if (pocetok != -1 && kraj != -1 && kraj>pocetok+1){
            for (int i = pocetok + 1; i<kraj; i++){
                cout<<str[i];
            }
            cout<<endl;
        }
    }
    return 0;
}