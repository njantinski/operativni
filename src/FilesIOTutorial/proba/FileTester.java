package FilesIOTutorial.proba;


import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class FileTester {
    public static void main(String[] args) {
        // go zema momentalniot pat
        File path = new File(".");
        if(args.length == 0){
            // proveruva dali sme vnele filter argument, lista se ako ne
            listFilesRecursive(path);
        }
        else{
            // gi lista samo isfiltriranite podatoci
            // so filterot sto sme go vnele
            listFilesRecursive(path,args[0]);
        }
    }

    private static void listFilesRecursive(File path) {
        File[] dirs;
        // kreirame niza od fajlovi

        // filtrirame vo nizata da gi stavime
        // site fajlovi sto se direktoriumi
        dirs = path.listFiles(new FileFilter() {
            public boolean accept(File dir) {
                return dir.isDirectory();
            }
        });

        // go printame momentalniot direktorium
        System.out.println("Directory: " + path.getAbsolutePath());
        // gi printame site fajlovi vo momentalniot direktorium
        listFiles(path);
        // ja povikuvame funkcijata za izlistuvanje na site fajlovi
        // sto gi imame od pocetok
        for(File f: dirs){
            listFilesRecursive(f);
        }
    }
    private static void listFilesRecursive(File path, String filter){
        // kreirame niza od fajlovi
        File[] dirs;


        // filtrirame vo nizata da gi stavime
        // site fajlovi sto se direktoriumi
        dirs = path.listFiles(new FileFilter(){
            public boolean accept(File dir){
                return dir.isDirectory();
            }
        });

        // go printame imeto na direktoriumot
        System.out.println("Directory: " + path.getAbsolutePath());

        // go predavame filterot za listanje i momentalniot direktorium vo
        // funkcijata za listanje so filter
        listFilesFilter(path,filter);
        for(File f: dirs){
            listFilesRecursive(f, filter);
        }


    }

    private static void listFilesFilter(File path, String filter) {
        // kreirame filter za fajlovi odnosno go
        // implementirame funkciskiot interfejs
        // preku objektot textFilter

        // tekst filter go prima momentalniot direktorium i imeto na
        // sekoj fajl i vrakja vrz osnova na primeniot filter dali
        // go sodrzi toj filter ili ne
       FilenameFilter textFilter = (dir, name) -> name.indexOf(filter) != -1;

       String[] files = path.list(textFilter);
       for(String f : files){
           System.out.println(f);
       }
    }

    private static void listFiles(File path) {
        // gi stafa site fajlovi vo edna niza
        // od fajlovi
        File[] files = path.listFiles();

        for(File f: files){
            System.out.println(f.getName());
        }

    }

}


