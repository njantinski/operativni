package mk.ukim.Threads2020.aud2_Part2;

public class tester {
    public static void main(String[] args) 
        throws InterruptedException {


        Incrementor_2 incrementor = new Incrementor_2();
            // imaat pristap
            // do instancata inkrementor
            // kako da se sinhronizira na nivo
            // na site instanci od inkrementor
            // odnosno na nivo na klasa
            // 1) synchronized(Incrementor.class)
            // 2) static synchronized metod
             
        Thread t1 = new Exec("t1", incrementor);
        Thread t2 = new Exec("t2", incrementor);

        t1.start();
        t2.start();

        t1.join();
        t2.join();


        /*
        if(t1.isAlive() || t2.isAlive()){
            System.out.println("Dealock!");
            t1.interrupt();
            t2.interrupt();
        }
        */
        System.out.println(incrementor.getCount());

    }
}

class Exec extends Thread {
    // private String name;
    private Incrementor_2 incrementor;

    public Exec(String name, Incrementor_2 incr) {
        // this.name = name;
        this.incrementor = incr;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2000; i++) {
            try {
                incrementor.increment();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
}
}
