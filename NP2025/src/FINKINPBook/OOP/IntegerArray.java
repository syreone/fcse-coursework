package FINKINPBook.OOP;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class IntegerArray {
    private int a[];

    public IntegerArray(int a[]) {
        this.a = Arrays.copyOf(a, a.length);
    }

    private IntegerArray(int a[], boolean to_copy) {
        if (to_copy)
            this.a = a;
        else
            this.a = Arrays.copyOf(a, a.length);
    }

    public int length() {
        return a.length;
    }

    public int getElementAt(int i) {
        return a[i];
    }

    public int sum() {
        int sum = 0;
        for (int e : a) sum += e;
        return sum;
    }

    public double average() {
        return sum() * 1.0 / length();
    }

    public IntegerArray getSorted() {
        int sorted_a[] = Arrays.copyOf(a, length());
        Arrays.sort(sorted_a);
        return new IntegerArray(sorted_a);
    }

    public IntegerArray concat(IntegerArray ia) {
        int res_a[] = new int[a.length + ia.a.length];
        System.arraycopy(a, 0, res_a, 0, a.length);
        System.arraycopy(ia.a, 0, res_a, a.length, ia.a.length);
        return new IntegerArray(res_a, true);
    }

    public String toString() {
        return Arrays.toString(a);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        IntegerArray other = (IntegerArray) obj;
        if (!Arrays.equals(a, other.a))
            return false;
        return true;
    }
}

class ArrayReader {
    public static IntegerArray readIntegerArray(InputStream input) {
        Scanner jin = new Scanner(input);
        int n = jin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = jin.nextInt();
        }
        jin.close();
        return new IntegerArray(a);
    }
}

class IntegerArrayTester {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        IntegerArray ia = null;
        switch (s) {
            case "testSimpleMethods":
                ia = new IntegerArray(generateRandomArray(sc.nextInt()));
                testSimpleMethods(ia);
                break;
            case "testConcat":
                testConcat(sc);
                break;
            case "testEquals":
                testEquals(sc);
                break;
            case "testSorting":
                testSorting(sc);
                break;
            case "testReading":
                testReading(new ByteArrayInputStream(sc.nextLine().getBytes()));
                break;
            case "testImmutability":
                int a[] = generateRandomArray(sc.nextInt());
                ia = new IntegerArray(a);
                testSimpleMethods(ia);
                testSimpleMethods(ia);
                IntegerArray sorted_ia = ia.getSorted();
                testSimpleMethods(ia);
                testSimpleMethods(sorted_ia);
                sorted_ia.getSorted();
                testSimpleMethods(sorted_ia);
                testSimpleMethods(ia);
                a[0] += 2;
                testSimpleMethods(ia);
                ia = ArrayReader.readIntegerArray(new ByteArrayInputStream(integerArrayToString(ia).getBytes()));
                testSimpleMethods(ia);
                break;
        }
        sc.close();
    }

    static void testReading(InputStream in) {
        IntegerArray read = ArrayReader.readIntegerArray(in);
        System.out.println(read);
    }

    static void testSorting(Scanner sc){
        int [] a = readArray(sc);
        IntegerArray ia = new IntegerArray(a);
        System.out.println(ia.getSorted());
    }

    static void testEquals (Scanner sc){
        int [] a = readArray(sc);
        int [] b = readArray(sc);
        int [] c = readArray(sc);
        IntegerArray ia = new IntegerArray(a);
        IntegerArray ib = new IntegerArray(b);
        IntegerArray ic = new IntegerArray(c);
        System.out.println(ia.equals(ib));
        System.out.println(ia.equals(ic));
        System.out.println(ib.equals(ic));
    }

    static void testConcat(Scanner sc){
        int [] a = readArray(sc);
        int [] b = readArray(sc);
        IntegerArray array1 = new IntegerArray(a);
        IntegerArray array2 = new IntegerArray(b);
        IntegerArray concatenated = array1.concat(array2);
        System.out.println(concatenated);
    }

    static void testSimpleMethods(IntegerArray ia) {
        System.out.print(integerArrayToString(ia));
        System.out.println(ia);
        System.out.println(ia.sum());
        System.out.printf("%.2f\n", ia.average());
    }

    static String integerArrayToString(IntegerArray ia) {
        StringBuilder sb = new StringBuilder();
        sb.append(ia.length()).append('\n');
        for (int i = 0; i < ia.length(); ++i)
            sb.append(ia.getElementAt(i)).append(' ');
        sb.append('\n');
        return sb.toString();
    }

    static int[] readArray(Scanner sc){
        int n = sc.nextInt();
        int [] a = new int[n];
        for (int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }
        return a;
    }

    static int[] generateRandomArray(int k){
        Random rnd = new Random(k);
        int n = rnd.nextInt(8) + 2;
        int a[] = new int[n];
        for (int i = 0; i < n; ++i){
            a[i] = rnd.nextInt(20) - 5;
        }
        return a;
    }
}