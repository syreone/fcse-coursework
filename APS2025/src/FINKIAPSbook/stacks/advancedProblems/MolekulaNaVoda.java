package FINKIAPSbook.stacks.advancedProblems;

import FINKIAPSbook.stacks.ArrayQueue;
import FINKIAPSbook.stacks.ArrayStack;

import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;



public class MolekulaNaVoda {
    public static String napraviMolekula(ArrayQueue<String> l) {
        ArrayStack<String> vodorod = new ArrayStack<String>(1000);
        ArrayStack<String> kislorod = new ArrayStack<String>(1000);
        String s;
        String izlez = new String();
        int br = 0;

        while (!l.isEmpty()) {
            s = (String) l.dequeue();
            if (s.equals("H"))
                vodorod.push(s);
            else
                kislorod.push(s);
        }

        while (!kislorod.isEmpty()) {
            if (!vodorod.isEmpty()) {
                vodorod.pop();
                if (!vodorod.isEmpty()) {
                    vodorod.pop();
                    kislorod.pop();
                    br++;
                } else {
                    vodorod.push("H");
                    break;
                }
            } else
                break;
        }

        while (!vodorod.isEmpty()) {
            vodorod.pop();
            izlez += "H\n";
        }

        while (!kislorod.isEmpty()) {
            kislorod.pop();
            izlez += "O\n";
        }

        System.out.println(br);
        return izlez;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] vlez = new String[1000];
        vlez = br.readLine().split(" ");

        ArrayQueue<String> atomi = new ArrayQueue<String>(1000);
        for (int i = 0; i < vlez.length; i++)
            atomi.enqueue(vlez[i]);

        System.out.println(napraviMolekula(atomi));
    }
}