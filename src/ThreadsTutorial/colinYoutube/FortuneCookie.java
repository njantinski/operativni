package ThreadsTutorial.colinYoutube;
// ova znaci deka moze da raboti
// deka ima run metod
public class FortuneCookie implements Runnable {

    private String first;
    private String last;
    private int sleepTime;


    public FortuneCookie(String first, String last, int sleepTime){
        this.first = first;
        this.last = last;
        this.sleepTime = sleepTime;
    }

    public String getFirst() {
        return first;
    }

    public void run(){

        while(true){
            System.out.print(first);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(last);
        }

    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }
}
