package ThreadsTutorial.colinYoutube;

public class Fortune extends Thread {

    public void run(){
        while(true){
            System.out.println("Good things will come to your way......");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
