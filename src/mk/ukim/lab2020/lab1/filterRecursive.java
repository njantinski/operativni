package mk.ukim.lab2020.lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 * Напишете Java програма која рекурзивно ги изменува сите документи
 * во даден именик и неговото поддрво, и ги пребарува сите `.txt` или `.out`
 * документи кои се поголеми од 1KB, a помали од 100KB. Патеката на директориумот
 * ја прима како влезен аргумент од командна линија. Апсолутната патека, на
 * документот кој го задоволува условот, ја печати  на екран.
 */
public class filterRecursive {

    public static void listRecursively(String path) throws FileNotFoundException {
        File dir = validateDir(path);
        Predicate<File> filter1 = f -> f.getAbsolutePath().endsWith(".txt") || f.getAbsolutePath().endsWith(".out");
        Predicate<File> filter2 = f -> f.length() > 1000 &&  f.length() < 1000 * 100;

        File[] filesInDir = dir.listFiles();

        for(File f : filesInDir){
            if(f.isDirectory())
                listRecursively(path);
            else{
                if(filter1.and(filter2).test(f))
                    System.out.println(f.getAbsolutePath());
            }
        }
    }
    private static File validateDir(String path) throws FileNotFoundException {
        File returnFile = new File(path);
        if(!returnFile.exists())
            throw new FileNotFoundException("File doesn't exist");
        if(!returnFile.isDirectory())
            throw new FileNotFoundException("File is not a directory!");
        return returnFile;
    }
}
