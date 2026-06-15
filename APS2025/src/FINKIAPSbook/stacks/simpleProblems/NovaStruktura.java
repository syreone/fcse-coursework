package FINKIAPSbook.stacks.simpleProblems;

import FINKIAPSbook.lists.DLL.DLLNode;
import FINKIAPSbook.stacks.Stack;

import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class QuasiStack<E extends Comparable<E>> implements Stack<E> {
    private DLLNode<E> top, bottom;
    public QuasiStack () {
        top = null;
        bottom = null;
    }

    public boolean isEmpty () {
        return (top==null);
    }

    @Override
    public E peek() {
        return null;
    }

    public E peekTop () {
        if (top == null)
            throw new NoSuchElementException();
        return top.element;
    }

    public E peekBottom () {
        if (bottom==null)
            throw new NoSuchElementException();
        return bottom.element;
    }

    public void clear () {
        top = null;
        bottom = null;
    }

    public void push (E x) {
        DLLNode<E> ins = new DLLNode<E>(x, null, top);
        if (top == null)
            bottom = ins;
        else
            top.pred = ins;
        top = ins;
    }

    public E pop () {
        if (top == null)
            throw new NoSuchElementException();
        E topElem = top.element;
        E bottomElem = bottom.element;

        if (top==bottom){
            top = null;
            bottom = null;
            return topElem;
        }

        if (topElem.compareTo(bottomElem) == 1){
            top = top.succ;
            top.pred = null;
            return topElem;
        } else {
            bottom = bottom.pred;
            bottom.succ = null;
            return bottomElem;
        }
    }
}

public class NovaStruktura {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        QuasiStack<Integer> qs = new QuasiStack<Integer>();
        int brElementi = Integer.parseInt(br.readLine());
        for (int i=0; i<brElementi; i++){
            int x = Integer.parseInt(br.readLine());
            qs.push(x);
        }

        System.out.println(qs.peekTop());
        System.out.println(qs.peekBottom());

        while(!qs.isEmpty()){
            System.out.print(qs.pop()+" ");
        }
    }
}
