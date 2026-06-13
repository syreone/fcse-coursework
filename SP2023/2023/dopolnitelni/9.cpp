#include <iostream>
#include <cstring>
#include <cmath>
using namespace std;
int broi(char str[], int indeks, int brojac) {
    if (str[indeks] == '\0')
        return 0;
    if (tolower(str[indeks]) < 'a' || tolower(str[indeks]) > 'z') {
        if (brojac >= 1 && brojac <= 3)
            return 1 + broi(str, indeks + 1, 0);
        else
            return broi(str, indeks + 1, 0);
    }
    return broi(str, indeks + 1, ++brojac);
}

int main(){
    char str[101], maksStr[101];
    int maksBrojac=-1;
    while(cin.getline(str, 101)) {
        int brojac = broi(str, 0, 0);
        if(brojac>maksBrojac)
        {
            maksBrojac=brojac;
            strcpy(maksStr, str);
        }
    }
    cout<<maksBrojac<<": "<<maksStr;
}

