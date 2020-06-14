package mk.ukim.lab2020.lab3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class Singleton {

    private static volatile Singleton singleton;
    private static final Semaphore singletonSemaphore = new Semaphore(1);

    private Singleton() {

    }

    public static Singleton getInstance() throws InterruptedException {
        // TODO: 3/29/20 Synchronize this
        singletonSemaphore.acquire();
        singleton = new Singleton();

        return singleton;
    }

    public static void main(String[] args) {
        // TODO: 3/29/20 Simulate the scenario when multiple threads call the method getInstance
        List<Singleton> singletons = new ArrayList<>();
        Set<Thread> threads = new HashSet<>();
        IntStream.range(0,100).forEach(i -> threads.add(new Thread(() -> {
            try {
                singletons.add(getInstance());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })));
        threads.forEach(Thread::start);

        threads.forEach(t -> {
            try {
                t.join(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Instances of singleton class: " + singletons.size());


    }


}
