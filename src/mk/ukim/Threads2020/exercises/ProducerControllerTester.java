package mk.ukim.Threads2020.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class ProducerControllerTester {
    public static int numRun = 500;

    static Semaphore accessBuffer;
    static Semaphore lock;
    static Semaphore controlBuffer;
    static int numChecks = 0;

    public static void init(){
        accessBuffer = new Semaphore(1);
        lock = new Semaphore(1);
        controlBuffer = new Semaphore(10);
    }

    public static class Buffer{
        public void produce(){
            System.out.println("Producer is producing...");
        }
        public void check(){
            System.out.println("Controler is checking...");
        }
    }
    public static class Producer extends Thread{
        private final Buffer buffer;

        public Producer(Buffer b){
            this.buffer= b;
        }


        public void execute() throws InterruptedException {
            accessBuffer.acquire();
            this.buffer.produce();
            accessBuffer.release();
        }

        public void run(){
            IntStream.range(0,numRun).forEach(i -> {
                try {
                    execute();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }


    public static class Controler extends Thread{
        private final Buffer buffer;

        public Controler(Buffer b){
            this.buffer = b;
        }

        public void execute() throws InterruptedException{
            lock.acquire();
            if(numChecks == 0){
                accessBuffer.acquire();
            }
            numChecks++;
            lock.release();

            controlBuffer.acquire();
            this.buffer.check();
            lock.acquire();
            numChecks--;
            controlBuffer.release();


            if(numChecks == 0){
                accessBuffer.release();
            }
            lock.release();
        }

        public void run(){
            IntStream.range(0,numRun).forEach(i -> {
                try {
                    execute();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();
        Producer p = new Producer(buffer);
        List<Controler> controllers = new ArrayList<>();
        init();
        for (int i = 0; i < 10; i++) {
            controllers.add(new Controler(buffer));
        }
        p.start();

        for (int i = 0; i < 10; i++) {
            controllers.get(i).start(); controllers.get(i).join();
        }

    }

}



