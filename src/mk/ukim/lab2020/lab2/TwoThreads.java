package mk.ukim.lab2020.lab2;

public class TwoThreads {
  /*  public static class Thread1 extends Thread {
        public void run() {
            System.out.println("A");
            System.out.println("B");
        }
    }

    public static class Thread2 extends Thread {
        public void run() {
            System.out.println("1");
            System.out.println("2");
        }
    }*/

    public static void main(String[] args) {
        new Thread(new ThreadAB("str1","str2")).start();
        new Thread(new ThreadAB("1","2")).start();
    }
    static class ThreadAB implements Runnable{
        private String str1;
        private String str2;

        public ThreadAB(String str1, String str2){
            this.str1 = str1;
            this.str2 = str2;
        }
        public void run(){
            System.out.println(str1);
            System.out.println(str2);
        }
    }
}