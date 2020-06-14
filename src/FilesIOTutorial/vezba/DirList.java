package FilesIOTutorial.vezba;

import java.io.File;
import java.io.FileFilter;


public class DirList {
    public static void listTextFiles(File file){
        File[] dirs = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        System.out.println("The text files in directory: " + file.getAbsolutePath());
        listTxtFiles(file);
        for(File f: dirs){
            listTextFiles(f);
        }

    }

    private static void listTxtFiles(File dirs) {
        File[] txtFiles = dirs.listFiles();
        for(File f: txtFiles)
            System.out.println(f.getName());
    }

    public static void main(String[] args) {
        listTextFiles(new File("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni"));
    }

}
