package FINKIAPSbook.queues.exercises;

import FINKIAPSbook.stacks.ArrayQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KolokviumskaNedela {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String vlez;

        ArrayQueue<String> siteAsistenti = new ArrayQueue<String>(100);
        ArrayQueue<String> predmeti = new ArrayQueue<String>(100);
        ArrayQueue<Integer> brPotrebni = new ArrayQueue<Integer>(100);
        ArrayQueue<String> otsutni = new ArrayQueue<String>(100);
        ArrayQueue<String> aktivniAsistenti = new ArrayQueue<String>(100);

        int i, j;
        int numAsistenti, numPredmeti, numOtsutni;

        numAsistenti = Integer.parseInt(br.readLine());
        for (i = 0; i<numAsistenti; i++){
            vlez = br.readLine();
            siteAsistenti.enqueue(vlez);
        }

        numPredmeti = Integer.parseInt(br.readLine());
        for (i = 0; i<numPredmeti; i++){
            vlez = br.readLine();
            int spaceIdx = vlez.indexOf(' ');
            String imePredmet = vlez.substring(0, spaceIdx);
            int kolicina = Integer.parseInt(vlez.substring(spaceIdx+1));
            predmeti.enqueue(imePredmet);
            brPotrebni.enqueue(kolicina);
        }

        numOtsutni = Integer.parseInt(br.readLine());
        for (i = 0; i<numOtsutni; i++){
            vlez = br.readLine();
            otsutni.enqueue(vlez);
        }

        while (!siteAsistenti.isEmpty()){
            String current = siteAsistenti.dequeue();
            boolean isOtsuten = false;

            int sz = otsutni.size();
            for (j=0; j<sz; j++){
                String o = otsutni.dequeue();
                if (o.equals(current)){
                    isOtsuten = true;
                }
                otsutni.enqueue(o);
            }
            if (!isOtsuten){
                aktivniAsistenti.enqueue(current);
            }
        }

        while (!predmeti.isEmpty()){
            String predmet = predmeti.dequeue();
            int b = brPotrebni.dequeue();

            System.out.println(predmet);
            System.out.println(b);

            for (i=0; i<b; i++){
                String asistent = aktivniAsistenti.dequeue();
                System.out.println(asistent);
                aktivniAsistenti.enqueue(asistent);
            }
        }
    }
}
