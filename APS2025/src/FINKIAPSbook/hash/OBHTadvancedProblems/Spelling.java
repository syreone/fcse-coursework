package FINKIAPSbook.hash.OBHTadvancedProblems;

import java.io.*;
import FINKIAPSbook.hash.MapEntry;
import FINKIAPSbook.hash.OBHT;

class Word implements Comparable<Word> {
    String word;

    public Word(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object obj) {
        Word temp = (Word) obj;
        return this.word.equals(temp.word);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < word.length(); i++) {
            hash += word.charAt(i);
        }
        hash += word.length();
        return hash;
    }

    @Override
    public String toString() {
        return word;
    }

    @Override
    public int compareTo(Word arg0) {
        return word.compareTo(arg0.word);
    }
}

public class Spelling {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        OBHT<Word, String> hashtable = new OBHT<Word, String>(2*n);

        for (int i=0; i<n; i++) {
            Word word = new Word(br.readLine());
            String newWord = word.word.toLowerCase().replaceAll("\\?",
                    "").replaceAll("\\!", "").replaceAll("\\.",
                    "").replaceAll("\\,", "");
            hashtable.insert(new Word(newWord), word.word.replaceAll("\\?",
                    "").replaceAll("\\!", "").replaceAll("\\.",
                    "").replaceAll("\\,", ""));
        }
        String text = br.readLine();
        String [] p = text.split(" ");
        int m = 0;

        for (int i=0; i<p.length; i++){
            String original = p[i];
            p[i] = p[i].toLowerCase().replaceAll("\\?", "").replaceAll("\\!",
                    "").replaceAll("\\.", "").replaceAll("\\,", "");
            if(hashtable.search(new Word(p[i]))==-1){
                System.out.println(original.replaceAll("\\?", "").replaceAll("\\!", "").replaceAll("\\.",
                        "").replaceAll("\\,", ""));
            } else {
                m++;
            }
        }
        if (m==p.length) {
            System.out.println("Bravo");
        }
    }
}
