package FINKIAPSbook.stacks.advancedProblems;

import FINKIAPSbook.stacks.ArrayStack;

import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


public class PonishtuvanjeTopchinja {
    public static String ponistiTopcinja (List l) {
        ArrayStack<String> crveni = new ArrayStack<String>(100);
        ArrayStack<String> zeleni = new ArrayStack<String>(100);
        ArrayStack<String> sini = new ArrayStack<String>(100);
        String s;
        String izlez = new String();
        int n = 0;

        for (int i=0 ; i<l.size(); i++){
            s = (String) l.get(i);

            if (s.charAt(0) == 'R'){
                if (!crveni.isEmpty())
                    if(crveni.peek().equals(s))
                        crveni.push(s);
                    else
                        crveni.pop();
                else
                    crveni.push(s);
            }
            if (s.charAt(0) == 'G'){
                if (!zeleni.isEmpty())
                    if(zeleni.peek().equals(s))
                        zeleni.push(s);
                    else
                        zeleni.pop();
                else
                    zeleni.push(s);
            }
            if (s.charAt(0) == 'B'){
                if (!sini.isEmpty())
                    if(sini.peek().equals(s))
                        sini.push(s);
                    else
                        sini.pop();
                else
                    sini.push(s);
            }
        }
        while (!crveni.isEmpty()){
            n++;
            if(crveni.pop().charAt(1)=='+')
                izlez+="R-";
            else
                izlez+="R+";
        }
        while (!zeleni.isEmpty()){
            n++;
            if(zeleni.pop().charAt(1)=='+')
                izlez+="G-";
            else
                izlez+="G+";
        }
        while (!sini.isEmpty()){
            n++;
            if(sini.pop().charAt(1)=='+')
                izlez+="B-";
            else
                izlez+="B+";
        }
        System.out.println(n);
        return izlez;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String vlez[] = new String[100];
        vlez = br.readLine().split(" ");

        List<String> topcinja = new LinkedList<String>();
        for (int i=0; i<vlez.length; i++)
            topcinja.add(vlez[i]);

        System.out.println(ponistiTopcinja(topcinja));
    }
}
