package mk.ukim.lab2020.lab1.obicni;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Напишете Java програма која рекурзивно ги изменува сите документи
 * во даден именик и неговото поддрво, и ги пребарува сите `.txt` или `.out`
 * документи кои се поголеми од 1KB, a помали од 100KB. Патеката на директориумот
 * ја прима како влезен аргумент од командна линија. Апсолутната патека, на
 * документот кој го задоволува условот, ја печати  на екран.
 */
public class filterRecursive {
    public static void main(String[] args) throws IOException {
        if(args == null)
            throw new IOException("No args entered");
        File f = new File(args[0]);
        printRecursive(f);
    }

    private static void printRecursive(File dir) {
        File[] filesInDir = dir.listFiles();
        for(File f: filesInDir){
            if(f.isDirectory())
                printRecursive(f);
            else{
                if((f.length() > 1000 && f.length() < 1000 * 100)
                        && (f.getAbsolutePath().endsWith(".txt") || f.getAbsolutePath().endsWith(".out")))
                    System.out.println(f.getAbsolutePath());
            }
        }

    }
}
