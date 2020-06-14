package mk.ukim.IO.zadaciRiste;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

public class PrintFilesPermission {
    public static void printFilesPermission(String folder){
        File f = new File(folder);
        System.out.println("Printing in folder " + f.getAbsolutePath());
        System.out.println("Files with only reading permission: ");
        File[] files = f.listFiles();
        Arrays.stream(files).filter(fos -> !fos.canWrite()).forEach(file -> System.out.println(file.getAbsolutePath()));

        System.out.println("Printing files with writting and reading ");
        Arrays.stream(files).filter(fos -> fos.canWrite()).forEach(file -> System.out.println(file.getAbsolutePath()));

        Arrays.stream(files).filter(fil -> fil.isDirectory()).forEach(fil -> printFilesPermission(fil.getAbsolutePath()));

    }

    public static void main(String[] args) {
        printFilesPermission("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni");

    }
}
