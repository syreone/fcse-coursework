package labExercises.lab3.fromHome;

import java.util.Scanner;

public class Main {

    static int binarySearch(int[] arr, int left, int right, int m) {
        if (left>right)
            return -1;

        int mid =(left+right)/2;

        if (arr[mid]==m)
            return mid;
        else if (arr[mid] > m)
            return binarySearch(arr, left, mid - 1, m);
        else
            return binarySearch(arr, mid + 1, right, m);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n];
        for (int i=0;i<n;i++)
            arr[i] = sc.nextInt();

        int pos = binarySearch(arr, 0, n - 1, m);

        if (pos==-1)
            System.out.println("Ne postoi");
        else
            System.out.println(pos);
    }
}
