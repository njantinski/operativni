package mk.ukim.Threads2020.aud3_part2;

public class MyState {

    int items;
    int numberOfConsumers;

    public MyState(int numberOfConsumers){
        this.items =0;
        this.numberOfConsumers = numberOfConsumers;
    }

    public boolean isBufferEmtpy(){
        return this.items == 0;
    }

    public int getNumberOfConsumers() {
        return numberOfConsumers;
    }


    public synchronized void fillBuffer(){
        if(this.items != 0){
            System.err.println("GRESHKA");
            throw new RuntimeException("GRESHKA");
        }
        this.items = numberOfConsumers;
        System.out.println("FILL BUFFER");
    }

    public synchronized void decrementBuffer(){
        this.items--;
        if(items < 0){
            System.err.println("GRESHKA");
            throw new RuntimeException("GRESHKA");
        }

        System.out.println("DECREMENT");
    }

    public void getItemById(int id){
        System.out.println(String.format("Get item by id: %d ", id));
    }
}
