package FINKIAPSbook.hash.OBHTadvancedProblems;


import FINKIAPSbook.hash.MapEntry;
import FINKIAPSbook.hash.OBHT;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;

class Driver implements Comparable<Driver> {
    String name;
    String surname;
    Date time;

    public Driver(String name, String surname, Date time) {
        this.name = name;
        this.surname = surname;
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    @Override
    public int compareTo(Driver o) {
        return this.getTime().compareTo(o.getTime());
    }
}

public class TrafficLights {
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        OBHT<String, String> hashtable = new OBHT<String, String>(2*n);

        for (int i =0; i<n; i++) {
            String [] p = br.readLine().split(" ");
            hashtable.insert(p[0], p[1] +" " +p[2]);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        int speed = Integer.parseInt(br.readLine());
        String traffic [] = br.readLine().split(" ");
        LinkedList<Driver> drivers = new LinkedList<Driver>();

        for (int i=0; i<(traffic.length-2); i+=3) {
            String plateDriver = traffic[i];
            int speedDriver = Integer.parseInt(traffic[i+1]);
            String timeDriver = traffic[i+2];

            if (speedDriver>speed) {
                String pom[] = hashtable.getBucket(hashtable.search(plateDriver)).value.split(" ");
                drivers.add(new Driver(pom[0], pom[1], formatter.parse(timeDriver)));
            }
        }

        Collections.sort(drivers);
        ListIterator<Driver> listIterator = drivers.listIterator();

        while (listIterator.hasNext()) {
            System.out.println(listIterator.next().toString());
        }
    }
}
