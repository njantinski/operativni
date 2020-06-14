package ThreadsTutorial.caveOfProg.thread4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
    private Random random = new Random();
    // zatoa mora da kreirame razlicni logovi za razlicnite metodi

    private Object lock1 = new Object();
    private Object lock2 = new Object();


    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    // so sinhroniziran go zemas logot na objektot
    // i ako edna nitka go izvrsuva treba da ceka
    // za da go dobie logot, odnosno da zavrsi
    // za da drugata nitka go zeme i pocne da go izvrsuva

    // problemot e sto ima eden log za worker objektot
    // i ako edna nitka go izvrsuva stage one treba da ceka nitkata
    // da zavrsi worker za da go zeme i da go izvrsuva stageTwo metodot
    // iako se nezavisni i ne pisuvaat na istata lista, povtorno treba
    // da cekaat bidejki dvete nitki se del od isti log

    // zatoa treba da napravime metodot da moze da se izvrsuva na druga nitka
    // bidejki e nezavisen od prviot i ne gi menuva vrednostite sto gi
    // koristi prviot metod

    public void stageOne() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt());
        }
    }


    public void stageTwo(){

        // ova znaci deka 2 threadovi mozat da go izvrsuvaat metodot
        // odednas, no ako nekoj go zaklucil procesot, togas
        // ne e dostapen za izvrsuvanje i treba da poceka dodeka ne zavrsi
        // drugiot proces
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt());
        }
    }

    public void process(){
        for(int i =0 ; i < 1000; i++){
            stageOne();
            stageTwo();
        }
    }


    public void main(){

        System.out.println("Starting... ");
        long start = System.currentTimeMillis();

        Thread t1 = new Thread(()-> process());
        Thread t2 = new Thread(()-> process());
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start));

        System.out.println("List 1: " + list1.size());
        System.out.println("List 2: " + list2.size());
    }
}
