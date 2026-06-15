package exercises.arraysAndLists.exercise3;

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
        head = insertTargetAfterLength(head, L);
        printList(head);
    }

    static Node insertTargetAfterLength(Node head, int L) {
        Node current = head;
        while (current != null) {
            if (current.data.length() == L) {
                Node newNode = new Node("Target");
                newNode.next = current.next;
                current.next = newNode;
                current = newNode.next;
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

