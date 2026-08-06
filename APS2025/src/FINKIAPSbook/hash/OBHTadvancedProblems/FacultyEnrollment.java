package FINKIAPSbook.hash.OBHTadvancedProblems;

import FINKIAPSbook.hash.MapEntry;
import FINKIAPSbook.hash.OBHT;
import java.io.*;

public class FacultyEnrollment {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        OBHT<String, Double> candidates = new OBHT<String, Double>(2*n);

        for (int i=0; i<n; i++) {
            String input = br.readLine();
            String[] p = input.split(" ");
            candidates.insert(p[0], Double.parseDouble(p[1]));
        }

        int m = Integer.parseInt(br.readLine());
        OBHT<String, Double> gradebook = new OBHT<String, Double>(2*m);

        for (int i=0; i<m; i++) {
            String input = br.readLine();
            String[] p = input.split(" ");
            gradebook.insert(p[0], Double.parseDouble(p[1]));
        }

        String PIN = br.readLine();

        if (candidates.search(PIN)!=-1) {
            if  (gradebook.search(PIN)!=-1) {
                if (candidates.getBucket(candidates.search(PIN)).value.equals(gradebook.getBucket(gradebook.search(PIN)).value)) {
                    System.out.println("OK");
                } else {
                    System.out.println("Error");
                }
            } else {
                System.out.println("Empty");
            }
        } else {
            System.out.println("Empty");
        }
    }
}
