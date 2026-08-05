package FINKIAPSbook.hash.OBHTsimpleProblems;

import FINKIAPSbook.hash.MapEntry;
import FINKIAPSbook.hash.OBHT;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Employee implements Comparable<Employee> {
    String name;
    String surname;
    String dateB;

    public Employee(String name, String surname, String dateB) {
        this.name = name;
        this.surname = surname;
        this.dateB = dateB;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateB() {
        return dateB;
    }

    public void setDateB(String dateB) {
        this.dateB = dateB;
    }

    @Override
    public int compareTo (Employee o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return this.name +  " " + this.surname;
    }
}

public class Birthdays {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        OBHT<String, ArrayList<Employee>> hashtable = new OBHT<String, ArrayList<Employee>>(2 * n);

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            String[] elems = input.split(" ");
            Employee emp = new Employee(elems[0], elems[1], elems[2]);
            String key = elems[2].substring(0, 5);

            if (hashtable.search(key) != -1) {
                MapEntry<String, ArrayList<Employee>> result = hashtable.getBucket(hashtable.search(key));
                ArrayList<Employee> array = result.value;
                array.add(emp);
                hashtable.insert(key, array);
            } else {
                ArrayList<Employee> a = new ArrayList<Employee>();
                a.add(emp);
                hashtable.insert(key, a);
            }
        }
        String dateIn = br.readLine();
        String date = dateIn.substring(0, 5);
        int yearIn = Integer.parseInt(dateIn.substring(0, 5));

        if (hashtable.search(date) != -1) {
            FINKIAPSbook.hash.MapEntry<String, ArrayList<Employee>> result = hashtable.getBucket(hashtable.search(date));
            ArrayList<Employee> niza = result.value;
            Employee[] p = new Employee[niza.size()];
            for (int i = 0; i < p.length; i++)
                p[i] = niza.get(i);
            Arrays.sort(p);
            for (int i = 0; i < p.length; i++) {
                int year = Integer.parseInt(p[i].getDateB().substring(6, 10));
                System.out.println(p[i].toString() + " " + (yearIn - year));
            }
        } else {
            System.out.println("Empty");
        }
    }
}
