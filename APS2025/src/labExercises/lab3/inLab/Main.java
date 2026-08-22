package labExercises.lab3.inLab;

import java.io.*;
import java.util.*;

public class Main {
    public static int solve(int []lights, int n, int len){
        Arrays.sort(lights);
        for (int i = 0; i < n; i++) {
            lights[i]--;
        }
        int edge = 0;
        int i = 0;
        int prev = 0;
        int ctr = 0;
        while (edge < len - 1 && i < len){
            while (i < n && lights[i] - 2 <= edge){
                prev = i;
                i++;
            }
            i = prev;
            edge = lights[i];
            while (edge < len  && lights[i] + 2 > edge){
                edge++;
            }
            ctr++;
            edge++;
            i++;
            if (edge > len - 1) return ctr;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[]lights = new int[n];

        for (int i = 0; i < n; i++) {
            lights[i] = sc.nextInt();
        }

        System.out.println(solve(lights, n, m));
    }
}