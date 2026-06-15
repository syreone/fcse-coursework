package FINKIAPSbook.arrays.exercises;

import java.util.Scanner;

public class DodadiSredniVrednosti {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n];

        for (int i=0; i<n; i++) {
            numbers[i] = sc.nextInt();
        }

        System.out.print(numbers[0]);
        for (int i = 1; i < n; i++) {
            int avg = (numbers[i-1] + numbers[i] + 1)/2;
            System.out.print(" " + avg + " " + numbers[i]);
        }
        System.out.println();
    }
}
