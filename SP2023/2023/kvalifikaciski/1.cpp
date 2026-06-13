// ispechati gi cite 4 cifreni broevi delivi so 127 i shto se parni vo opagjacki redosled

#include <iostream>
using namespace std;

int main(){
    for (int i=9998; i>=1000; i--){
        if ((i%127 == 0)&&(i%2==0)){
            cout<<i<<endl;
        }
    }
}