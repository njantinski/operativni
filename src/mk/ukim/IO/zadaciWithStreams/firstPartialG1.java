package mk.ukim.IO.zadaciWithStreams;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class firstPartialG1 {
    public static void moveWrittableFilesStreams(String in, String out) throws IOException {
        File fromFile = new File(in);
        File toFile = new File(out);

        if(!fromFile.exists())
            throw new IOException("File does not exist exception");
        if(!fromFile.isDirectory()){
            throw new IOException("File is not a directory!");
        }

        if(!toFile.exists())
            toFile.mkdirs();



        Arrays.stream(fromFile.listFiles()).filter(f -> f.canWrite() /*&& f.getAbsolutePath()
                .endsWith(".txt")*/).collect(Collectors.toList()).
                forEach(f -> moveAndRenameFile(f,out,f.getName()));
    }

    public static void moveAndRenameFile(File f, String newParent, String newName) {

        File parent = new File(newParent);
        File newNameFile = new File(newParent, newName);
        f.renameTo(newNameFile);
    }

    public static void main(String[] args) throws IOException {
        moveWrittableFilesStreams("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\IO\\zadaciWithStreams\\kopiranjeOd",
               "C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\IO\\zadaciWithStreams\\kopiranjeVo" );


    }
}
