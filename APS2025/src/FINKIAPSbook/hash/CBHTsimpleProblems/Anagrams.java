package FINKIAPSbook.hash.CBHTsimpleProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Anagrams {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        CBHT<String, Integer> hashtable = new CBHT<String, Integer>(2*n);
        String input;

        for (int i=1; i<=n; i++){
            input = br.readLine();
            char [] letters = input.toCharArray();
            Arrays.sort(letters);
            String sortedLetters = new String(letters);

            if (hashtable.search(sortedLetters) == null){
                hashtable.insert(sortedLetters,1);
            }else {
                SLLNode<MapEntry<String, Integer>> result = hashtable.search(sortedLetters);
                hashtable.insert(sortedLetters, result.element.value+1);
            }
        }

        String word = br.readLine();
        char [] letters = word.toCharArray();
        Arrays.sort(letters);
        String sortedLetters = new String(letters);
        SLLNode<MapEntry<String, Integer>> result = hashtable.search(sortedLetters);
        System.out.println(result.element.value);
    }
}
