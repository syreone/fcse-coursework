package labExercises.lab5.inLab;
import java.util.*;
import java.io.*;

public class Main {
    static class Student {
        String name;
        int dava;
        int zema;
        int index;

        Student(String n, int a, int b, int c) {
            name = n;
            dava = a;
            index = b;
            zema = c;
        }

        Student() {}
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();

        Queue<Student> dava = new LinkedList<>();
        Queue<Student> indeks = new LinkedList<>();
        Queue<Student> zema = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String name = in.nextLine();
            int a = Integer.parseInt(in.nextLine());
            int b = Integer.parseInt(in.nextLine());
            int c = Integer.parseInt(in.nextLine());
            Student s = new Student(name, a, b, c);

            if (a == 1) {
                dava.offer(s);
            } else if (b == 1) {
                indeks.offer(s);
            } else {
                zema.offer(s);
            }
        }

        while (!dava.isEmpty() || !indeks.isEmpty() || !zema.isEmpty()) {
            for (int i = 0; i < 2; i++) {
                if (dava.isEmpty()) break;
                Student st = dava.peek();
                if (st.index == 1) {
                    indeks.offer(dava.poll());
                } else if (st.zema == 1) {
                    zema.offer(dava.poll());
                } else {
                    System.out.println(st.name);
                    dava.poll();
                }
            }

            for (int i = 0; i < 3; i++) {
                if (indeks.isEmpty()) break;
                Student st = indeks.peek();
                if (st.zema == 1) {
                    zema.offer(indeks.poll());
                } else {
                    System.out.println(st.name);
                    indeks.poll();
                }
            }

            if (!zema.isEmpty()) {
                System.out.println(zema.peek().name);
                zema.poll();
            }
        }
    }
}