package FINKIAPSbook.queues.advancedProblems;

import FINKIAPSbook.stacks.ArrayQueue;

import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Kolokvium {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String vlez;

        ArrayQueue<String> redMath = new ArrayQueue<String>(100);
        ArrayQueue<String> redOstanati = new ArrayQueue<String>(100);
        LinkedList<String> listRealMath = new LinkedList<String>();

        int i;
        int brStudentiTermin, brStudentiMath, brOstanati, brRealMath;

        brStudentiTermin = Integer.parseInt(br.readLine());
        brStudentiMath = Integer.parseInt(br.readLine());
        for (i=0; i<brStudentiMath; i++){
            vlez = br.readLine();
            redMath.enqueue(vlez);
        }

        brOstanati = Integer.parseInt(br.readLine());
        for (i=0; i<brOstanati; i++){
            vlez = br.readLine();
            redOstanati.enqueue(vlez);
        }

        brRealMath = Integer.parseInt(br.readLine());
        for (i=0; i<brRealMath; i++){
            vlez = br.readLine();
            listRealMath.add(vlez);
        }

        String elem;
        int t = 1;

        while (!redMath.isEmpty()){
            System.out.println(t);
            for (i=0; i<brStudentiTermin;){
                if (!redMath.isEmpty()){
                    elem = redMath.peek();
                    if (!listRealMath.contains(elem))
                        redOstanati.enqueue(redMath.dequeue());
                    else{
                        elem = redMath.dequeue();
                        i++;
                        System.out.println(elem);
                    }
                }
                else if (!redOstanati.isEmpty()){
                    elem = redOstanati.dequeue();
                    i++;
                    System.out.println(elem);
                }
                else break;
            }
            t++;
            if (redMath.isEmpty())
                break;
        }
        if (redMath.isEmpty()){
            while (!redOstanati.isEmpty()){
                System.out.println(t);
                for (i = 0;i<brStudentiTermin;){
                    if (!redOstanati.isEmpty()){
                        elem = redOstanati.dequeue();
                        i++;
                        System.out.println(elem);
                    }
                    else break;
                }
                t++;
            }
        }
    }
}
