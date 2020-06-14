package mk.ukim.Threads2020.exercises.SeptemberTribeDinner;

import mk.ukim.Threads2020.ProblemExecution;
import mk.ukim.Threads2020.TemplateThread;

import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.Semaphore;



public class SeptemberTribeDinnerSynchronization {

    static Semaphore waiting,table, canCook, checkPot;
    static int numWaiting;

    public static void init() {
       table = new Semaphore(4);
       canCook = new Semaphore(0);
       checkPot = new Semaphore(1);
       numWaiting = 0;
       waiting = new Semaphore(0);


    }

    public static class TribeMember extends TemplateThread {

        public TribeMember(int numRuns) {
            super(numRuns);
        }

        @Override
        public void execute() throws InterruptedException {
                checkPot.acquire();
                if(state.isPotEmpty()){
                    canCook.release();
                    waiting.acquire();
                }

                state.fillPlate();
                checkPot.release();

                table.acquire();
                state.eat();
                table.release();
        }

    }

    public static class Chef extends TemplateThread {

        public Chef(int numRuns) {
            super(numRuns);
        }

        @Override
        public void execute() throws InterruptedException {
            canCook.acquire();
            state.cook();
            waiting.release();

        }

    }

    static SeptemberTribeDinnerState state = new SeptemberTribeDinnerState();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            run();
        }
    }

    public static void run() {
        try {
            int numRuns = 1;
            int numIterations = 150;

            HashSet<Thread> threads = new HashSet<Thread>();

            for (int i = 0; i < numIterations; i++) {
                TribeMember h = new TribeMember(numRuns);
                threads.add(h);
            }

            Chef chef = new Chef(10);
            threads.add(chef);

            init();

            ProblemExecution.start(threads, state);
            System.out.println(new Date().getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}