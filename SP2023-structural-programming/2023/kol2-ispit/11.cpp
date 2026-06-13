#include <iostream>
#include <cstring>
using namespace std;

int main() {
    char str[1001];

    while (cin.getline(str, 1001)) {
        if (strcmp(str, "#") == 0) {
            break;
        }

        int brCifri = 0;
        char cifri[101];

        for (int i = 0; str[i] != '\0'; i++) {
            if (isdigit(str[i])) {
                cifri[brCifri++] = str[i];
            }
        }

        for (int i = 0; i < brCifri - 1; i++) {
            for (int j = 0; j < brCifri - i - 1; j++) {
                if (cifri[j] > cifri[j + 1]) {
                    char temp = cifri[j];
                    cifri[j] = cifri[j + 1];
                    cifri[j + 1] = temp;
                }
            }
        }


        cout << brCifri << ":";
        for (int i = 0; i < brCifri; i++) {
            cout << cifri[i];
        }
        cout<<endl;
    }
    return 0;
}