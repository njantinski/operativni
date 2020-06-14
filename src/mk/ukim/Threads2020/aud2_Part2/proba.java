package mk.ukim.Threads2020.aud2_Part2;

class Executor extends Thread{

    private String name;

    public Executor(String name){
        this.name = name + "\t";
    }
    @Override
    public void run(){
        for(int i = 0; i < 20; i++){
            System.out.println(name + i);
        }
    }
}

public class proba{

    public static void threadWithRunnable(){
        Thread t1 = new Thread(()->{
            System.out.println("Hello from runnable");
        });
    t1.start();
    }



    public static void main(String[] args) {
        Thread thread1 = new Executor("Thread 1");
        Thread thread2 = new Executor("Thread 2");
        // za da se kreira nova niska
        thread1.start();
        thread2.start();
        threadWithRunnable();
    }
}