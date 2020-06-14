package ThreadsTutorial.caveOfProg.thread2;

// sinhronizacija na procesi


import java.util.Scanner;

class Processor extends Thread{
    // prevencija da se keshira vrednosta na running vo true
    // i nikogas povekje da ne se proveruva bidejki run nikogas
    // ne go povikuva metodot shutdown, tuku toj se povikuva od druga
    // nitka
    private volatile boolean running = true;


    public void run(){

        while(running){
            System.out.println("Hello ");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void shutDown(){
        running = false;
    }
}

public class Syn1 {
    public static void main(String[] args) {
        Processor p1 = new Processor();
        p1.start();
        System.out.println("Press return to stop");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        p1.shutDown();


    }

}
