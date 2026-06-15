package labExercises.lab2.inLab;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        DLL<Task> list = new DLL<>();
        for (int i=0;i<n;i++) {
            int time=sc.nextInt();
            int profit=sc.nextInt();
            list.insertLast(new Task(time, profit));
        }
        double maxEarnings = getMaxEarnings(list, 40);
        System.out.println((int)maxEarnings);
    }

    private static double getMaxEarnings(DLL<Task> list,int hours) {
        list.sort();
        double earnings=0;
        int remaining=hours;
        DLLNode<Task> curr= list.getFirst();
        while (curr!=null && remaining>0) {
            Task t= curr.element;
            if (t.time<=remaining) {
                earnings+=t.profit;
                remaining-=t.time;
            }else{
                earnings+=(double)t.profit*remaining/t.time;
                remaining=0;
            }
            curr=curr.succ;
        }
        return earnings;
    }
}

class Task {
    int time;
    int profit;
    double profitZaVreme;
    Task(int time, int profit) {
        this.time=time;
        this.profit=profit;
        this.profitZaVreme =(double)profit/time;
    }
    public String toString() {
        return "("+time+","+profit+")";
    }
}

class DLLNode<E> {
    E element;
    DLLNode<E> pred, succ;
    DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }
    public String toString() {
        return element.toString();
    }
}

class DLL<E> {
    private DLLNode<E> first, last;
    void insertLast(E o) {
        if (first == null) first = last = new DLLNode<>(o, null, null);
        else {
            DLLNode<E> ins = new DLLNode<>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }
    DLLNode<E> getFirst() {
        return first;
    }
    DLLNode<E> getLast() {
        return last;
    }
    void setFirst(DLLNode<E> node) {
        first = node;
    }
    void setLast(DLLNode<E> node) {
        last = node;
    }

    void sort() {
        if (first == null) return;
        for (DLLNode<E> i=first; i!=null; i=i.succ) {
            for (DLLNode<E> j=i.succ; j!=null; j=j.succ) {
                Task t1 = (Task)i.element;
                Task t2 = (Task)j.element;
                if (t1.profitZaVreme <t2.profitZaVreme) {
                    E tmp = i.element;
                    i.element = j.element;
                    j.element = tmp;
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        DLLNode<E> tmp = first;
        if (tmp != null) sb.append(tmp);
        while (tmp != null && tmp.succ != null) {
            tmp = tmp.succ;
            sb.append("<->").append(tmp);
        }
        return sb.toString();
    }
}
