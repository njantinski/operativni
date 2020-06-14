package mk.ukim.Threads2020.aud2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sample {
    public static final Object monitor = new Object();
    public static final Lock lock = new ReentrantLock();
    public static final Semaphore semaphore = new Semaphore(1);

    
    public static void main(String[] args) throws InterruptedException {
        Incrementor incrementor = new Incrementor();
        ThreadExecutor thread1 = new ThreadExecutor("t1",incrementor);
        ThreadExecutor thread2 = new ThreadExecutor("t2",incrementor);

        

        thread1.start();
        thread2.start();

        // not safe
        // should be synchronized
        thread1.publicCountIncrement();
        thread2.publicCountIncrement();
        // not safe, should be synchronized on a
        // class level
        // and we use the monitor of the object monitor
        // and we lock it here and in the incrementor
        // class when we increment the public incrementor
        // we can use instead public static lock or
        // public static semaphore which will be the
        // same for 2 classes when incrementing the 
        // variable, we use single shared synchronized
        // methods and objects for synchronization
        synchronized(monitor){
        thread1.publicIncrement++;
        }
    

        thread1.join();
        thread2.join();
        int b = incrementor.getCount();
        System.out.println(b);
    }

}

// nasleduva od Thread i se ovverridnuva metodot run

class ThreadExecutor extends Thread{
    private String name;
    private Incrementor incrementor;


    public int publicIncrement;

    public ThreadExecutor(String name, Incrementor incrementor){
        this.name = name;
        this.incrementor = incrementor;
        this.publicIncrement = 0;
    }

    public void publicCountIncrement(){
        // lokalna promenliva
        // poedinecna za sekoja instanca
        // treba da se sinhronizira samo 
        // promenlivata
        // synchronized go zema monitorot na
        // instancata i e bezbedno bidejki
        // e zaklucena od taa sto ja zgolemuva
        synchronized(Sample.monitor){
        publicIncrement++;
        }
    }
    public void run(){
        for(int i = 0; i < 20000; i++){
            
            publicCountIncrement();
        
           incrementor.increment();
        }
    }

}



// komunikacija so 2 niski

class Incrementor{
    private int count;

    public void increment(){
        synchronized(this) {
            count++;
        } 


    }
    public int getCount(){
        return this.count;
    }

}