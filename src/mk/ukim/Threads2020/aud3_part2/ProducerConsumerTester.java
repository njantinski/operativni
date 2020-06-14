package mk.ukim.Threads2020.aud3_part2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class ProducerConsumerTester {
    public static final int NUMBER_OF_RUNS = 10;


    public static void main(String[] args) {
        int numberOfConsumers = 12;
        MyState myState = new MyState(numberOfConsumers);
        init(numberOfConsumers);
        Set<Thread> threads = new HashSet<>();
        IntStream.range(0, numberOfConsumers)
                .forEach(i -> threads.add(new Consumer(myState,i)));
        Producer producer = new Producer(myState);
        threads.add(producer);
        threads.forEach(Thread::start);

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void init(int numCons){
        Locks.bufferEmpty = new Semaphore(1);
        Locks.bufferLocked = new Semaphore(1);
        Locks.items = new Semaphore[numCons];


        IntStream.range(0,numCons).forEach(i -> Locks.items[i] = new Semaphore(0));
    }

}

class Locks{
    public static Semaphore bufferEmpty;
    public static Semaphore bufferLocked;
    public static Semaphore[] items;



}