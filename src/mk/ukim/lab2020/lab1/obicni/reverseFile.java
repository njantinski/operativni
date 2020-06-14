package mk.ukim.lab2020.lab1.obicni;

import java.io.*;
import java.util.Random;

public class reverseFile {
    public static void writeToFile(String from, String to) throws IOException {
        File fromFile = validateFile(from);
        File toFile = validateFile(to);

        writeReverse(fromFile, toFile);
    }

    private static void writeReverse(File fromFile, File toFile) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(fromFile.getAbsolutePath(),"r");
                OutputStream os = new FileOutputStream(toFile);
        ) {
            long rafLength = raf.length() - 1;
            for(int i = (int) rafLength; i >= 0 ; i--){
                raf.seek(i);
                os.write(raf.read());
            }

        }

    }

    private static File validateFile(String from) throws FileNotFoundException {
        File f = new File(from);
        if(!f.isFile() || !f.getAbsolutePath().endsWith(".txt"))
            throw new FileNotFoundException("File is not a txt file! ");
        return f;
    }

    public static void main(String[] args) throws IOException {
        writeToFile("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\lab2020\\lab1\\obicni\\os.txt"
        ,"C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\lab2020\\lab1\\obicni\\so.txt");
    }
}
