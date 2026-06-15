package FINKIAPSbook.queues.advancedProblems;

import FINKIAPSbook.stacks.ArrayQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Polaganje {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String vlez;

        ArrayQueue<String> redEtest = new ArrayQueue<String>(100);
        ArrayQueue<String> redZadaci = new ArrayQueue<String>(100);
        ArrayQueue<String> redEtestZadaci = new ArrayQueue<String>(100);

        int i;
        int brStudentiEtest, brStudentiZadaci, brStudentiEtestZadaci;

        brStudentiEtest = Integer.parseInt(br.readLine());
        for (i = 0; i < brStudentiEtest; i++) {
            vlez = br.readLine();
            redEtest.enqueue(vlez);
        }
        brStudentiZadaci = Integer.parseInt(br.readLine());
        for (i = 0; i < brStudentiZadaci; i++) {
            vlez = br.readLine();
            redEtest.enqueue(vlez);
        }
        brStudentiEtestZadaci = Integer.parseInt(br.readLine());
        for (i = 0; i < brStudentiEtestZadaci; i++) {
            vlez = br.readLine();
            redEtest.enqueue(vlez);
        }
        String elem;
        int t = 1;
        System.out.println("Polagaat e-test:");
        while (!redEtest.isEmpty()) {
            System.out.println("termin" + t);
            for (i = 0; i < 20; ) {
                if (!redEtest.isEmpty()) {
                    elem = redEtest.dequeue();
                    i++;
                    System.out.println(elem);
                } else if (!redEtestZadaci.isEmpty()) {
                    elem = redEtestZadaci.dequeue();
                    i++;
                    System.out.println(elem);
                    redZadaci.enqueue(elem);
                } else break;
            }
            t++;
            if (redEtest.isEmpty())
                break;
        }
        if (redEtest.isEmpty()) {
            while (!redEtestZadaci.isEmpty()) {
                System.out.println("termin" + t);
                for (i = 0; i < 20; ) {
                    if (!redEtestZadaci.isEmpty()) {
                        elem = redEtestZadaci.dequeue();
                        i++;
                        System.out.println(elem);
                        redZadaci.enqueue(elem);
                    } else break;
                }
                t++;
            }
        }
        t = 1;
        System.out.println("Polagaat zadaci:");
        if (redEtestZadaci.isEmpty())
            while (!redZadaci.isEmpty()) {
                System.out.println("termin" + t);
                for (i = 0; i < 20; ) {
                    if (!redZadaci.isEmpty()) {
                        elem = redZadaci.dequeue();
                        i++;
                        System.out.println(elem);
                    } else break;
                }
                t++;
            }
    }
}