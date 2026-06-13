#include <iostream>
#include <cstring>

using namespace std;

class Museum {
protected:
    string name;
    string city;
    float base_price;
    int working_hours;
public:
    Museum() {
        name = "";
        city = "";
        base_price = 0.0;
        working_hours = 0;
    }

    Museum(string name, string city, float base_price, int working_hours) {
        this->name = name;
        this->city = city;
        this->base_price = base_price;
        this->working_hours = working_hours;
    }

    Museum(const Museum &c) {
        this->name = c.name;
        this->city = c.city;
        this->base_price = c.base_price;
        this->working_hours = c.working_hours;
    }

    virtual void printDetails() = 0;
    virtual double calculateTicketCost() = 0;
    virtual ~Museum() {}

    int getWorkingHours() const {
        return working_hours;
    }
};

class ScientificMuseum : public Museum {
protected:
    bool interactive_shows;
    int multimedia_pres;
public:
    ScientificMuseum() {
        interactive_shows = false;
        multimedia_pres = 0;
    }

    ScientificMuseum(string name, string city, float base_price, int working_hours, bool interactive_shows, int multimedia_pres)
        : Museum(name, city, base_price, working_hours) {
        this->interactive_shows = interactive_shows;
        this->multimedia_pres = multimedia_pres;
    }

    ScientificMuseum(const ScientificMuseum &c) {
        this->interactive_shows = c.interactive_shows;
        this->multimedia_pres = c.multimedia_pres;
    }

    double calculateTicketCost() override {
        double cost = base_price;
        if (interactive_shows) {
            cost *= 1.12;
        }
        cost += multimedia_pres * 6;
        return cost;
    }

    void printDetails() override {
        cout << name << " - (Scientific) " << city << " " << multimedia_pres << " " << calculateTicketCost() << endl;
    }
};

class ArtMuseum : public Museum {
protected:
    bool amateur_shows;
    int original_artwork;
public:
    ArtMuseum() {
        amateur_shows = false;
        original_artwork = 0;
    }

    ArtMuseum(string name, string city, float base_price, int working_hours, bool amateur_shows, int original_artwork)
        : Museum(name, city, base_price, working_hours) {
        this->amateur_shows = amateur_shows;
        this->original_artwork = original_artwork;
    }

    ArtMuseum(const ArtMuseum &c) {
        this->amateur_shows = c.amateur_shows;
        this->original_artwork = c.original_artwork;
    }

    double calculateTicketCost() override {
        double cost = base_price;
        if (amateur_shows) {
            cost *= 0.82;
        }
        cost += original_artwork * 3;
        return cost;
    }

    void printDetails() override {
        cout << name << " - (Art) " << city << " " << amateur_shows << " " << calculateTicketCost() << endl;
    }
};

int findCheapestScientificMuseum(Museum **m, int n) {
    int index = -1;
    double minCost = 1e9;
    int maxWorkingHours = -1;

    for (int i = 0; i < n; i++) {
        ScientificMuseum *sm = dynamic_cast<ScientificMuseum *>(m[i]);
        if (sm) {
            double cost = sm->calculateTicketCost();
            int hours = sm->getWorkingHours();
            if (cost < minCost || (cost == minCost && hours > maxWorkingHours)) {
                minCost = cost;
                maxWorkingHours = hours;
                index = i;
            }
        }
    }

    return index;
}

int main() {
    int n, testCase, type;
    cin >> testCase >> n;
    cin.ignore();

    Museum **m = new Museum *[n];

    for (int i = 0; i < n; ++i) {
        string name;
        string city;
        float base_price;
        int working_hours;

        cin >> type;
        cin.ignore();
        getline(cin, name);
        getline(cin, city);
        cin >> base_price;
        cin.ignore();
        cin >> working_hours;
        cin.ignore();

        if (type == 1) {
            bool interactive_shows;
            int multimedia_pres;

            cin >> interactive_shows >> multimedia_pres;
            cin.ignore();

            m[i] = new ScientificMuseum(name, city, base_price, working_hours, interactive_shows, multimedia_pres);
        } else {
            bool amateur_shows;
            int original_artwork;

            cin >> amateur_shows >> original_artwork;
            cin.ignore();

            m[i] = new ArtMuseum(name, city, base_price, working_hours, amateur_shows, original_artwork);
        }
    }

    if (testCase == 1) {
        cout << "Abstract and child classes OK" << endl;
    } else if (testCase == 2) {
        for (int i = 0; i < n; i++) {
            cout << m[i]->calculateTicketCost() << endl;
        }
        cout << "calculateTicketCost method OK" << endl;
    } else if (testCase == 3) {
        for (int i = 0; i < n; i++) {
            m[i]->printDetails();
        }
        cout << "printDetail method OK" << endl;
    } else if (testCase == 4) {
        int cheapest_sci_museum_index = findCheapestScientificMuseum(m, n);
        if (cheapest_sci_museum_index >= 0) {
            m[cheapest_sci_museum_index]->printDetails();
        } else {
            cout << "Scientific Museum not found in the array!" << endl;
        }
        cout << "findCheapestOnlineOrder method OK" << endl;
    }

    for (int i = 0; i < n; ++i) {
        delete m[i];
    }
    delete[] m;

    return 0;
}
