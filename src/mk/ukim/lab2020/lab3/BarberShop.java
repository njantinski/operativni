package mk.ukim.lab2020.lab3;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class BarberShop {

    static int waitingCustomers = 0;
    static Semaphore barberWorking = new Semaphore(0);
    static Semaphore custWaiting = new Semaphore(5);
    static Semaphore syncCustChange = new Semaphore(1);


    static void customerComesIn() throws InterruptedException {
        // TODO: 3/29/20 Synchronize this method, invoked by a Customer thread

        custWaiting.acquire();
        syncCustChange.acquire();

        if(waitingCustomers == 0){
            barberWorking.release();
        }

        waitingCustomers++;
        System.out.println("Customer came in...");
        syncCustChange.release();




    }

    static void barber() throws InterruptedException {
        // TODO: 3/29/20 Synchronize this method, invoked by Barber thread
            barberWorking.acquire();
            syncCustChange.acquire();


            waitingCustomers--;
            System.out.println("Barber working on customer...");
            if(waitingCustomers > 0){
                barberWorking.release();
            }
            syncCustChange.release();

            custWaiting.release();

    }

    public static void main(String[] args) throws InterruptedException {
        // TODO: 3/29/20 Synchronize the scenario
        Set<Thread> customers = new HashSet<>();
        IntStream.range(0,100).forEach(t -> customers.add(new Thread(() -> {
            try {
                customerComesIn();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })));

        Thread barber = new Thread(() ->{
            IntStream.range(0,100).forEach(i ->
            {
                try {
                    barber();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });


        customers.forEach(t -> t.start());
        barber.start();
        barber.join();
        customers.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}