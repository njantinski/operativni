package mk.ukim.lab2020.lab3.H2O;

import java.util.concurrent.Semaphore;

class H2OMachine {

    String[] molecule;
    int count;
     Semaphore OStart;
     Semaphore HStart;
     Semaphore OHere;
     Semaphore HHere;
     Semaphore ready;




    public H2OMachine() {
        molecule = new String[3];
        count = 0;

        OStart = new Semaphore(1);
        HStart = new Semaphore(2);
        OHere = new Semaphore(0);
        HHere = new Semaphore(0);
        ready = new Semaphore(0);

    }

    public void hydrogen() throws InterruptedException {
        // TODO: 3/29/20 synchronized logic here
        HStart.acquire();
        OHere.acquire();
        HHere.release();

        ready.acquire();



        System.out.println("The molecule is formed");
        HStart.release();
    }

    public void oxygen() throws InterruptedException {
        // TODO: 3/29/20 synchronized logic here
        OStart.acquire();
        OHere.release(2);

        HHere.acquire(2);
        ready.release(2);


        System.out.println("The molecule is formed");

        OStart.release();
    }
}
class H2OThread extends Thread {

    H2OMachine molecule;
    String atom;

    public H2OThread(H2OMachine molecule, String atom){
        this.molecule = molecule;
        this.atom = atom;
    }

    public void run() {
        if ("H".equals(atom)) {
            try {
                molecule.hydrogen();
            }
            catch (Exception e) {
            }
        }
        else if ("O".equals(atom)) {
            try {
                molecule.oxygen();
            }
            catch (Exception e) {
            }
        }
    }
}

public class Main
{
    public static void main(String[] args) throws InterruptedException {

        // TODO: 3/29/20 Simulate with multiple scenarios
        H2OMachine molecule = new H2OMachine();

        Thread t1 = new H2OThread(molecule,"H");
        Thread t2 = new H2OThread(molecule,"O");
        Thread t3 = new H2OThread(molecule,"H");
        Thread t4 = new H2OThread(molecule,"O");
        Thread t5 = new H2OThread(molecule, "H");
        Thread t6 = new H2OThread(molecule, "H");

        t2.start();
        t1.start();
        t4.start();
        t3.start();
        t5.start();
        t6.start();

    }
}