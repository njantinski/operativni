package mk.ukim.Threads2020.aud3_part2;

import java.util.stream.IntStream;

public class Consumer extends Thread {

    int id;
    MyState myState;

    public Consumer(MyState myState, int id){
        this.myState = myState;
        this.id = id;
    }

    @Override
    public void run(){
        IntStream.range(0, ProducerConsumerTester.NUMBER_OF_RUNS).forEach(i -> {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void execute() throws InterruptedException {
        // proveri dali smeam da zemam
        // zakluci go delot za mojot item
        // zakluci go bafeort
        // zemi go ajtemot
        // namali go baferot
        // otkluci go baferot
        Locks.items[id].acquire();


        myState.getItemById(id);
        Locks.bufferLocked.acquire();
        myState.decrementBuffer();
        if(myState.isBufferEmtpy()){
            Locks.bufferEmpty.release();
        }
        Locks.bufferLocked.release();
    }
}
