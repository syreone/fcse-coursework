#include <iostream>
#include <cstring>
using namespace std;

int main() {
    int n;
    char str[81];
    char najdolga[81];

    while (cin >> n) {
        int maxLen = 0; 
        for (int i = 0; i < n; i++) {
            cin >> str;

            int len = strlen(str);
            bool palindrom = true;

            for (int j = 0; j < len / 2; j++) {
                if (str[j] != str[len - 1 - j]) {
                    palindrom = false;
                    break;
                }
            }
            bool specijalen = false;
            for (int j = 0; j < len; j++) {
                if (!((str[j] >= 'a' && str[j] <= 'z') ||
                      (str[j] >= 'A' && str[j] <= 'Z') ||
                      (str[j] >= '0' && str[j] <= '9'))) {
                    specijalen = true;
                    break;
                      }
            }
            if (specijalen && palindrom) {
                if (len > maxLen) {
                    maxLen = len;
                    strcpy(najdolga, str);
                }
            }
        }
        if (maxLen > 0) {
            cout << najdolga << endl;
        } else {
            cout << "Nema!" << endl;
        }
    }
    return 0;
}
