package FINKIAPSbook.queues.advancedProblems;

import FINKIAPSbook.stacks.ArrayQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Konsultacii {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String vlez;

        ArrayQueue<String> redAPS = new ArrayQueue<String>(100);
        ArrayQueue<String> redTip = new ArrayQueue<String>(100);
        ArrayQueue<String> redMMS = new ArrayQueue<String>(100);

        int i;
        int brStudentiAPS, brStudentiMMS;

        brStudentiAPS = Integer.parseInt(br.readLine());
        for (i = 0; i<brStudentiAPS; i++){
            vlez = br.readLine();
            String[] pom = vlez.split(" ");
            redAPS.enqueue(pom[0]);
            redTip.enqueue(pom[1]);
        }

        brStudentiMMS = Integer.parseInt(br.readLine());
        for (i = 0; i<brStudentiMMS; i++){
            vlez = br.readLine();
            redMMS.enqueue(vlez);
        }

        String pom, pomTip, tip="";
        if (!redAPS.isEmpty()){
            pom = redAPS.dequeue();
            System.out.println(pom);
            tip = redTip.dequeue();
            i++;
        }

        while(!redAPS.isEmpty()){
            pom = redAPS.dequeue();
            pomTip = redTip.dequeue();
                if(tip.equals(pomTip)){
                    redAPS.enqueue(pom);
                    redTip.enqueue(pomTip);
                    if(!redMMS.isEmpty()){
                        pom = redMMS.dequeue();
                        System.out.println(pom);
                    }
                } else {
                    System.out.println(pom);
                    tip = pomTip;
                }
        }
        while (!redMMS.isEmpty()){
            pom = redMMS.dequeue();
            System.out.println(pom);
        }
    }
}

