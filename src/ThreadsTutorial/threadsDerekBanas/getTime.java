package ThreadsTutorial.threadsDerekBanas;
import java.sql.Time;
import java.util.*;
import java.text.DateFormat;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import java.util.concurrent.TimeUnit.*;


public class getTime extends Thread{
    public void run(){

        Date rightNow;
        Locale currentLocale;
        DateFormat timeFormatter;
        DateFormat dateFormatter;
        String timeOutput;
        String dateOutput;

        for(int i = 1; i <= 20; i++){
            rightNow = new Date();
            currentLocale = new Locale("en");

            timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT, currentLocale);
            dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, currentLocale);

            timeOutput = timeFormatter.format(rightNow);
            dateOutput = dateFormatter.format(rightNow);
            System.out.println(timeOutput);
            System.out.println(dateOutput);
            System.out.println();

            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}

/*class Lesson17{
    public static void main(String[] args) {
         Thread getTime = new getTime();

         Runnable getMail = new getTheMail(10);
         Runnable getMailAgain = new getTheMail (20);
         getTime.start();
         Thread get1 = new Thread(getMail);
         Thread get2 = new Thread(getMailAgain);
         get1.start();
         get2.start();

    }
}*/

class getTheMail implements Runnable{
    private int startTime;

    public getTheMail(int startTime){
        this.startTime = startTime;
    }
    @Override
    public void run() {
        try{
            Thread.sleep(startTime * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Checking mail...");
    }
}

class CheckSystemTime implements Runnable{


    @Override
    public void run() {
        Date rightNow;
        Locale currentLocale;
        DateFormat timeFormatter;
        String timeOuput;

        rightNow = new Date();
        currentLocale = new Locale ("en");

        timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT, currentLocale);
        timeOuput = timeFormatter.format(rightNow);

        System.out.println("Time: " + timeOuput);
    }
}


class PerformSystemCheck implements Runnable{
    private String checkWhat;
    ReentrantLock lock = new ReentrantLock();

    public PerformSystemCheck(String checkWhat) {
        this.checkWhat = checkWhat;
    }

    @Override
    public void run() {
        lock.lock();

        System.out.println("Checkign: " + this.checkWhat);

        lock.unlock();
    }
}

class Lesson18{
    public static void main(String[] args) {
        addThreadsToPool();
    }

    private static void addThreadsToPool() {
        ScheduledThreadPoolExecutor eventPool = new ScheduledThreadPoolExecutor(5);

        eventPool.scheduleAtFixedRate(new CheckSystemTime(), 0, 2, TimeUnit.SECONDS);
        eventPool.scheduleAtFixedRate(new PerformSystemCheck("Mail"), 5, 5 , TimeUnit.SECONDS);
        eventPool.scheduleAtFixedRate(new PerformSystemCheck("Calendar"), 15, 15 , TimeUnit.SECONDS);

        System.out.println("Number of threads: " + Thread.activeCount());

        Thread[] listOfThreads = new Thread[Thread.activeCount()];

        Thread.enumerate(listOfThreads);
        for(Thread t : listOfThreads){
            System.out.println(t.getName());
        }

    }
}

































