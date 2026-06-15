package FINKIAPSbook.queues.simpleProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

interface Stack<E> {
    boolean isEmpty();
    E peek();
    void push(E x);
    E pop();
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack(int maxDepth) {
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }

    public boolean isEmpty() {
        return (depth == 0);
    }

    public E peek() {
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth - 1];
    }

    public void push(E x) {
        elems[depth++] = x;
    }

    public E pop() {
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

class ArrayQueue<E> implements Queue<E> {
    private E[] elems;
    private int length, front, rear;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int maxlength) {
        elems = (E[]) new Object[maxlength];
        clear();
    }

    public void clear() {
        length = 0;
        front = rear = 0;
    }

    public boolean isEmpty() {
        return (length == 0);
    }

    public int size() {
        return length;
    }

    public E peek() {
        if (length == 0)
            throw new NoSuchElementException();
        return elems[front];
    }

    public void enqueue(E x) {
        elems[rear++] = x;
        if (rear == elems.length)
            rear = 0;
        length++;
    }

    public E dequeue() {
        if (length == 0)
            throw new NoSuchElementException();
        E frontmost = elems[front];
        elems[front++] = null;
        if (front == elems.length)
            front = 0;
        length--;
        return frontmost;
    }
}

public class Vozovi {
    public static String preraspredeli(String[] vlez) {
        ArrayStack<Integer> starVoz = new ArrayStack<Integer>(vlez.length);
        ArrayStack<Integer> novVoz = new ArrayStack<Integer>(vlez.length);
        ArrayQueue<Integer> shina = new ArrayQueue<Integer>(vlez.length);
        int top;
        String out = new String("");

        for(int i=0; i<vlez.length; i++)
            starVoz.push(Integer.parseInt(vlez[i]));

        while(true){
            if(!starVoz.isEmpty()&&starVoz.peek()!=0)
                novVoz.push(starVoz.pop());
            else{
                while(!starVoz.isEmpty() && starVoz.peek()==0)
                    starVoz.pop();
                if(starVoz.isEmpty())
                    out+="Site vagoni se rasipani";
                else
                    novVoz.push(starVoz.pop());
            }

            while(!starVoz.isEmpty()){
                while(!starVoz.isEmpty() && starVoz.peek()==0){
                    starVoz.pop();
                }
                if (starVoz.isEmpty())
                    break;
                else{
                    top = starVoz.pop();
                    if (novVoz.peek() < top){
                        shina.enqueue(novVoz.pop());
                        novVoz.push(top);
                    }
                    else
                        shina.enqueue(top);
                }
            }
            if(!shina.isEmpty())novVoz.push(shina.dequeue());
            while(!shina.isEmpty()){
                top = shina.dequeue();
                if (novVoz.peek() < top){
                    starVoz.push(novVoz.pop());
                    novVoz.push(top);
                }
                else
                    starVoz.push(top);
            }
            if (starVoz.isEmpty() && shina.isEmpty()) break;
        }

        while(!novVoz.isEmpty()){
            out+=novVoz.pop()+" ";
        }

        return out;
    }

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String[] vlez = new String[n];

        for(int i=0;i<n;i++){
            vlez[i] = br.readLine();
        }

        System.out.println(preraspredeli(vlez));
        br.close();
    }
}
