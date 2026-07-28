package FINKIAPSbook.hash.CBHTadvancedProblems;

import FINKIAPSbook.hash.CBHT;
import FINKIAPSbook.hash.MapEntry;
import FINKIAPSbook.hash.SLLNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.util.Objects.hash;

class Name implements Comparable<Name> {
    String name;

    public String getIme(){
        return name;
    }

    public void setIme(String name) {
        this.name = name;
    }

    public Name(String name) {
        this.name = name.toUpperCase();
    }

    @Override
    public boolean equals(Object obj) {
        Name temp = (Name) obj;
        return this.name.equals(temp.name);
    }

    @Override
    public int hashCode(){
        int hash = (100*name.charAt(0) + name.charAt(1));
        return hash;
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public int compareTo(Name arg0) {
        return name.compareTo(arg0.name);
    }
}

public class Statisitcs {
    public static void main(String[] args) throws Exception, IOException {
        CBHT<Name, Integer> tableM, tableF;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        tableM = new CBHT<Name, Integer>(9091);
        tableF = new CBHT<Name, Integer>(9091);

        for (int i=1; i<=n; i++){
            String line = br.readLine();
            String[] input = line.split(" ");
            Name nameUpper = new Name(input[0].toUpperCase());

            if (input[1].compareTo("M") == 0){
                SLLNode<MapEntry<Name, Integer>> resM = tableM.search(nameUpper);
                if(resM==null){
                    tableM.insert(nameUpper,1);
                }else {
                    int oldValue = resM.element.value;
                    tableM.insert(nameUpper, oldValue+1);
                }
            }

            if(input[1].compareTo("F") == 0){
                SLLNode<MapEntry<Name, Integer>> resF = tableF.search(nameUpper);
                if(resF==null){
                    tableF.insert(nameUpper, 1);
                }else {
                    int oldValue = resF.element.value;
                    tableF.insert(nameUpper, oldValue+1);
                }
            }
        }

        String sex = (br.readLine()).toUpperCase();
        String names = (br.readLine()).toUpperCase();

        while(names.compareTo("END")!=0){
            if(sex.compareTo("M")==0){
                SLLNode<MapEntry<Name, Integer>> resM1 = tableM.getFirst(new Name(names));
                SLLNode<MapEntry<Name, Integer>> curr;

                for (curr = resM1; curr!= null; curr = curr.succ) {
                    System.out.println(curr.element.key.getIme());
                }

                SLLNode<MapEntry<Name, Integer>> resM2 = tableM.search(new Name(names));

                if(resM2==null){
                    System.out.println("No such name");
                    names = (br.readLine()).toUpperCase();
                } else {
                    System.out.println(sex+" "+resM2.element.key.toString()+" "+resM2.element.value.toString());
                    names = (br.readLine()).toUpperCase();
                }
            }

            if(sex.compareTo("F")==0){
                SLLNode<MapEntry<Name, Integer>> resF1 = tableF.getFirst(new Name(names));
                SLLNode<MapEntry<Name, Integer>> curr1;

                for(curr1 = resF1; curr1 != null; curr1 = curr1.succ){
                    System.out.println(curr1.element.key.getIme());
                }
                SLLNode<MapEntry<Name, Integer>> resF2 = tableF.search(new Name(names));
                if(resF2==null){
                    System.out.println("No such name");
                    names = (br.readLine()).toUpperCase();
                } else {
                    System.out.println(sex+" "+resF1.element.key.toString()+" "+resF1.element.value.toString());
                    names = (br.readLine()).toUpperCase();
                }
            }
        }
    }
}
