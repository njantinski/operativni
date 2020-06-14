package mk.ukim.Threads2020.exercises.MusicBand;

import mk.ukim.Threads2020.ProblemExecution;
import mk.ukim.Threads2020.TemplateThread;

import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.Semaphore;




public class MusicBand {


        static MusicBandState state = new MusicBandState();


        static int numsMusicians;

        static int doneMusicians;
        static Semaphore singers;
        static Semaphore guitarists;
        static Semaphore canPlay;
        static Semaphore canExit;
        static Semaphore lock;



        public static class GuitarPlayer extends TemplateThread {

            public GuitarPlayer(int numRuns) {
                super(numRuns);

            }

            @Override
            public void execute() throws InterruptedException {
                guitarists.acquire();
                lock.acquire();
                numsMusicians++;
                if(numsMusicians == 5){
                    canPlay.release(4);
                    numsMusicians = 0;
                    lock.release();
                }
                else{
                    lock.release();
                    canPlay.acquire();

                }
                state.play();
                lock.acquire();
                doneMusicians++;

                if(doneMusicians == 5){
                    state.evaluate();
                    canExit.release(5);
                    doneMusicians = 0;
                    lock.release();
                }
                else{
                    lock.release();
                }

                canExit.acquire();
                guitarists.release();

            }

        }

        public static class Singer extends TemplateThread {


            public Singer(int numRuns) {
                super(numRuns);

            }

            @Override
            public void execute() throws InterruptedException {
                singers.acquire();
                lock.acquire();
                numsMusicians++;
                if(numsMusicians == 5){
                    canPlay.release(5);
                    numsMusicians = 0;
                }

                lock.release();
                canPlay.acquire();
                state.play();
                lock.acquire();
                doneMusicians++;

                if(doneMusicians == 5){
                    state.evaluate();
                    canExit.release(5);
                    doneMusicians = 0;
                    lock.release();
                }
                else{
                    lock.release();
                }

                canExit.acquire();
                singers.release();

            }



        }

        public static void init() {
            numsMusicians = 0;
            doneMusicians = 0;
            canExit = new Semaphore(0);
            canPlay = new Semaphore(0);
            singers = new Semaphore(2);
            guitarists = new Semaphore(3);
            lock = new Semaphore(1);
        }

        public static void main(String[] args) {
            for (int i = 0; i < 10; i++) {
                run();
            }
        }

        public static void run() {
            try {
                Scanner s = new Scanner(System.in);
                int numRuns = 1;
                int numIterations = 100;
                s.close();

                HashSet<Thread> threads = new HashSet<Thread>();

                for (int i = 0; i < numIterations; i++) {
                    mk.ukim.Threads2020.exercises.MusicBand.MusicBand.Singer singer = new mk.ukim.Threads2020.exercises.MusicBand.MusicBand.Singer(numRuns);
                    threads.add(singer);
                    mk.ukim.Threads2020.exercises.MusicBand.MusicBand.GuitarPlayer gp = new mk.ukim.Threads2020.exercises.MusicBand.MusicBand.GuitarPlayer(numRuns);
                    threads.add(gp);
                    gp = new mk.ukim.Threads2020.exercises.MusicBand.MusicBand.GuitarPlayer(numRuns);
                    threads.add(gp);
                    singer = new mk.ukim.Threads2020.exercises.MusicBand.MusicBand.Singer(numRuns);
                    threads.add(singer);
                    gp = new mk.ukim.Threads2020.exercises.MusicBand.MusicBand.GuitarPlayer(numRuns);
                    threads.add(gp);
                }

                init();

                ProblemExecution.start(threads, state);
                System.out.println(new Date().getTime());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }
