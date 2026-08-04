package FINKIAPSbook.hash.OBHTsimpleProblems;

import java.io.*;

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {
    K key;
    E value;

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo(K that) {
        @SuppressWarnings("unchecked")
        MapEntry<K, E> other = (MapEntry<K, E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString() {
        return "(" + key + "," + value + ")";
    }
}

class OBHT<K extends Comparable<K>,E> {

    private MapEntry<K, E>[] buckets;
    static final int NONE = -1;
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static final MapEntry former = new MapEntry(null, null);
    private int occupancy = 0;

    @SuppressWarnings("unchecked")
    public OBHT(int m) {
        buckets = (MapEntry<K, E>[]) new MapEntry[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public MapEntry<K, E> getBucket(int i) {
        return buckets[i];
    }

    public int search(K targetKey) {
        int b = hash(targetKey);
        int n_search = 0;
        for (; ; ) {
            MapEntry<K, E> oldEntry = buckets[b];
            if (oldEntry == null)
                return NONE;
            else if (targetKey.equals(oldEntry.key))
                return b;
            else {
                b = (b + 1) % buckets.length;
                n_search++;
                if (n_search == buckets.length)
                    return NONE;
            }
        }
    }

    public void insert(K key, E val) {
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        int n_search = 0;

        for (; ; ) {
            MapEntry<K, E> oldEntry = buckets[b];
            if (oldEntry == null) {
                if (++occupancy == buckets.length) {
                    System.out.println("Hash table is full!!!");
                }
                buckets[b] = newEntry;
                return;
            } else if (oldEntry == former
                    || key.equals(oldEntry.key)) {
                buckets[b] = newEntry;
                return;
            } else {
                b = (b + 1) % buckets.length;
                n_search++;
                if (n_search == buckets.length)
                    return;

            }
        }
    }

    @SuppressWarnings("unchecked")


    public void delete(K key) {
        int b = hash(key);
        int n_search = 0;
        for (; ; ) {
            MapEntry<K, E> oldEntry = buckets[b];

            if (oldEntry == null)
                return;
            else if (key.equals(oldEntry.key)) {
                buckets[b] = former;
                return;

            } else {
                b = (b + 1) % buckets.length;
                n_search++;
                if (n_search == buckets.length)
                    return;
            }

        }

    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] == null)
                temp += "";
            else
                temp += buckets[i].key + "=" + buckets[i].value + "\n";

        }
        return temp;
    }
}

public class RedCross {
    public static void main(String[] args) throws IOException {
        OBHT<String, Integer> hashtable = new OBHT<String, Integer>(11);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String input;


        for (int i=0; i<n; i++){
            input = br.readLine();
            String[] row = input.split(" ");
            String key = row[1];
            key = key.replaceAll("[1-2]","");
            if (hashtable.search(key)==-1){
                hashtable.insert(key,1);
            } else {
                hashtable.insert(key, hashtable.getBucket(hashtable.search(key)).value+1);
            }
        }
        System.out.println(hashtable);
    }
}
