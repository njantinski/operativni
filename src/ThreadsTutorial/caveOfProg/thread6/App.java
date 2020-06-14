package ThreadsTutorial.caveOfProg.thread6;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable{

    private CountDownLatch latch;

    public Processor(CountDownLatch latch){
        this.latch = latch;
    }
    @Override
    public void run() {
        System.out.println("Started.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // sekogas koga ke se povika metodot
        // ke se namali za 1 vrednosta na latch
        // ova e thread safe klasa ne ti treba sinhroniziran
        latch.countDown();
    }
}



public class App {
    public static void main(String[] args) {
        // dozvoluva 1 ili povekje threadovi da cekaad dodeka dostigne 0
        // eden thread ili povekje moze da go namalat brojot


        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        // kreira 3 threada i dobiva 3 procesori
        // i vo glavnata nitka se stava latch.await
        // za da poceka dodeka countdown latch ne izbroi do 0
        for(int i = 0 ; i < 10; i++){
            executor.submit(new Processor(latch));
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed");
    }
}
