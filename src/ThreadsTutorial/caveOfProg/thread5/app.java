package ThreadsTutorial.caveOfProg.thread5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{
    private int id;

    public Processor(int id){
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Starting..." + this.id);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Ending..." + this.id);
    }
}


public class app {
    public static void main(String[] args) {
        //thread pool e broj na rabotnici vo fabrika (primer)
        // i imaaat povekje zadaci sto treba da gi zavrsat
        // i sakame sekoj rabotnik da procesira baranje i koga ke
        // zavrsi so edna rabota da pocne da procesira nova rabota
        // i sega sakame na executorot da mu dademe taskovi
        // za da gi raboti
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for(int i = 0; i < 5; i++){
            executor.submit(new Processor(i));
        }
        // go sprecuvame executorot da prima novi taskovi
        // ke ceka da zavrsat site threadovi pa da se iskluci
        executor.shutdown();

        System.out.println("All tasks submitted");

        try {
            executor.awaitTermination(2, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks completed");

    }

}
