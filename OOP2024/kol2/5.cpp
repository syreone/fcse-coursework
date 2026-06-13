#include <iostream>
#include <cstring>
using namespace std;

class Vozac{
protected:
    char ime[101];
    int vozrast;
    int trki;
    bool vet;
public:
    Vozac(){
        ime[0] = '\0';
        vozrast = 0;
        trki = 0;
        vet = false;
    }

    Vozac(const char* ime, int vozrast,int trki, bool vet){
        strcpy(this->ime, ime);
        this->vozrast=vozrast;
        this->trki=trki;
        this->vet=vet;
    }

    Vozac(const Vozac &v){
        strcpy(this->ime, v.ime);
        this->vozrast=v.vozrast;
        this->trki=v.trki;
        this->vet=v.vet;
    }

    friend ostream& operator<<(ostream& output, const Vozac& v){
        output << v.ime << endl;
        output << v.vozrast << endl;
        output << v.trki << endl;
        if(v.vet){
            output << "VETERAN" <<endl;
        }
        return output;
    }


    virtual float danok() const = 0;
    virtual float zarabotka() const = 0;
    virtual ~Vozac(){}


};

class Avtomobilist : public Vozac {
protected:
    float cena_avto;
public:
    Avtomobilist() {
        cena_avto = 0.0;
    }

    Avtomobilist(const char *ime, int vozrast, int trki, bool vet, float cena_avto) : Vozac(ime, vozrast, trki, vet) {
        this->cena_avto = cena_avto;
    }

    Avtomobilist(const Avtomobilist &a) : Vozac(a) {
        this->cena_avto = a.cena_avto;
    }

    float zarabotka() const override {
        float zarabotkaPoTrka;
        return zarabotkaPoTrka = cena_avto / 5;
    }

    float danok() const override {
        if (trki > 10) {
            return zarabotka() * 0.15;

        }
        return zarabotka() * 0.10;
    }

};

class Motociklist : public Vozac{
protected:
    int mokjnost;
public:
    Motociklist(){
        mokjnost = 0;
    }
    Motociklist(const char* ime, int vozrast, int trki, bool vet,  int mokjnost) : Vozac (ime, vozrast, trki, vet){
        this->mokjnost = mokjnost;
    }
    Motociklist(const Motociklist& m) : Vozac(m){
        this->mokjnost=m.mokjnost;
    }

    float zarabotka() const override {
        float zarabotkaPoTrka;
        return zarabotkaPoTrka = mokjnost*20;
    }

    float danok() const override {
        if (vet) {
            return zarabotka() * 0.25;
        }
        return zarabotka() * 0.20;
    }

};

bool operator==(const Vozac& v1, const Vozac& v2) {
    return v1.zarabotka() == v2.zarabotka();
}

int soIstaZarabotuvachka(Vozac** v, int n, Vozac* x) {
    int count = 0;
    for (int i = 0; i < n; ++i) {
        if (*v[i] == *x) {
            ++count;
        }
    }
    return count;
}

int main() {
    int n, x;
    cin >> n >> x;
    Vozac **v = new Vozac*[n];
    char ime[100];
    int vozrast;
    int trki;
    bool vet;
    for(int i = 0; i < n; ++i) {
        cin >> ime >> vozrast >> trki >> vet;
        if(i < x) {
            float cena_avto;
            cin >> cena_avto;
            v[i] = new Avtomobilist(ime, vozrast, trki, vet, cena_avto);
        } else {
            int mokjnost;
            cin >> mokjnost;
            v[i] = new Motociklist(ime, vozrast, trki, vet, mokjnost);
        }
    }
    cout << "=== DANOK ===" << endl;
    for(int i = 0; i < n; ++i) {
        cout << *v[i];
        cout << v[i]->danok() << endl;
    }
    cin >> ime >> vozrast >> trki >> vet;
    int mokjnost;
    cin >> mokjnost;
    Vozac *vx = new Motociklist(ime, vozrast, trki, vet, mokjnost);
    cout << "=== VOZAC X ===" << endl;
    cout << *vx;
    cout << "=== SO ISTA ZARABOTUVACKA KAKO VOZAC X ===" << endl;
    cout << soIstaZarabotuvachka(v, n, vx);
    for(int i = 0; i < n; ++i) {
        delete v[i];
    }
    delete [] v;
    delete vx;
    return 0;
}