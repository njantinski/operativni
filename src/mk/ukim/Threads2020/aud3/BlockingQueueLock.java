package mk.ukim.Threads2020.aud3;
// 41 min aud3 

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueLock<T> {

    private final Lock lock= new ReentrantLock();

    List<T> contents;
    int capacity;

    public BlockingQueueLock(int capacity) {
        contents = new ArrayList<>();
        this.capacity = capacity;
    }

    public void enqueue(T item) {
       // needs to be synchronized
        while(true){

             lock.lock();   
            // critical region
            if(contents.size() < capacity){
                contents.add(item);
                lock.unlock();
                break;
            }
            lock.unlock();
        }
    }

    public T dequeue() {
       // needs to be syncrhonized

       T item = null;
       while(true){

            if(contents.size() > 0){
                lock.lock();
                item = contents.remove(contents.size() - 1);
                lock.unlock();
                break;
            }
            lock.unlock();
        }
        return item;
    }
}