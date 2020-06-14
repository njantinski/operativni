package mk.ukim.Threads2020.aud3;

import java.util.HashSet;

public class BlockingQueueTester {
    public static void main(String[] args) {
        BlockingQueue<String> sharedQueue = new BlockingQueue<>(10);

        HashSet<Thread> threads = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Producer("Producer number: " + i, sharedQueue));
            threads.add(new Consumer("Consumer number: " + i, sharedQueue));
        }

        threads.forEach(t -> t.start());
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

}

class Producer extends Thread {
    private String name;
    private BlockingQueue<String> sharedQueue;

    public Producer(String name, BlockingQueue sharedQueue) {

        this.name = name;
        this.sharedQueue = sharedQueue;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("The producer" + name + " is producing...");
            try {
                sharedQueue.enqueue("Product" + i + " by " + name);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}

class Consumer extends Thread {
    private String name;
    private BlockingQueue<String> sharedQueue;

    public Consumer(String name, BlockingQueue sharedQueue) {
        this.name = name;
        this.sharedQueue = sharedQueue;
    }

    public void run() {
        for(int i = 0; i < 10; i++){
            System.out.println("Consumer " + i +" is trying to consume");
            String element = null;
        try {
            element = sharedQueue.dequeue();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         System.out.println(name + " is getting element: " + element);
        }
    }

}