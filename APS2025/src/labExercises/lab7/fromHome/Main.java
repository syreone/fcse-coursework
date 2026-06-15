package labExercises.lab7.fromHome;

import java.util.Scanner;

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
    private int sum;

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
        } else;
        return t;
    }

    public void transformTree() {
        sum = 0;
        inorderTransform(root);
    }

    @SuppressWarnings("unchecked")
    private void inorderTransform(BNode<E> node) {
        if (node==null)
            return;
        inorderTransform(node.left);
        int original=(Integer) node.info;
        node.info=(E) Integer.valueOf(original+sum);
        sum+=original;
        inorderTransform(node.right);
    }

    public void printTreeWithIndent() {
        printTreeWithIndent(root, 0);
    }

    private void printTreeWithIndent(BNode<E> t, int indent) {
        if (t != null) {
            printTreeWithIndent(t.left, indent + 1);
            for (int i = 0; i < indent; i++)System.out.print("   ");
            System.out.println(t.info);
            printTreeWithIndent(t.right, indent + 1);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < n; i++)
            bst.insert(sc.nextInt());
        bst.transformTree();
        bst.printTreeWithIndent();
    }
}

