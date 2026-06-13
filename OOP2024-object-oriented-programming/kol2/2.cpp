#include <iostream>
#include <cstring>
#include <fstream>

using namespace std;

void wtf() {
    ofstream fout("input.txt");
    string line;
    while (getline(std::cin, line)) {
        if (line == "----"){
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


class Student {
private:
    string index;
    string name;
    string surname;
    int points;
public:
    Student() {
        index = "";
        name = "";
        surname = "";
        points = 0;
    }
    Student(string index, string name, string surname, int points) {
        this->index = index;
        this->name = name;
        this->surname = surname;
        this->points = points;
    }
    Student(const Student &c) {
        this->index = c.index;
        this->name = c.name;
        this->surname = c.surname;
        this->points = c.points;
    }

    friend ostream &operator<<(ostream &output, const Student &student) {
        output << student.index << " " << student.name << " " << student.surname << " " << student.points << " Grade: " << student.grade() << endl;
        return output;
    }

    int grade() const {
        if (points <= 49) {
            return 5;
        } else if (points <= 59) {
            return 6;
        } else if (points <= 69) {
            return 7;
        } else if (points <= 79) {
            return 8;
        } else if (points <= 89) {
            return 9;
        } else {
            return 10;
        }
    }

    string getIndex() const {
        return index;
    }
};


class StudentFailedException : public exception{
private:
    string id;
public:
    StudentFailedException(const string& id) {
        this->id = id;
    }
    string getID() const {
        return id;
    }
};


class Results {
private:
    Student *array;
    int n;
public:
    Results() {
        n = 0;
        array = nullptr;
    }
    Results(int n, const Student* array) {
        this->n = n;
        this->array = new Student[n];
        for (int i=0; i<n; i++) {
            this->array[i] = array[i];
        }
    }

    Results(const Results &r) {
        this->n = r.n;
        this->array = new Student[n];
        for (int i=0; i<n; i++) {
            array[i] = r.array[i];
        }
    }

    ~Results() {
        delete[] array;
    }

    Results& operator+=(const Student &s) {
        if (s.grade() == 5) {
            throw StudentFailedException(s.getIndex());
        }
        Student *temp = new Student[n+1];
        for (int i=0; i<n; i++) {
            temp[i] = array[i];
        }
        temp[n++] = s;
        delete[] array;
        array = temp;
        return *this;
    }

    friend ostream& operator<<(ostream &output, const Results &r) {
        for (int i=0; i<r.n; i++) {
            output << r.array[i];
        }
        return output;
    }

    Results withGrade(int grade) {
        Results filtered;
        for (int i=0; i<n; i++){
            if (array[i].grade() == grade) {
                filtered += array[i];
            }
        }
        return filtered;
    }

    bool empty() const {
        return n == 0;
    }
};

int main() {

    wtf();

    Results results;


    ifstream fin("input.txt");
    while (true) {
        string index, name, surname;
        int points;
        fin >> index;
        if (fin.fail()) break;
        fin >> name >> surname >> points;
        if (fin.fail()) break;

        Student s(index, name, surname, points);
        try {
            results += s;
        } catch (StudentFailedException &e) {
            cout << "Student with id " << e.getID() << " failed the course" << endl;
        }
    }
    fin.close();


    //DO NOT MODIFY THE CODE BETWEEN THIS AND THE NEXT COMMENT
    int grade;
    cin >> grade;
    //DO NOT MODIFY THE CODE BETWEEN THIS AND THE PREVIOUS COMMENT


    ofstream fout1("output1.txt");
    fout1 << results;
    fout1.close();

    Results filtered = results.withGrade(grade);
    ofstream fout2("output2.txt");
    if (filtered.empty()) {
        fout2 << "None" << endl;
    } else {
        fout2 << filtered;
    }
    fout2.close();


    //DO NOT MODIFY THE CODE BELOW

    cout << "All students:" << endl;
    rff("output1.txt");
    cout << "Grade report for grade " << grade << ": " << endl;
    rff("output2.txt");

    return 0;
}
