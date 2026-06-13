#include <iostream>
#include <cstring>
using namespace std;

int main() {
    char str[101];
    char bestStr[101];
    int maxLength = 0;
    int bestFirst = -1, bestLast = -1;

    while (cin.getline(str, 101)) {
        if (strcmp(str, "0") == 0) {
            break;
        }

        int len = strlen(str);
        int firstDigit = -1;
        int lastDigit = -1;
        int brojac = 0;

        for (int i = 0; i < strlen(str); i++) {
            if (str[i] >= '0' && str[i] <= '9') {
                brojac++;
                if (firstDigit == -1) {
                    firstDigit = i;
                }
                lastDigit = i;
            }
        }

        if (brojac>=2){
            if (len > maxLength || (len == maxLength)){
                maxLength = len;
                strcpy(bestStr, str);
                bestFirst = firstDigit;
                bestLast = lastDigit;
            }
        }
    }

    if(maxLength>0){
        for (int i =bestFirst; i<=bestLast; i++){
            cout<<bestStr[i];
        }
        cout<<endl;
    }
    return 0;
}