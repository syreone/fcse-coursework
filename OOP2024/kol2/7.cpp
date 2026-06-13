#include <cstring>
#include <iostream>
using namespace std;

enum Size {SMALL = 0, LARGE = 1, FAMILY = 2};

class Pizza {
protected:
    char name[21];
    char ingridients[101];
    float basePrice;
public:
    Pizza() {
        name[0] = '\0';
        ingridients[0] = '\0';
        basePrice = 0.0;
    }
    Pizza(const char* name, const char* ingridients, float basePrice) {
        strcpy(this->name, name);
        strcpy(this->ingridients, ingridients);
        this->basePrice = basePrice;
    }

    virtual double price() const = 0;
    virtual ~Pizza() {}
};

class FlatPizza : public Pizza {
protected:
    Size size;
public:
    FlatPizza() : Pizza() {
        size = SMALL;
    }
    FlatPizza(const char* name, const char* ingridients, float inPrice, Size size = SMALL) : Pizza(name, ingridients, inPrice) {
        this->size = size;
    }
    FlatPizza(const FlatPizza &fl) : Pizza(fl) {
        this->size = fl.size;
    }

    double price() const override {
        if (size == SMALL) {
            return basePrice * 1.10;
        }
        else if (size == LARGE) {
            return basePrice * 1.20;
        }
        else {
            return basePrice * 1.30;
        }
    }



    friend ostream& operator<<(ostream& output, const FlatPizza& fl) {
        output << fl.name << ": " << fl.ingridients << ", ";
        if (fl.size == SMALL) {
            output << "small";
        }
        else if (fl.size == LARGE) {
            output << "large";
        }
        else if (fl.size == FAMILY) {
            output << "family";
        }
        output << " - " << fl.price() << endl;
        return output;
    }
};

class FoldedPizza : public Pizza {
protected:
    bool whiteFlour;
public:
    FoldedPizza() : Pizza() {
        whiteFlour = false;
    }
    FoldedPizza(const char* name, const char* ingridients, float inPrice, bool whiteFlour = true) : Pizza(name, ingridients, inPrice) {
        this->whiteFlour = whiteFlour;
    }
    FoldedPizza(const FoldedPizza &fo) : Pizza(fo) {
        this->whiteFlour = fo.whiteFlour;
    }

    double price() const override {
        if (whiteFlour) {
            return basePrice * 1.10;
        }
        return basePrice * 1.30;
    }

    void setWhiteFlour(bool wf) {
        whiteFlour = wf;
    }

    friend ostream& operator<<(ostream& output, const FoldedPizza& fo) {
        output << fo.name << ": " << fo.ingridients << ", ";
        if (fo.whiteFlour) {
            output << "wf";
        }
        else {
            output << "nwf";
        }
        output << " - " << fo.price() << endl;
        return output;
    }
};


bool operator<(const Pizza& p1, const Pizza& p2) {
    return p1.price() < p2.price();
}


void expensivePizza(Pizza** pizzas, int n) {
    if (n == 0) return;

    int maxIndex = 0;
    for (int i = 1; i < n; ++i) {
        if (*pizzas[maxIndex] < *pizzas[i]) {
            maxIndex = i;
        }
    }

    FlatPizza* fp = dynamic_cast<FlatPizza*>(pizzas[maxIndex]);
    if (fp) {
        cout << *fp;
        return;
    }
    FoldedPizza* fdp = dynamic_cast<FoldedPizza*>(pizzas[maxIndex]);
    if (fdp) {
        cout << *fdp;
        return;
    }
}

int main() {
    int test_case;
    char name[20];
    char ingredients[100];
    float inPrice;
    Size size;
    bool whiteFlour;

    cin >> test_case;
    if (test_case == 1) {
        // Test Case FlatPizza - Constructor, operator <<, price
        cin.get();
        cin.getline(name,20);
        cin.getline(ingredients,100);
        cin >> inPrice;
        FlatPizza fp(name, ingredients, inPrice);
        cout << fp;
    } else if (test_case == 2) {
        // Test Case FlatPizza - Constructor, operator <<, price
        cin.get();
        cin.getline(name,20);
        cin.getline(ingredients,100);
        cin >> inPrice;
        int s;
        cin>>s;
        FlatPizza fp(name, ingredients, inPrice, (Size)s);
        cout << fp;

    } else if (test_case == 3) {
        // Test Case FoldedPizza - Constructor, operator <<, price
        cin.get();
        cin.getline(name,20);
        cin.getline(ingredients,100);
        cin >> inPrice;
        FoldedPizza fp(name, ingredients, inPrice);
        cout << fp;
    } else if (test_case == 4) {
        // Test Case FoldedPizza - Constructor, operator <<, price
        cin.get();
        cin.getline(name,20);
        cin.getline(ingredients,100);
        cin >> inPrice;
        FoldedPizza fp(name, ingredients, inPrice);
        fp.setWhiteFlour(false);
        cout << fp;

    } else if (test_case == 5) {
        // Test Cast - operator <, price
        int s;

        cin.get();
        cin.getline(name,20);
        cin.getline(ingredients,100);
        cin >> inPrice;
        cin>>s;
        FlatPizza *fp1 = new FlatPizza(name, ingredients, inPrice, (Size)s);
        cout << *fp1;

        cin.get();
        cin.getline(name,20);
        cin.getline(ingredients,100);
        cin >> inPrice;
        cin>>s;
        FlatPizza *fp2 = new FlatPizza(name, ingredients, inPrice, (Size)s);
        cout << *fp2;

        cin.get();
        cin.getline(name,20);
        cin.getline(ingredients,100);
        cin >> inPrice;
        FoldedPizza *fp3 = new FoldedPizza(name, ingredients, inPrice);
        cout << *fp3;

        cin.get();
        cin.getline(name,20);
        cin.getline(ingredients,100);
        cin >> inPrice;
        FoldedPizza *fp4 = new FoldedPizza(name, ingredients, inPrice);
        fp4->setWhiteFlour(false);
        cout << *fp4;

        cout<<"Lower price: "<<endl;
        if(*fp1<*fp2)
            cout<<fp1->price()<<endl;
        else cout<<fp2->price()<<endl;

        if(*fp1<*fp3)
            cout<<fp1->price()<<endl;
        else cout<<fp3->price()<<endl;

        if(*fp4<*fp2)
            cout<<fp4->price()<<endl;
        else cout<<fp2->price()<<endl;

        if(*fp3<*fp4)
            cout<<fp3->price()<<endl;
        else cout<<fp4->price()<<endl;

    } else if (test_case == 6) {
        // Test Cast - expensivePizza
        int num_p;
        int pizza_type;

        cin >> num_p;
        Pizza **pi = new Pizza *[num_p];
        for (int j = 0; j < num_p; ++j) {

            cin >> pizza_type;
            if (pizza_type == 1) {
                cin.get();
                cin.getline(name,20);

                cin.getline(ingredients,100);
                cin >> inPrice;
                int s;
                cin>>s;
                FlatPizza *fp = new FlatPizza(name, ingredients, inPrice, (Size)s);
                cout << (*fp);
                pi[j] = fp;
            }
            if (pizza_type == 2) {

                cin.get();
                cin.getline(name,20);
                cin.getline(ingredients,100);
                cin >> inPrice;
                FoldedPizza *fp =
                        new FoldedPizza (name, ingredients, inPrice);
                if(j%2)
                    (*fp).setWhiteFlour(false);
                cout << (*fp);
                pi[j] = fp;

            }
        }

        cout << endl;
        cout << "The most expensive pizza:\n";
        expensivePizza(pi,num_p);


    }
    return 0;
}
