package mk.ukim.Threads2020.aud2_Part2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Incrementor_2 {
    private int count;
    private Lock lock;
    // semaforot bara kako parametar
    // inicijalen broj na dozvoli
    // za da mozat da gi zemat niskite
    // da mozat da pristapat do kriticen
    // region i da izvrsat rabota pa
    // da go oslobodat
    private Semaphore s1 = new Semaphore(1);

    public Incrementor_2() {
        this.count = 0;
        lock = new ReentrantLock();

    }

    public void increment() throws InterruptedException {
        // Thread.sleep(1);
        // increment treba da se sinhronizira, da bide
        // rezultatot predvidliv, pristapot da e
        // sinhroniziran

        // dodadi synchronized, monitor na klasata
        // sto e mutex za taa klasa, za toj metod
        // i ako e zaklucen, ne moze druga niska
        // da go povika ovoj metodot za instancata
        // od ovaa klasa
        // synhronized(this){}
        // za da go simhroniziras samo inkrementiranjeto
        // na promenlivata, a ne na celata metoda
        // synchronized(this){
        // count++;
        // }

        // lock e mutex i e lokalna promenliva
        // na sekoja instanca, ako sakame na nivo
        // na klasa lock ja pravime static
        lock.lock();
        count++;
        lock.unlock();

    }

    public void semaphoreIncrement() {
        // dokolku e slobodna dozvolata, ja zema
        // dozvolata i izvrsuva vo kriticniot region
        // ako ne moze da ja zeme dozvolata, togas
        // procesot blokira se dodeka nekoj drug ne
        // go oslobodi semaforot i toj ke moze da go
        // zavzeme
        try {
            s1.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        count++;

        // se osloboduva dozvolata za semaforot
        s1.release();
    }
    public int getCount(){
        return this.count;
    }
}
