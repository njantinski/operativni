package mk.ukim.Threads2020.aud3_part2;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Producer extends Thread {

    MyState myState;

    public  Producer(MyState myState){
        this.myState = myState;
    }

    @Override
    public void run(){
        IntStream.range(0,ProducerConsumerTester.NUMBER_OF_RUNS).forEach(i -> {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void execute() throws InterruptedException {
        // povikaj fillBuffer
        // samokoga e prazen bufferot
        // proveri dali e prazen baferot

        // ako e prazen, zakluci go baferot i napolni go
        // i otkluci go

        Locks.bufferEmpty.acquire();
        Locks.bufferLocked.acquire();
        myState.fillBuffer();
        Arrays.stream(Locks.items).forEach(s -> s.release());
        Locks.bufferLocked.release();
        
    }
}
