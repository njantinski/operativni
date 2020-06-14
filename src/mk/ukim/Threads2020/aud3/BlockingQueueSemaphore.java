package mk.ukim.Threads2020.aud3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class BlockingQueueSemaphore<T> {
    List<T> contents;
    int capacity;
    // how to allow multiple producers and consumers
    // to call the method and synchronize only the
    // critical region
    private final Semaphore producerSemaphore = new Semaphore(100); 
    private final Semaphore consumerSemaphore = new Semaphore(100);

    // acting like a single lock
    private final Semaphore synchronizedSemaphore = new Semaphore(1);


    public BlockingQueueSemaphore(int capacity) {
        contents = new ArrayList<>();
        this.capacity = capacity;
    }

    public void enqueue(T item) throws InterruptedException {
        producerSemaphore.acquire();

        synchronizedSemaphore.acquire();


        while(contents.size() == capacity){
            synchronizedSemaphore.release();
            Thread.sleep(10);

            synchronizedSemaphore.acquire();
        }
        contents.add(item);

        synchronizedSemaphore.release();

        consumerSemaphore.release();
             
    }

    public T dequeue() throws InterruptedException {
       // needs to be syncrhonized
        // this isnt good, because 
        
       T item = null;
       consumerSemaphore.acquire();
        // block untill an item is avaliable;

        
       synchronizedSemaphore.acquire();
       while(contents.size() == 0){
           synchronizedSemaphore.release();
           // forced switch
           Thread.sleep(10);
            // continues here
            // we need to again acquire the
            // semaphore in order to perform
            // the check
           synchronizedSemaphore.acquire();
       }
       item = contents.remove(contents.size() - 1);

       synchronizedSemaphore.release();
       producerSemaphore.release();
        return item;
    }
}
