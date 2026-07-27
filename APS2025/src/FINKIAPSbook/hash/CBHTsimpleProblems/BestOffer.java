package FINKIAPSbook.hash.CBHTsimpleProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Lecture implements Comparable<Lecture>{
    String date;
    String time;
    String place;
    Integer fee;

    public Lecture(String date, String time, String place, Integer fee){
        this.date = date;
        this.time = time;
        this.place = place;
        this.fee = fee;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee){
        this.fee = fee;
    }

    @Override
    public int compareTo(Lecture obj) {
        if (this.fee > obj.fee)
            return 1;
        else if (this.fee < obj.fee)
            return -1;
        else
            return 0;
    }
}

public class BestOffer {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        CBHT<String, ArrayList<Lecture>> hashtable = new CBHT<String, ArrayList<Lecture>>(2*N);

        for (int i=0; i<N; i++){
            String[] input = br.readLine().split(" ");
            Lecture p = new Lecture(input[0], input[1], input[2], Integer.parseInt(input[3]));

            if (hashtable.search(input[0])==null){
                ArrayList<Lecture> lectures = new ArrayList<Lecture>();
                lectures.add(p);
                hashtable.insert(input[0], lectures);
            } else {
                SLLNode<MapEntry<String, ArrayList<Lecture>>> result = hashtable.search(input[0]);
                ArrayList<Lecture> lectures = result.element.value;
                lectures.add(p);
                Collections.sort(lectures, Collections.reverseOrder());
                hashtable.insert(input[0], lectures);
            }
        }

        String date=br.readLine();
        SLLNode<MapEntry<String, ArrayList<Lecture>>> tosearch = hashtable.search(date);
        if (tosearch!=null){
            System.out.println(tosearch.element.value.get(0).getTime()+" "+tosearch.element.value.get(0).getPlace()+" "+tosearch.element.value.get(0).getFee());
        } else {
            System.out.println("No offers");
        }
    }
}
