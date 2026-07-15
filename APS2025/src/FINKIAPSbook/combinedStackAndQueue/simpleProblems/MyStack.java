package FINKIAPSbook.combinedStackAndQueue.simpleProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class MyStack<E> extends Stack<E> {
    ArrayQueue<E> q1, q2;
    int size;

    public MyStack() {
        q1 = new ArrayQueue<E>(1000);
        q2 = new ArrayQueue<E>(1000);
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        if(q1.isEmpty()&&q2.isEmpty())
            return true;
        else
            return false;
    }
    @Override
    public E peek() {
        if (q1.isEmpty())
            return null;
        return q1.peek();
    }

    public int getSize(){
        return size;
    }

    @Override
    public void clear(){

    }

    @Override
    public E push(E x) {
        size++;

        q2.enqueue(x);

        while(!q1.isEmpty())
        {
            q2.enqueue(q1.peek());
            q1.dequeue();
        }

        ArrayQueue<E> q = q1;
        q1 = q2;
        q1 = q;
        return x;
    }

    @Override
    public E pop() {
        if (q1.isEmpty())
            return null;
        E tmp = q1.dequeue();
        size--;
        return tmp;
    }
}

class MyStackTest {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MyStack ob = new MyStack();

        int N = Integer.parseInt(br.readLine());

        for (int i=0; i<N; i++){
            int x =  Integer.parseInt(br.readLine());
            ob.push(x);
        }
        String str = br.readLine();
        while(!str.equals("KRAJ")){
            int x = Integer.parseInt(str);
            if (x==1){
                if(ob.peek()==null)
                    System.out.println("Prazen stek");
                else
                    System.out.println(ob.pop());
            }
            if (x==2){
                str = br.readLine();
                x = Integer.parseInt(str);
                ob.push(x);
            }
            str = br.readLine();
        }
        System.out.println("Goleminata na stekot e: "+ob.getSize());
    }
}
