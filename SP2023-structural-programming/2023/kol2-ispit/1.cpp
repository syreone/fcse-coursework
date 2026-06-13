#include <iostream>
#include <cstring>
using namespace std;


char samoglaska(char a) {
    if ( a == 'a' || a == 'e' ||  a == 'i' ||  a == 'o' || a == 'u') {
        return true;
    }
    return false;
}

int main() {
    char niza[1001];
    int brojac = 0;
    while(cin.getline(niza, 1001)) {
        if (niza[0] == '#') {
            break;
        }
        for (int i=0; i<strlen(niza); i++) {
            if(samoglaska(tolower(niza[i]))&&samoglaska(tolower(niza[i+1]))) {
                brojac++;
                cout<<(char)tolower(niza[i])<<(char)tolower(niza[i+1])<<endl;
            }
        }
    }
    cout<<brojac<<endl;
    return 0;
}