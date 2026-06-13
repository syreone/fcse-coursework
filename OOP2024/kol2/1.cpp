#include <iostream>
#include <cstring>
#include <fstream>

using namespace std;

class NoProductFoundException : public exception {
private:
    string category;
public:
    NoProductFoundException(const string &category) {
        this->category = category;
    }
};

void wtf() {
    ofstream fout("input.txt");
    string line;
    while (getline(std::cin, line)) {
        if (line == "----") {
            break;
        }
        fout << line << endl;
    }
}

void rff(string path) {
    ifstream fin(path);
    string line;
    while (getline(fin, line)) {
        cout << line << endl;
    }
}

class Product {
private:
    string name;
    string category;
    int price;
    int number;
public:
    Product() {
        name = "";
        category = "";
        price = 0;
        number = 0;
    }

    Product(string name, string category, int price, int number) {
        this->name = name;
        this->category = category;
        this->price = price;
        this->number = number;
    }

    Product (const Product &c) {
        this->name = c.name;
        this->category = c.category;
        this->price = c.price;
        this->number = c.number;
    }

    friend ostream &operator<<(ostream &output, const Product &product) {
        output << product.name << " (" << product.category << ") " << product.number << " x " << product.price << " = " << product.totalPrice() <<endl;
        return output;
    }

    int totalPrice() const {
        return price * number;
    }

    string getCategory() const {
        return category;
    }

};



class Store {
private:
    Product *array;
    int n;
public:
    Store () {
        array = nullptr;
        n = 0;
    }

    Store(const Product* array, int n) {
        this->array = new Product[n];
        for (int i=0; i<n; i++) {
            this->array[i] = array[i];
        }
        this->n = n;
    }

    Store(const Store &s) {
        this->n = s.n;
        this->array = new Product[n];
        for (int i=0; i<n; i++) {
            array[i] = s.array[i];
        }
    }

    Store& operator=(const Store &s) {
        if (this!= &s) {
            delete[] array;
            this->n = s.n;
            this->array = new Product[n];
            for (int i=0; i<n; i++) {
                array[i] = s.array[i];
            }
        }
        return *this;
    }

    ~Store() {
        delete[] array;
    }

    Store& operator+=(const Product &p) {
        Product *temp = new Product[n+1];
        for (int i=0; i<n; i++) {
            temp[i] = array[i];
        }
        temp[n++] = p;
        delete[] array;
        array = temp;
        return *this;
    }

    friend ostream& operator<<(ostream &output, const Store &s) {
        for (int i = 0; i < s.n; i++) {
            output << s.array[i];
        }
        return output;
    }

    Store fromCategory(const string &category) {
        Store result;
        for (int i=0; i<n; i++) {
            if (array[i].getCategory() == category) {
                result += array[i];
            }
        }
        if (result.n == 0) {
            throw NoProductFoundException(category);
        }
        return result;
    }
};

int main() {
    wtf();

    Store s;


    ifstream fin("input.txt");
    string name, catInput;
    int price, number;

    while (getline(fin, name) && getline(fin, catInput) && fin >> price >> number) {
        fin.ignore();  
        Product p(name, catInput, price, number);
        s += p;
    }
    fin.close();

    // DO NOT MODIFY THE CODE BETWEEN THIS AND THE NEXT COMMENT

    string category;
    cin >> category;

    // DO NOT MODIFY THE CODE BETWEEN THIS AND THE PREVIOUS COMMENT


    ofstream fout1("output1.txt");
    fout1 << s;
    fout1.close();


    try {
        Store filtered = s.fromCategory(category);
        ofstream fout2("output2.txt");
        fout2 << filtered;
        fout2.close();
    } catch (NoProductFoundException &e) {
        cout << "No products from category " << category << " were found in the store" << endl;
        ofstream fout2("output2.txt");
        fout2.close();
    }

    // DO NOT MODIFY THE CODE BELOW

    cout << "All products:" << endl;
    rff("output1.txt");
    cout << "Products from category " << category << ": " << endl;
    rff("output2.txt");

    return 0;
}