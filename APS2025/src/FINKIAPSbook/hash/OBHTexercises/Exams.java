package FINKIAPSbook.hash.OBHTexercises;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

class Exam implements Comparable<Exam> {
    String name;
    Date time;
    String room;
    String date;

    Exam(String name, Date time, String room, String date) {
        this.name = name;
        this.time = time;
        this.room = room;
        this.date = date;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(time) + " " + room + " " + name;
    }

    @Override
    public int compareTo(Exam o) {
        return this.time.compareTo(o.time);
    }
}

public class Exams {
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, ArrayList<Exam>> exams = new HashMap<>();

        int n = Integer.parseInt(br.readLine().trim());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        for (int i=0; i<n; i++){
            String line = br.readLine();
            String[] tokens = line.split(" ", 4);
            String date = tokens[0];
            Date time = sdf.parse(tokens[1]);
            String room = tokens[2];
            String name = tokens[3];
            Exam exam = new Exam(name, time, room, date);
            if (!exams.containsKey(date)) {
                exams.put(date, new ArrayList<>());
                exams.get(date).add(exam);
            } else {
                exams.get(date).add(exam);
            }
        }

        String query = br.readLine();
        Collections.sort(exams.get(query));
        for (Exam exam : exams.get(query)) {
            System.out.println(exam.toString());
        }
    }
}
