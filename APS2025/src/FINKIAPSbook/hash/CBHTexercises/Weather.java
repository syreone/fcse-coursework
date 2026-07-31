package FINKIAPSbook.hash.CBHTexercises;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

class Town {
    String town;
    String from;
    String to;
    double degrees;

    Town(String town, String from, String to, double degrees) {
        this.town = town;
        this.from = from;
        this.to = to;
        this.degrees = degrees;
    }

    @Override
    public String toString() {
        return from + " - " + to + " " + degrees;
    }
}
public class Weather {
    public static double hasSameIntervals(ArrayList<Town> towns, String from, String to, double degrees) {
        double average = 0;
        for (Town town : towns) {
            if (town.from.equals(from) && town.to.equals(to)) {
                average = (town.degrees + degrees) / 2;
                return average;
            }
        }
        return degrees;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, ArrayList<Town>> towns = new HashMap<String, ArrayList<Town>>();

        int n = Integer.parseInt(br.readLine());
        for (int i=0; i<n; i++) {
            String line = br.readLine();
            String[] tokens = line.split(" ");
            String town = tokens[0];
            String from = tokens[1];
            String to = tokens[2];
            double degrees = Double.parseDouble(tokens[3]);

            if (!towns.containsKey(town)) {
                towns.put(town, new ArrayList<>());
                towns.get(town).add(new Town(town, from, to, degrees));
            } else towns.get(town).add(new Town(town, from, to, hasSameIntervals(towns.get(town), from, to, degrees)));
        }
        String query = br.readLine();
        System.out.print(query + ":");
        if (towns.containsKey(query)) {
            System.out.println();
            for (Town town : towns.get(query)) {
                System.out.println(town.toString());
            }
        } else {
            System.out.print("deos not exist");
        }
    }
}
