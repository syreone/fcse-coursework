package exercises.arraysAndLists.exercise2;

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
        head = filterList(head, L);
        printList(head);
    }

    static Node filterList(Node head, int L) {
        while (head != null && head.data.length() < L) {
            head = head.next;
        }
        Node current = head;
        while (current != null && current.next != null) {
            if (current.next.data.length() < L) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
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

