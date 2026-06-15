package labExercises.lab4.inLab;


import java.util.*;

class Student {
    String name;
    boolean needDocs;
    boolean needIndex;
    boolean needHighSchool;

    Student(String name, int docs, int index, int highSchool) {
        this.name=name;
        this.needDocs=docs == 1;
        this.needIndex=index == 1;
        this.needHighSchool=highSchool == 1;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        Queue<Student> docsQueue = new LinkedList<>();
        Queue<Student> indexQueue = new LinkedList<>();
        Queue<Student> highSchoolQueue = new LinkedList<>();

        for (int i=0; i<n; i++) {
            String name = sc.nextLine();
            int docs = Integer.parseInt(sc.nextLine());
            int index = Integer.parseInt(sc.nextLine());
            int highSchool = Integer.parseInt(sc.nextLine());

            Student s = new Student(name, docs, index, highSchool);

            if (s.needDocs) {
                docsQueue.add(s);
            } else if (s.needIndex) {
                indexQueue.add(s);
            } else if (s.needHighSchool) {
                highSchoolQueue.add(s);
            }
        }

        while (!docsQueue.isEmpty() || !indexQueue.isEmpty() || !highSchoolQueue.isEmpty()) {
            for (int i=0; i<2 && !docsQueue.isEmpty(); i++) {
                Student s = docsQueue.peek();
                docsQueue.remove();
                s.needDocs = false;
                if (s.needIndex) indexQueue.add(s);
                else if (s.needHighSchool) highSchoolQueue.add(s);
                else System.out.println(s.name);
            }
            for (int i=0; i<3 && !indexQueue.isEmpty(); i++) {
                Student s = indexQueue.peek();
                indexQueue.remove();
                s.needIndex = false;
                if (s.needHighSchool) highSchoolQueue.add(s);
                else System.out.println(s.name);
            }
            for (int i=0; i<1 && !highSchoolQueue.isEmpty(); i++) {
                Student s = highSchoolQueue.peek();
                highSchoolQueue.remove();
                s.needHighSchool = false;
                System.out.println(s.name);
            }
        }
    }
}
