package mk.ukim.IO.zadaciWithStreams;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class JuneExamIO {
    public static void manage(String in, String out) throws IOException {
        File from = new File(in);
        if(!from.exists())
            throw new IOException("Not a directory");
        File to = new File(out);
        if(to.exists()){
            File[] files = to.listFiles();
            Arrays.stream(files).forEach(f -> f.delete());
        }
        File[] files = from.listFiles();

        PrintWriter pw = new PrintWriter(new FileWriter(new File("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\IO\\zadaciWithStreams\\resources\\writable-content.txt"),true));


        Arrays.stream(files).filter(f -> f.canWrite()).forEach(f -> {
            System.out.println("Posetuvam: " + f.getAbsolutePath());
            renameAndMove(f, out, f.getName());
        });

        Arrays.stream(files).filter(f -> !f.canWrite()).forEach(f -> {
            System.out.println("dopisuvam.. " + f.getAbsolutePath());
            pw.println(f.getAbsolutePath());
        });

        Arrays.stream(files).filter(f -> f.isHidden()).forEach(f -> {
            System.out.println("zbunet sum..." + f.getAbsolutePath());
            f.delete();

        });



    }
    public static void renameAndMove(File f, String parent, String newName){
        File parentFile = new File(parent);
        File newFile = new File(parentFile,newName);
        f.renameTo(newFile);
    }
}
