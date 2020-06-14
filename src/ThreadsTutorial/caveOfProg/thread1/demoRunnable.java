package ThreadsTutorial.caveOfProg.thread1;

class Runnerce implements Runnable{

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            System.out.println("Hello " + i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class demoRunnable {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnerce());
        Thread t2 = new Thread(new Runnerce());

      t1.start();
      t2.start();
    }
}
