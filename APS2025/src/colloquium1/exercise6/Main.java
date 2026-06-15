package colloquium1.exercise6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static int count(int N){
        Queue<Integer> deck = new LinkedList<>();

        for (int i = 1; i <= 51; i++) {
            deck.add(i);
        }

        int shuffles = 0;

        while (deck.peek() != N) {
            Stack<Integer> hand = new Stack<>();

            for (int i = 0; i < 7; i++) {
                hand.push(deck.poll());
            }

            while (!hand.isEmpty()) {
                deck.add(hand.pop());
                deck.add(deck.poll());
            }

            shuffles++;
        }

        return shuffles;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
        System.out.println(count(Integer.parseInt(br.readLine())));
    }
}
