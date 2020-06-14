package mk.ukim.lab2020.lab1.obicni;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Пронајдете го најголемиот документ во директориумот
 * чија патека се наоѓа во filepath. Rezultatot prikazete
 * go na standarden izlez
 *
 */
public class biggestDoc {

    public static void biggestDocsPrint(File filePath) throws IOException {
        // proveruva dali e direktorium
        if(!validateDir(filePath))
            throw new FileNotFoundException("The file is not a directory");
        // go povikuva metodot za naogjanje na najgolemiot dokument
        // sto ke go vrati vo biggest file
        File biggestFile = findBiggest(filePath);

        // go printa najgolemiot dokument
        System.out.println(biggestFile.getAbsolutePath());

    }

    private static File findBiggest(File filePath) throws IOException {
        // se filtriraat so ova dokumentite vo
        // direktiroiumot, ekstenzija doc, valjda nz dali e vaka
        // ili kako treba posto nemaat dadeno ekstenzija
        File[] filesInDir = filePath.listFiles(f -> f.getAbsolutePath().endsWith(".doc"));

        if(filesInDir.length == 0)
            throw new IOException("The folder is empty");
        File test = filesInDir[0];
        for(int i = 1; i < filesInDir.length;i++) {
            if (test.length() < filesInDir[i].length())
                test = filesInDir[i];
        }
        return test;

    }

    private static boolean validateDir(File filePath) {
        return filePath.isDirectory();
    }


}
