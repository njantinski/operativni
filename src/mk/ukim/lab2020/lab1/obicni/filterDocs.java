package mk.ukim.lab2020.lab1.obicni;

import java.io.BufferedReader;
import java.io.File;

/**
 *Исфилтрирајте ги сите документи чија големина е поголема од 50KB во директориумот чија
 * патека се наоѓа во filePath. Резултатот прикажете
 *  го на стандарден излез.
 * Користете ги готовите класи од библиотеката Java IO. Целосното решение
 * (Java кодот) на задачата внесете го како одговор.
 */
public class filterDocs {
    public static void filterDocs(String path){
        File f = new File(path);
        File[] filesInDir = f.listFiles(fi -> fi.length() > 50 * 1000 && !fi.isDirectory());
        for(File fil : filesInDir)
            System.out.println(fil.getAbsolutePath());
    }
}
