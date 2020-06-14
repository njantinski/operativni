package mk.ukim.lab2020.lab1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class modifiedInLast7Days {
    public static void printFilesModifiedInLast7Days(String path) throws IOException {
        File dir =  validateDir(path);
        ArrayList<File> filesModified = new ArrayList<>();

        getFilesModifiedRecursively(path,filesModified);

    }

    private static void getFilesModifiedRecursively(String path,ArrayList<File> files) throws IOException {
        File f = validateDir(path);
        File[] filesInDir = f.listFiles();

        long weekAgo = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(7);

        files.addAll(Arrays.stream(filesInDir).filter(fi -> fi.lastModified() > weekAgo).collect(Collectors.toList()));
        Arrays.stream(filesInDir).filter(fi -> fi.isDirectory()).forEach(fi -> {
            try {
                getFilesModifiedRecursively(fi.getAbsolutePath(),files);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private static File validateDir(String path) throws IOException {
        File f = new File(path);
        if(!f.isDirectory() || !f.canRead())
            throw new IOException();
        return f;
    }
}
