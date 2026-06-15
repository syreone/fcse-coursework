package exercises.arraysAndLists.exercise5;

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
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        int L = Integer.parseInt(sc.nextLine());

        printList(head);
        head = moveToBack(head, L);
        printList(head);
    }

    static Node moveToBack(Node head, int L) {
        Node keepHead = null, keepTail = null;
        Node moveHead = null, moveTail = null;

        Node curr = head;

        while (curr != null) {
            Node next = curr.next;
            curr.next = null;

            if (curr.data.length() == L) {
                if (moveHead == null) moveHead = moveTail = curr;
                else {
                    moveTail.next = curr;
                    moveTail = curr;
                }
            } else {
                if (keepHead == null) keepHead = keepTail = curr;
                else {
                    keepTail.next = curr;
                    keepTail = curr;
                }
            }
            curr = next;
        }

        if (keepHead == null) return moveHead;

        if (moveHead == null) return keepHead;

        keepTail.next = moveHead;

        return keepHead;
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
