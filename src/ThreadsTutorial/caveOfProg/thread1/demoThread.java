package ThreadsTutorial.caveOfProg.thread1;

// run e proccesot koj se izvrsuva
// vo nitkata go stavame kodot
// kojsto sakame da se izvrsi
class Runner extends Thread{
    public void run(){

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


public class demoThread {
    public static void main(String[] args) {

        Runner runner1 = new Runner();
        runner1.start();

        Runner runner2 = new Runner();
        runner2.start();


    }
}
