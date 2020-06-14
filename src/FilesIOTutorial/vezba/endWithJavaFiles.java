package FilesIOTutorial.vezba;

import java.io.File;
import java.io.FileFilter;


public class endWithJavaFiles {
    public static void ListFilesJava(File path){
        File[] dirs = path.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        System.out.println("Directory: " + path.getAbsolutePath());
        listJavaFiles(path);
        for(File f: dirs){
            ListFilesJava(f);
        }
    }



    private static void listJavaFiles(File path) {
        File[] javaFiles = path.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getAbsolutePath().endsWith(".java");
            }
        });
        for(File f: javaFiles)
            System.out.println(f.getName());
    }

    public static void main(String[] args) {
        ListFilesJava(new File("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src"));
    }
}
