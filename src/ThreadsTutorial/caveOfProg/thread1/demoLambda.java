package ThreadsTutorial.caveOfProg.thread1;

public class demoLambda {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            for(int i = 0; i < 10; i++){
                System.out.println("Hello " + i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(()->{
            for(int i = 0; i < 10; i++){
                System.out.println("Hello " + i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
