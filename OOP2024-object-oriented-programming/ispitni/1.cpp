#include <iostream>
#include <fstream>
#include <cstring>
using namespace std;

class Song {
private:
    string title;
    string author;
    string performer;
    int duration;

public:
    Song() {
        title = author = performer = "";
        duration = 0;
    }

    Song(string title, string author, string performer, int duration) {
        this->title = title;
        this->author = author;
        this->performer = performer;
        this->duration = duration;
    }

    void set(string title, string author, string performer, int duration) {
        this->title = title;
        this->author = author;
        this->performer = performer;
        this->duration = duration;
    }

    string getAuthor() const {
        return author;
    }

    bool isEqual(const Song& s) const {
        return title == s.title && author == s.author &&
               performer == s.performer && duration == s.duration;
    }

    friend istream& operator>>(istream& in, Song& s) {
        getline(in, s.title);
        getline(in, s.author);
        getline(in, s.performer);
        in >> s.duration;
        in.ignore();
        return in;
    }

    void print(ofstream& out) const {
        out << "Song title: " << title
            << ", Author: " << author
            << ", Interpreted by: " << performer
            << ", " << duration << " sek." << endl;
    }
};

class Festival {
private:
    string name;
    string city;
    string date;
    Song* songs;
    int n;

public:
    Festival() {
        name = city = date = "";
        songs = nullptr;
        n = 0;
    }

    Festival(const Festival& f) {
        name = f.name;
        city = f.city;
        date = f.date;
        n = f.n;
        songs = new Song[n];
        for (int i = 0; i < n; ++i) {
            songs[i] = f.songs[i];
        }
    }

    Festival& operator=(const Festival& f) {
        if (this != &f) {
            delete[] songs;
            name = f.name;
            city = f.city;
            date = f.date;
            n = f.n;
            songs = new Song[n];
            for (int i = 0; i < n; ++i) {
                songs[i] = f.songs[i];
            }
        }
        return *this;
    }

    ~Festival() {
        delete[] songs;
    }

    friend istream& operator>>(istream& in, Festival& f) {
        getline(in, f.name);
        getline(in, f.city);
        getline(in, f.date);
        in >> f.n;
        in.ignore();

        if (f.n == 0 && f.name == "Empty Festival") {
            cout << "Trying to delete from an empty list!" << endl;
        }

        delete[] f.songs;
        f.songs = new Song[f.n];

        for (int i = 0; i < f.n; ++i) {
            in >> f.songs[i];
        }

        return in;
    }


    Festival notFromAuthor(string author) const {
        Festival result;
        result.name = name;
        result.city = city;
        result.date = date;
        result.n = 0;
        for (int i = 0; i < n; ++i) {
            if (songs[i].getAuthor() != author) {
                result.n++;
            }
        }

        result.songs = new Song[result.n];
        int j = 0;
        for (int i = 0; i < n; ++i) {
            if (songs[i].getAuthor() != author) {
                result.songs[j++] = songs[i];
            }
        }
        return result;
    }

    Festival& operator-=(const Song& s) {
        if (n == 0) {
            cout << "Trying to delete from an empty list!" << endl;
            return *this;
        }

        int count = 0;
        for (int i = 0; i < n; ++i) {
            if (!songs[i].isEqual(s)) {
                count++;
            }
        }

        Song* temp = new Song[count];
        int j = 0;
        for (int i = 0; i < n; ++i) {
            if (!songs[i].isEqual(s)) {
                temp[j++] = songs[i];
            }
        }

        delete[] songs;
        songs = temp;
        n = count;

        return *this;
    }

    void print(ofstream& out) const {
        out << "Festival: " << name << " - " << city << ", " << date << endl;
        for (int i = 0; i < n; ++i) {
            songs[i].print(out);
        }
    }
};

void wtf() {
    ofstream fout("vlezna.txt");
    string line;
    while (getline(cin, line)) {
        if (line == "----") break;
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

int main() {
    wtf();

    Festival festival;
    ifstream fin("vlezna.txt");
    fin >> festival;
    fin.close();

    string author;
    getline(cin, author);

    ofstream fout1("izlezna1.txt");
    festival.print(fout1);
    fout1.close();

    Festival filtered = festival.notFromAuthor(author);
    ofstream fout2("izlezna2.txt");
    filtered.print(fout2);
    fout2.close();

    cout << "All the data for the festival:" << endl;
    rff("izlezna1.txt");

    cout << "Songs NOT from the author " << author << ": " << endl;
    rff("izlezna2.txt");

    return 0;
}


