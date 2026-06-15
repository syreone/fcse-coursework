package FINKIAPSbook.arrays.exercises;

import java.util.Scanner;

public class BrishiDupli {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n];

        for (int i=0; i<n; i++) {
            numbers[i] = sc.nextInt();
        }

        System.out.print(numbers[0]);
        for (int i = 1; i < n; i++) {
            if (numbers[i] != numbers[i-1]) {
                System.out.print(" " + numbers[i]);
            }
        }
        System.out.println();
    }
}
