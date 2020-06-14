package ThreadsTutorial.caveOfProg.thread3;

public class sync2 {


    // mora da se osigurame deka koga eden proces ja procital promelnivata
    // i saka da ja promeni da ja blokirame za da ne moze nekoj drug
    // proces da pristapi do ovaa promenliva
    private int count = 0;


    // sekoj objekt vo java ima monitor lock / mutex i ako go
    // povikas sinhroniziraniot metod od objektot, moras da go zemes
    // lock na objektot pred da go povikas i samo 1 thread moze da
    // go zeme lock i ako eden thread go zeme lock, drug thread ne moze
    // vo isto vreme da go povika toj metod, tuku mora da ceka dodeka
    // prviot thread ne go oslobodi logot, odnosno ne zavrsi so negovata rabtoa
    public synchronized void increment(){
        count++;
    }

    public static void main(String[] args) {
        sync2 app = new sync2();
        try {
            app.doWork();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void doWork() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 10000; i++){
                increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 10000; i++){
                increment();
            }
        });

        // znaci ja printame vrednosta na count vo glavnata nitka
        // iako drugite 2 seuste se izvrsuvaat
        // taka da bez razlika na toa
        // dali zavrsile ili ne se printa prvicnata verzija na
        // count

        // zatoa treba da cekame dodeka ne zavrsat tie 2 nitki
        t1.start();
        t2.start();

        // join znaci ne prodolzuvaj so nitkata kojasto ja
        // povikala vtorata se dodeka povikanata nitka ne zavrsi
        t1.join();
        t2.join();


        // ova nema da raboti sekogas bidejki procesot moze da zastane
        // pred da se zgolemi count i vo sred proces da pocne drug proces
        // i vtoriot proces  go cita count kako edna vrednost i go vrakja kako
        // prvobitnata, a dodeka drugiot go procital so istata sostojba
        // pred vtoriot da go inkrementira i po inkrementacijata go vrakja
        // inkrementiran od pocetnata sostojba ne zemajki vo predvid deka i
        // vtoriot proces veke go inkrementiral i zatoa moze da dade pomala vrednost


        System.out.println("Count is: " + count);
    }
}


