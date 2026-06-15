package exercises.introJava;

import java.util.Scanner;

class QuarterlySales {

    private int numOfSales;
    private int[] revenues;
    private int quarterNo;

    public QuarterlySales(int numOfSales, int[] revenues, int quarterNo) {
        this.numOfSales = numOfSales;
        this.revenues = revenues;
        this.quarterNo = quarterNo;
    }

    public int[] getRevenues() {
        return revenues;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Q").append(quarterNo).append(": [");
        for (int i=0; i <revenues.length; i++){
            sb.append(revenues[i]);
            if (i<revenues.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

class SalesPerson {

    private String name;
    private QuarterlySales[] quarters;

    public SalesPerson(String name, QuarterlySales[] quarters) {
        this.name = name;
        this.quarters = quarters;
    }

    public String getName() {
        return name;
    }

    public QuarterlySales[] getQuarters() {
        return quarters;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class Main {

    public static int sumSales(SalesPerson sp) {
        int total = 0;
        for (QuarterlySales q : sp.getQuarters()) {
            for (int revenue : q.getRevenues()) {
                total += revenue;
            }
        }
        return total;
    }

    public static SalesPerson salesChampion(SalesPerson [] arr) {
        SalesPerson best = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (sumSales(arr[i]) > sumSales(best)) {
                best = arr[i];
            }
        }
        return best;
    }

    public static void table(SalesPerson [] arr) {
        System.out.print("SP");
        for (int q = 1; q <= 4; q++) {
            System.out.print("   " + q);
        }
        System.out.println("   Total");

        for (SalesPerson sp : arr) {
            System.out.print(sp.getName());

            int total = 0;
            for (QuarterlySales q : sp.getQuarters()) {
                int qSum = 0;
                for (int r : q.getRevenues()) qSum += r;
                total += qSum;
                System.out.print("   " + qSum);
            }

            System.out.println("   " + total);
        }
    }

    public static void main(String[] args) {
        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        SalesPerson [] arr = new SalesPerson[n];
        for(int i = 0; i < n; i++) {
            String name = input.next();
            QuarterlySales[] quarters = new QuarterlySales[4];

            for (int q= 0; q < 4; q++) {
                int numSales = input.nextInt();
                int[] revenues = new int[numSales];
                for (int s=0; s < numSales; s++) {
                    revenues[s] = input.nextInt();
                }
                quarters[q] = new QuarterlySales(numSales, revenues, q + 1);
            }

            arr[i] = new SalesPerson(name, quarters);
        }

        table(arr);
        System.out.println();
        System.out.println("SALES CHAMPION: " + salesChampion(arr).getName());
    }
}