#include <iostream>
#include <cstring>

using namespace std;

class Library {
protected:
    string name;
    string city;
    double base_price;
    bool weekend_working;
public:
    Library() {
        name = "";
        city = "";
        base_price = 0.0;
        weekend_working = false;
    }

    Library (string name, string city, double base_price, bool weekend_working) {
        this->name=name;
        this->city=city;
        this->base_price=base_price;
        this->weekend_working=weekend_working;
    }

    Library (const Library &c) {
        this->name=c.name;
        this->city=c.city;
        this->base_price=c.base_price;
        this->weekend_working=c.weekend_working;
    }

    bool isWeekendWorking() const {
        return weekend_working;
    }

    virtual void printDetail() = 0;
    virtual double calculateMembershipCardCost() = 0;

    virtual ~Library() {
    }

};

class AcademicLibrary : public Library {
protected:
    bool open_cooperation;
    int specialized_articles;

public:
    AcademicLibrary() {
        open_cooperation = false;
        specialized_articles = 0;
    }
    AcademicLibrary(string name, string city, double base_price, bool weekend_working, bool open_cooperation, int specialized_articles)
    : Library(name, city, base_price, weekend_working){
        this->specialized_articles = specialized_articles;
        this->open_cooperation = open_cooperation;
    }

    AcademicLibrary(const AcademicLibrary &c) {
        this->specialized_articles = c.specialized_articles;
        this->open_cooperation = c.open_cooperation;
    }

    double calculateMembershipCardCost() override {
        double cost = base_price;
        if (open_cooperation)
            cost *= 1.24;
        cost += specialized_articles * 6;
        return cost;
    }

    void printDetail() override {
        cout<<name<<" - (Academic) "<<city<<" "<<specialized_articles<<" "<<calculateMembershipCardCost()<<endl;
    }

};

class NationalLibrary : public Library{
protected:
    bool cultural_program;
    int national_articles;

public:
    NationalLibrary() {
        cultural_program = false;
        national_articles = 0;
    }
    NationalLibrary(string name, string city, double base_price, bool weekend_working, bool cultural_program, int national_articles)
    : Library(name, city, base_price, weekend_working){
        this->cultural_program = cultural_program;
        this->national_articles = national_articles;
    }
    NationalLibrary(const NationalLibrary &c) {
        this->cultural_program = c.cultural_program;
        this->national_articles = c.national_articles;
    }
    double calculateMembershipCardCost() override {
        double cost = base_price;
        if (cultural_program)
            cost *= 0.93;
        cost += national_articles * 15;
        return cost;
    }

    void printDetail() override {
        cout<<name<<" - (National) "<<city<<" "<<national_articles<<" "<<calculateMembershipCardCost()<<endl;
    }

};

int findMostExpensiveNationalLibrary(Library **l, int n) {
    int most_expensive_nat_lib_index = -1;
    double max_cost = -1.0;
    for (int i = 0; i < n; i++) {
        NationalLibrary* nat = dynamic_cast<NationalLibrary*>(l[i]);
        if (nat) {
            double cost = nat->calculateMembershipCardCost();
            if (cost > max_cost || (cost == max_cost && nat->isWeekendWorking())) {
                max_cost = cost;
                most_expensive_nat_lib_index = i;
            }
        }
    }
    return most_expensive_nat_lib_index;
}



int main() {
    int n, testCase, type;
    cin >> testCase >> n;
    cin.ignore();

    Library** m = new Library*[n];

    for (int i = 0; i < n; ++i) {
        string name;
        string city;
        float base_price;
        bool weekend_working;

        cin >> type;
        cin.ignore();
        getline(cin, name);
        getline(cin, city);
        cin >> base_price;
        cin.ignore();
        cin >> weekend_working;
        cin.ignore();

        if (type == 1) {
            bool open_cooperation;
            int specialized_articles;

            cin >> open_cooperation >> specialized_articles;
            cin.ignore();

            m[i] = new AcademicLibrary(name, city, base_price, weekend_working, open_cooperation, specialized_articles);
        } else {
            bool cultural_program;
            int national_articles;

            cin >> cultural_program >> national_articles;
            cin.ignore();

            m[i] = new NationalLibrary(name, city, base_price, weekend_working, cultural_program, national_articles);
        }
    }

    if(testCase == 1){
        cout << "Abstract and child classes OK" << endl;
    }
    else if(testCase == 2){
        for(int i = 0; i < n; i++){
            cout << m[i]->calculateMembershipCardCost() << endl;
        }
        cout << "calculateMembershipCardCost method OK" << endl;
    }
    else if(testCase == 3){
        for(int i = 0; i < n; i++){
            m[i]->printDetail();
        }
        cout << "printDetail method OK" << endl;
    }
    else if(testCase == 4){
        int most_expensive_nat_lib_index = findMostExpensiveNationalLibrary(m, n);
        if(most_expensive_nat_lib_index>=0){
            m[most_expensive_nat_lib_index]->printDetail();
        }else{
            cout << "National Library not found in the array!"<<endl;
        }
        cout << "findMostExpensiveNationalLibrary method OK" << endl;
    }


    for (int i = 0; i < n; ++i) {
        delete m[i];
    }

    delete[] m;

    return 0;
}
