#include <iostream>
#include <cstring>
using namespace std;

class Book {
protected:
    char isbn[21];
    char title[51];
    char author[31];
    float price;
public:
    Book() {
        isbn[0] = '\0';
        title[0] = '\0';
        author[0] = '\0';
        price = 0.0;
    }

    Book(const char* isbn, const char* title, const char* author, float price) {
        strcpy(this->isbn, isbn);
        strcpy(this->title, title);
        strcpy(this->author, author);
        this->price = price;
    }

    virtual ~Book() {}

    virtual double bookPrice() const = 0;

    void setISBN(const char* isbn) {
        strcpy(this->isbn, isbn);
    }

    Book& operator=(const Book& other) {
        if (this != &other) {
            strcpy(this->isbn, other.isbn);
            strcpy(this->title, other.title);
            strcpy(this->author, other.author);
            this->price = other.price;
        }
        return *this;
    }

    friend ostream& operator<<(ostream& out, const Book& b);
    friend bool operator>(const Book& b1, const Book& b2);
};

class OnlineBook : public Book {
protected:
    int MB;
    char* url;
public:
    OnlineBook(const char* isbn, const char* title, const char* author, float price, const char* url, int MB)
            : Book(isbn, title, author, price) {
        this->url = new char[strlen(url) + 1];
        strcpy(this->url, url);
        this->MB = MB;
    }

    OnlineBook(const OnlineBook& ob) : Book(ob) {
        this->MB = ob.MB;
        this->url = new char[strlen(ob.url) + 1];
        strcpy(this->url, ob.url);
    }

    OnlineBook& operator=(const OnlineBook& ob) {
        if (this != &ob) {
            Book::operator=(ob);
            delete[] url;
            this->url = new char[strlen(ob.url) + 1];
            strcpy(this->url, ob.url);
            this->MB = ob.MB;
        }
        return *this;
    }

    ~OnlineBook() {
        delete[] url;
    }

    double bookPrice() const override {
        return MB > 20 ? price * 1.2 : price;
    }
};

class PrintBook : public Book {
protected:
    float weight;
    bool inStock;
public:
    PrintBook(const char* isbn, const char* title, const char* author, float price, float weight, bool inStock)
            : Book(isbn, title, author, price) {
        this->weight = weight;
        this->inStock = inStock;
    }

    double bookPrice() const override {
        return weight > 0.7 ? price * 1.15 : price;
    }
};

ostream& operator<<(ostream& out, const Book& b) {
    out << b.isbn << ": " <<  b.title << ", " <<  b.author << " " <<  b.bookPrice()  << endl;
    return out;
}

bool operator>(const Book& b1, const Book& b2) {
    return b1.bookPrice() > b2.bookPrice();
}

void mostExpensiveBook(Book** books, int n) {
    int onlineCount = 0, printCount = 0;
    Book* mostExpensive = books[0];

    for (int i = 0; i < n; i++) {
        if (dynamic_cast<OnlineBook*>(books[i])) {
            onlineCount++;
        } else if (dynamic_cast<PrintBook*>(books[i])) {
            printCount++;
        }

        if (books[i]->bookPrice() > mostExpensive->bookPrice()) {
            mostExpensive = books[i];
        }
    }

    cout << "FINKI-Education" << endl;
    cout << "Total number of online books: " << onlineCount << endl;
    cout << "Total number of print books: " << printCount << endl;
    cout << "The most expensive book is:\n" << *mostExpensive;
}

int main(){

    char isbn[20], title[50], author[30], url[100];
    int size, tip;
    float price, weight;
    bool inStock;
    Book  **books;
    int n;

    int testCase;
    cin >> testCase;

    if (testCase == 1){
        cout << "====== Testing OnlineBook class ======" << endl;
        cin >> n;
        books = new Book *[n];

        for (int i = 0; i < n; i++){
            cin >> isbn;
            cin.get();
            cin.getline(title, 50);
            cin.getline(author, 30);
            cin >> price;
            cin >> url;
            cin >> size;
            cout << "CONSTRUCTOR" << endl;
            books[i] = new OnlineBook(isbn, title, author, price, url, size);
            cout << "OPERATOR <<" << endl;
            cout << *books[i];
        }
        cout << "OPERATOR >" << endl;
        cout << "Rezultat od sporedbata e: " << endl;
        if (*books[0] > *books[1])
            cout << *books[0];
        else
            cout << *books[1];
    }
    if (testCase == 2){
        cout << "====== Testing OnlineBook CONSTRUCTORS ======" << endl;
        cin >> isbn;
        cin.get();
        cin.getline(title, 50);
        cin.getline(author, 30);
        cin >> price;
        cin >> url;
        cin >> size;
        cout << "CONSTRUCTOR" << endl;
        OnlineBook ob1(isbn, title, author, price, url, size);
        cout << ob1 << endl;
        cout << "COPY CONSTRUCTOR" << endl;
        OnlineBook ob2(ob1);
        cin >> isbn;
        ob2.setISBN(isbn);
        cout << ob1 << endl;
        cout << ob2 << endl;
        cout << "OPERATOR =" << endl;
        ob1 = ob2;
        cin >> isbn;
        ob2.setISBN(isbn);
        cout << ob1 << endl;
        cout << ob2 << endl;
    }
    if (testCase == 3){
        cout << "====== Testing PrintBook class ======" << endl;
        cin >> n;
        books = new Book *[n];

        for (int i = 0; i < n; i++){
            cin >> isbn;
            cin.get();
            cin.getline(title, 50);
            cin.getline(author, 30);
            cin >> price;
            cin >> weight;
            cin >> inStock;
            cout << "CONSTRUCTOR" << endl;
            books[i] = new PrintBook(isbn, title, author, price, weight, inStock);
            cout << "OPERATOR <<" << endl;
            cout << *books[i];
        }
        cout << "OPERATOR >" << endl;
        cout << "Rezultat od sporedbata e: " << endl;
        if (*books[0] > *books[1])
            cout << *books[0];
        else
            cout << *books[1];
    }
    if (testCase == 4){
        cout << "====== Testing method mostExpensiveBook() ======" << endl;
        cin >> n;
        books = new Book *[n];

        for (int i = 0; i<n; i++){

            cin >> tip >> isbn;
            cin.get();
            cin.getline(title, 50);
            cin.getline(author, 30);
            cin >> price;
            if (tip == 1) {

                cin >> url;
                cin >> size;

                books[i] = new OnlineBook(isbn, title, author, price, url, size);

            }
            else {
                cin >> weight;
                cin >> inStock;

                books[i] = new PrintBook(isbn, title, author, price, weight, inStock);
            }
        }

        mostExpensiveBook(books, n);
    }

    for (int i = 0; i<n; i++) delete books[i];
    delete[] books;
    return 0;
}

