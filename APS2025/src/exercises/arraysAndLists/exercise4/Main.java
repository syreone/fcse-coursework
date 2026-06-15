package exercises.arraysAndLists.exercise4;

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
        head = insertOutlier(head, L);
        printList(head);
    }

    static Node insertOutlier(Node head, int L) {
        Node newHead = null, newTail = null;
        Node curr = head;

        while (curr != null) {
            if (curr.data.length() > L) {
                Node out = new Node("Outlier");
                if (newHead == null) {
                    newHead = newTail = out;
                } else {
                    newTail.next = out;
                    newTail = out;
                }
            }

            Node next = curr.next;
            curr.next = null;
            if (newHead == null) {
                newHead = newTail = curr;
            } else {
                newTail.next = curr;
                newTail = curr;
            }

            curr = next;
        }

        return newHead;
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

