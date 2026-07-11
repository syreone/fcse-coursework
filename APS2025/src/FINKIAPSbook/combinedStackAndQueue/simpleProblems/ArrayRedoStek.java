package FINKIAPSbook.combinedStackAndQueue.simpleProblems;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

interface RedoStek {
    public boolean isEmpty();

    public Character peek ();

    public void clear();

    public void push (Character x);

    public Character pop ();
};

class ArrayRedoStek implements RedoStek {
    private ArrayQueue<Character> q;
    private ArrayStack<Character> s;
    private int lnRed, lnStek;

    public ArrayRedoStek (int maxRed, int maxStek) {
        s = new ArrayStack<Character>(maxStek);
        q = new ArrayQueue<Character>(maxRed);
        lnStek = maxStek;
        lnRed = maxRed;
    }

    @Override
    public boolean isEmpty() {
        if(q.isEmpty()&&s.isEmpty())
            return true;
        return false;
    }

    @Override
    public Character peek() {
        if (q.isEmpty())
            return s.peek();
        return q.peek();
    }

    @Override
    public void clear(){

    }

    @Override
    public void clear(){

    }

    @Override
    public void push(Character x) {
        if (q.isEmpty())
            q.enqueue(x);
        else if(s.isEmpty())
            s.push(x);
        else{
            if(s.size() < lnStek&&q.size() < lnRed){
                if(s.peek() < q.peek())
                    s.push(x);
                else
                    q.enqueue(x);
            }
            else if(s.size() < lnStek)
                s.push(x);
            else if(q.size() < lnRed)
                q.enqueue(x);
            else
                System.out.println("RedoStek is full");
        }
    }

    @Override
    public Character pop(){
        while(!q.isEmpty())
            return q.dequeue();
        while(!s.isEmpty())
            return s.pop();
        return null;
    }
};

public class TestRedoStek {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int maxRed = Integer.parseInt(br.readLine());
        int maxStek = Integer.parseInt(br.readLine());

        ArrayRedoStek sq = new ArrayRedoStek(maxRed, maxStek);

        int brElementi = Integer.parseInt(br.readLine());

        for (int i=0; i<brElementi; i++){
            char x = (char)br.read();
            sq.push(x);
        }

        System.out.println(sq.peek());

        while(!sq.isEmpty()){
            System.out.print(sq.pop()+" ");
        }
    }
}