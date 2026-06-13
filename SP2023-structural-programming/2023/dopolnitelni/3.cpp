#include <iostream>
#include <string.h>
#include <cctype>
using namespace std;

int main() {
    int n;
    cin >> n;

    char str[51];

    for (int i = 0; i < n; i++) {
        cin >> str;

        int len = strlen(str);
        int brojac = 0;

        for (int j = 0; j < len - 2; j++) {
            if (str[j] == 'A' && str[j + 1] == '1' && str[j + 2] == 'c') {
                brojac++;
            }
        }

        if (brojac >= 2) {
            for (int k = 0; k < len; k++) {
                cout << (char)tolower(str[k]);
            }
            cout << endl;
        }
    }

    return 0;
}
