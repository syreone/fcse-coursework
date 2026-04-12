#include <iostream>
#include <cstring>
using namespace std;

int main(){
    int n, x;
    cin>>n>>x;
    cin.ignore();
    char s[1000];
    for (int i=0; i<n; i++) {
        cin.getline(s, 1000);
        int len = strlen(s);
        for (int j = 0; j < len; j++) {
            if (s[j] >= 'a' && s[j] <= 'z') {
                s[j] = ((s[j] - 'a' + x) % 26) + 'a';
            } else if (s[j] >= 'A' && s[j] <= 'Z') {
                s[j] = ((s[j] - 'A' + x) % 26) + 'A';
            }
        }
        cout<<s<<endl;
    }
    return 0;
}