package mk.ukim.lab2020.lab2;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;

/*

Да се имплементира класа FileScanner која што ќе се однесува како thread. Во класата FileScanner се чуваат податоци за :
 - патеката на директориумот што треба да се скенира - статичка променлива counter што ќе брои колку нишки од класата
 FileScanner ќе се креираат Во класата FileScanner да се имплементираа статички методот што ќе печати информации за
  некоја датотека од следниот формат:

dir: C:\Users\185026\Desktop\lab1 - reshenija 4096 (dir за директориуми, апсолутна патека и големина)

file: C:\Users\Stefan\Desktop\spisok.pdf 29198 (file за обични фајлови, апсолутна патека и големина)

Дополнително да се преоптовари методот run() од класата Thread, така што ќе печати информации за директориумот за којшто
 е повикан. Доколку во директориумот има други под директориуми, да се креира нова нишка од тип FileScanner што ќе ги
 прави истите работи како и претходно за фајловите/директориумите што се наоѓаат во тие директориуми (рекурзивно).

На крај да се испечати вредноста на counter-от, односно колку вкупно нишки биле креирани.  Користете го следниот почетен
 код.

 */
class FileScanner  extends Thread{

    private String fileToScan;
    //TODO: Initialize the start value of the counter
    private static Long counter = 0l;

    public FileScanner (String fileToScan) {
        this.fileToScan=fileToScan;
        //TODO: Increment the counter on every creation of FileScanner object

        synchronized(this){
            counter++;
        }
    }

    public static long getDirSize(File f){
        if(!f.isDirectory())
            return 0;

        long length = 0;
        File[] filesInDir = f.listFiles();
        for(File ff: filesInDir){
            if(ff.isDirectory()){
                getDirSize(ff);
            }
            else{
                length += ff.length();
            }
        }

        return length;
    }

    public static void printInfo(File file)  {

        /*
         * TODO: Print the info for the @argument File file, according to the requirement of the task
         * */

        if(!file.exists()){
            System.out.println("Ne postoi fajlot");
            return;
        }

        if(file.isDirectory()){
            long size = getDirSize(file);
            System.out.println("Dir: " + file.getAbsolutePath() + ", size: " + size);
        }
        else if(file.isFile()){
            System.out.println("File: " + file.getAbsolutePath() + ", size: " + file.length());
        }

    }

    public static Long getCounter () {
        return counter;
    }


    public void run() {

        //TODO Create object File with the absolute path fileToScan.
        File file = new File(fileToScan);

        //TODO Create a list of all the files that are in the directory file.
        File[] files = file.listFiles();


        HashSet<Thread> threads = new HashSet<>();
        for (File f : files) {

            /*
             * TODO If the File f is not a directory, print its info using the function printInfo(f)
             * */
            if(!f.isDirectory()){
                printInfo(f);
            }
            else {
                /*
                 * TODO If the File f is a directory, create a thread from type FileScanner and start it.
                 * */

                Thread t = new FileScanner(f.getAbsolutePath());
                threads.add(t);
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //TODO: wait for all the FileScanner-s to finish
        }

    }

    public static void main (String [] args) throws InterruptedException {
        String FILE_TO_SCAN = "C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\FilesIOTutorial";

        //TODO Construct a FileScanner object with the fileToScan = FILE_TO_SCAN
        FileScanner fileScanner = new FileScanner(FILE_TO_SCAN);

        //TODO Start the thread from type FileScanner
        fileScanner.start();
        //TODO wait for the fileScanner to finish
        fileScanner.join();
        System.out.println("Number of created threads: " + counter);
        //TODO print a message that displays the number of thread that were created


    }
}