package exercises.graphs.exercise1;


import java.io.*;
import java.util.*;

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "<" + name + ", " + age + ">";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person p = (Person) o;
        return age == p.age && Objects.equals(name, p.name);
    }

    @Override
    public int hashCode() {
        return age * (int) name.charAt(0);
    }
}

class Project {
    int time;
    int rate;

    Project(int time, int rate) {
        this.time = time;
        this.rate = rate;
    }

    int getTotalSalary() {
        return time * rate;
    }

    @Override
    public String toString() {
        return "<" + time + ", " + rate + ">";
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Person, Project> table = new HashMap<>();
        List<Person> insertionOrder = new ArrayList<>();
        int n = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < n; i++) {
            String[] tokens = br.readLine().split(" ");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);
            int time = Integer.parseInt(tokens[2]);
            int rate = Integer.parseInt(tokens[3]);

            Person p = new Person(name, age);
            Project v = new Project(time, rate);

            if (!table.containsKey(p)) {
                insertionOrder.add(0, p);
                table.put(p, v);
            } else {
                Project existing = table.get(p);
                if (v.getTotalSalary() > existing.getTotalSalary()) {
                    table.put(p, v);
                }
            }
        }

        System.out.println(printBuckets(table, insertionOrder, 10));
    }

    private static String printBuckets(HashMap<Person, Project> table, List<Person> insertionOrder, int m) {
        List<List<Person>> buckets = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            buckets.add(new ArrayList<>());
        }

       for (Person p : insertionOrder) {
            int b = Math.abs(p.hashCode()) % m;
            buckets.get(b).add(p);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            sb.append(i).append(":");
            for (Person p : buckets.get(i)) {
                Project v = table.get(p);
                sb.append("<").append(p).append(",").append(v).append("> ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}