package mk.ukim.Threads2020.aud3_part2;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class SyncTester {
    public static void main(String[] args) {
        Set<ExampleThread> threads = new HashSet<>();

        int defaultValue = 0;
        IntegerWrapper wrapper = new IntegerWrapper(0);


        IntStream.range(0,100)
                .forEach(f -> threads.add(new ExampleThread(defaultValue,wrapper)));

        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        System.out.println();
        System.out.println();
        System.out.println(wrapper.getValue());


    }

}


class IntegerWrapper {
    private int value;
    //private Semaphore s = new Semaphore(1);
    public IntegerWrapper(int value){
        this.value = value;
    }

    public void increment(){
       this.value++;
    }
    public int getValue(){
        return this.value;
    }
}


class ExampleThread extends Thread{

    private int threadPrivateLocalField;

    public int threadPublicLocalField;
    // razlicni za sekoj objekt

    Lock lockPublic = new ReentrantLock();
    Semaphore semaphorePublicField = new Semaphore(1);
    Object mutex = new Object();

    static Object staticMonitor = new Object();
    static Semaphore staticSemaphore = new Semaphore(1);
    static Semaphore SemaphoreIntegerWrapperGlobal = new Semaphore(1);


    private static int threadStaticField;

    private IntegerWrapper wrapper;


    public ExampleThread(int init, IntegerWrapper wrapper){
        this.threadPrivateLocalField = init;
        this.wrapper = wrapper;
    }
    public static void forcedSwitch(int milis){
        try{
            Thread.sleep(milis);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    private void incrementThreadPrivateField() {
        this.threadPrivateLocalField++;
        int localVar = this.threadPrivateLocalField;

        forcedSwitch(10);
        if(localVar != this.threadPrivateLocalField){
            System.err.println(String.format("Private missmatch %d", getId()));
        }
        else{
            System.out.println(String.format("Private value: %d, value: %d",threadPrivateLocalField, localVar));
        }
    }

    public void incrementThreadPublicField(){
        this.threadPublicLocalField++;
        int localVar = this.threadPublicLocalField;

        forcedSwitch(10);
        if(localVar != this.threadPublicLocalField){
            System.err.println(String.format("Public missmatch %d", getId()));
        }
        else{
            System.out.println(String.format("Public value: %d, value: %d",this.threadPublicLocalField, localVar));
        }


    }

    public void incrementThreadStaticField(){
        threadStaticField++;
        int localVar = threadStaticField;

        forcedSwitch(10);
        if(localVar != threadStaticField){
            System.err.println(String.format("Static missmatch %d", getId()));
        }
        else{
            System.out.println(String.format("Static value: %d, value: %d",threadStaticField, localVar));
        }

    }

    public void incrementWrapper(){
        this.wrapper.increment();

        int localVar = this.wrapper.getValue();
        forcedSwitch(10);


        if(localVar != this.wrapper.getValue()){
            System.err.println(String.format("wrapper-missmatch %d", getId()));
        }
        else{
            System.out.println(
                    String.format("Wrapper value: %d, value: %d",wrapper.getValue(), localVar));
        }
    }



    public void safeIncrementPublic(){
        // instancata od objektot e zaklucena
        // monitorot na ovoj objekt e zaklucen
        // isto i ako stavime mutex objektot
        // go zaklucuva spored objektot mutex
        // i za sekoj objekt ima 1 monitor i koga
        // e zaklucen, toj blok ne moze drug da go izvrsi
        // dokolku e zafaten
        synchronized (this) {
            this.incrementThreadPublicField();
     }
    }


    public void safeStaticIncrement()  {
        try {
            staticSemaphore.acquire();
            incrementThreadStaticField();
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            staticSemaphore.release();
        }

    }

    public void safeIncrementWrapper() throws InterruptedException {
        SemaphoreIntegerWrapperGlobal.acquire();
        if(wrapper.getValue() < 5){
            incrementWrapper();

        }
        SemaphoreIntegerWrapperGlobal.release();

    }

    public int getThreadPrivateLocalField() {
        return threadPrivateLocalField;
    }

    public int getThreadPublicLocalField() {
        return threadPublicLocalField;
    }

    public static int getThreadStaticField() {
        return threadStaticField;
    }

    public IntegerWrapper getWrapper() {
        return wrapper;
    }

    @Override
    public void run(){
        incrementThreadPrivateField();
        safeIncrementPublic();
        safeStaticIncrement();
        try {
            safeIncrementWrapper();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
