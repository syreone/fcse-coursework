package labExercises.lab7.inLab;

import java.util.Scanner;
import java.util.Stack;

class BNode<E extends Comparable<E>> {
    E info;
    BNode<E> left;
    BNode<E> right;
    BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }
    BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }
}

class BinarySearchTree<E extends Comparable<E>> {
    private BNode<E> root;

    public void insert(E x) {
        root = insert(x, root);
    }

    private BNode<E> insert(E x, BNode<E> t) {
        if (t == null) {
            t = new BNode<E>(x, null, null);
        } else if (x.compareTo(t.info) < 0) {
            t.left = insert(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = insert(x, t.right);
        }
        return t;
    }

    private BNode<E> findLCA(BNode<E> t, E p, E q) {
        while (t != null) {
            if (p.compareTo(t.info) < 0 && q.compareTo(t.info) < 0) {
                t = t.left;
            } else if (p.compareTo(t.info) > 0 && q.compareTo(t.info) > 0) {
                t = t.right;
            } else {
                return t;
            }
        }
        return null;
    }

    public int sumSmallestK(E p, E q, int k) {
        BNode<E> lca = findLCA(root, p, q);
        if (lca == null) return 0;

        int sum = 0;
        int count = 0;
        Stack<BNode<E>> stack = new Stack<>();
        BNode<E> curr = lca;

        while ((curr != null || !stack.isEmpty()) && count < k) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            sum += (Integer) curr.info;
            count++;
            curr = curr.right;
        }

        return sum;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < n; i++)
            bst.insert(sc.nextInt());
        int p = sc.nextInt();
        int q = sc.nextInt();
        int k = sc.nextInt();
        System.out.println(bst.sumSmallestK(p, q, k));
    }
}