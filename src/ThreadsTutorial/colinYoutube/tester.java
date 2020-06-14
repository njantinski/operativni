package ThreadsTutorial.colinYoutube;


// nitkite nasleduvaat od klasata thread
// koga ke nasledis od ovaa klasa
// znaes deka ima run method i deka
// moze da se izvrsuva kako nitka

import java.util.Scanner;

public class tester {
    public static void main(String[] args) {

        // programava raboti na glavnata nitka
        Scanner sc = new Scanner(System.in);

        // se ragja threadot

        Thread cookie1 =
                new Thread(
                        new FortuneCookie("You will always ", "Travel to interesting places...", 2000));
        Thread cookie2 =
                new Thread(
                        new FortuneCookie("Never expect ", " lots of money out thin air....", 2200));
        cookie1.start();
        cookie2.start();

        cookie1.setPriority(Thread.MAX_PRIORITY);
        cookie2.setPriority(Thread.MIN_PRIORITY);


        try {
            // glavnata nitka go ceka cookie1 da zavrsi
            cookie1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Fortune myFortuneThread = new Fortune();

        myFortuneThread.setDaemon(false);
        // ne se povikuva run metodot, tuku se povikuva start
        // bidejki procesotorot kontrolira koga moze da zapocne
        // taa nitka da raboti, a ne nie, so start metodot go
        // stavame vo podgotvena sostojba i procesot ceka
        // da bide zapocnat

        // go stava threadot vo ready sostojba
        // podgotven za procesorsko vreme

        myFortuneThread.start();
        // ne se povikuva run bidejki go vikame na main nitkata
        // bidejki ne si pocnal nitka kojasto se natprevaruva za
        // procesorsko vreme, tuku isforsirano si i go dal toa
        // procesorsko vreme


        // tuka zastanuva programata koga ke baras vlez
        // se stoi, procesot i nitkata stojat i nisto ne
        // se izvrsuva
        sc.next();
        System.out.println("Input request was satisfied in main.");
        try {

            // dolzinata na milisekundi na kojasto sakame da ja
            // zaspieme nitkata
            Thread.sleep(6000);

            // koga e vo sleep, ne raboti tuku odmara
            // koga ke zavrsi vremeto, nitkata odi vo ready
            // sostojba i ceka da dobie procesorsko vreme
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("i slept for 6 seconds");
    }
}




