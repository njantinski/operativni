package mk.ukim.Threads2020.aud3;
// pocnuva od 15 min

import java.util.ArrayList;

// angliskata prezentacija
// aud 3

import java.util.List;

/*
Simulate a scenario where multiple concurrent threads enqueue and dequeue elements 
from one instance of the type BlockingQueue. Synchronize the access to enqueue
 and dequeue using a synchronization mechanism of your choice.
*/
public class BlockingQueue<T> {
 
    List<T> contents;
    int capacity;
 
    public BlockingQueue(int capacity) {
        contents = new ArrayList<>();
        this.capacity = capacity;
    }



    // the critical regions are enqueue and dequeue
    // we should synchronize them because they
    // access the same shared memory


    // concurently invoked methods
    public synchronized void enqueue(T item) throws InterruptedException {
        // should insert elements until the capacity is full

        while(contents.size() == capacity){
            wait();
        }
        // while instead of if, explained for the consumer
        // same here
      
            contents.add(item);
            // invokes all the threads which were
            // waiting for consuming, which were blocked
            // will be added to the ready state
            notifyAll();

        // what if the contents are full?
        // the producer should be blocked untill
        // the consumer removes item from the list
    }
    // consumer should wait untill
    // there are avaliable methods
    // for consuming, if there isnt any
    // the consumer should wait
 
    public synchronized T dequeue() throws InterruptedException {
        T item = null;
        while(contents.size() == 0){
            // ceka se dodeka
            // producerot ne go dodade
            // elementot vo nizata
            wait();
        }
        // problem: index out of bounds exception
        // coz all the waiting consumers will be woken up
        // and all of them will access the items

        // solution: while instead of if: if another thread removes
        // the item, so every thread should continuously check if
        // there are another threads that removed the 
        // item
        item = contents.remove(contents.size() - 1);
        // should notify all the producers that
        // the queue was decremented and that there is
        // place for more producing
        notifyAll();


        // difference between notify and notifyAll
        // notify informs only one thread to go from
        // sleeping to ready state
        // and notify All informs all of the threads to
        // go from sleeping to ready state

        return item;
    }
}