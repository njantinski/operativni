package FilesIOTutorial.vezba;

import java.io.File;
import java.io.FileFilter;

public class RecursiveFileAndFolderDemo  {
    public static void main(String[] args) {
        new RecursiveFileAndFolderDemo().listFolder(new File("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\FilesIOTutorial"));
    }

    void listFolder(File dir){
        File[] dirs = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        System.out.println("Directory of"  +  dir.getAbsolutePath());
        listFile(dir);
        for(File f : dirs){
            listFolder(f);
        }

    }

    private void listFile(File dir) {
        File[] files = dir.listFiles();
        for (File f: files){
            System.out.println(f.getName());
        }
    }
}
