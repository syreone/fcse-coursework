package exercises.arraysAndLists.exercise6;

import java.util.Scanner;

class Node {
    String data;
    Node next;

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        Node head = null, tail = null;

        for (int i = 0; i < N; i++) {
            String s = sc.nextLine();
            Node newNode = new Node(s);
            if (head == null) head = tail = newNode;
            else { tail.next = newNode; tail = newNode; }
        }

        int L = Integer.parseInt(sc.nextLine());
        printList(head);
        head = moveLastToFront(head, L);
        printList(head);
    }

    static Node moveLastToFront(Node head, int L) {
        if (head == null || head.next == null) return head;
        Node prev = null, lastMatch = null, lastMatchPrev = null, current = head;

        while (current != null) {
            if (current.data.length() == L) {
                lastMatch = current;
                lastMatchPrev = prev;
            }
            prev = current;
            current = current.next;
        }

        if (lastMatch == null || lastMatch == head) return head;
        if (lastMatchPrev != null) lastMatchPrev.next = lastMatch.next;
        lastMatch.next = head;
        return lastMatch;
    }

    static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) System.out.print("->");
            current = current.next;
        }
        System.out.println();
    }
}

