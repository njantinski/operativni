    package mk.ukim.lab2020.lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *Исфилтрирајте ги сите документи чија големина е поголема од 50KB во директориумот чија
 * патека се наоѓа во filePath. Резултатот прикажете
 *  го на стандарден излез.
 * Користете ги готовите класи од библиотеката Java IO. Целосното решение (Java кодот) на задачата внесете го како одговор.
 */
public class filterDocs {
    public static File[] filterDocs(String path) throws FileNotFoundException {
        File f = validateDir(path);

        File[] filesInDir = f.listFiles();

        List<Object> lis =  Arrays.stream(filesInDir)
                .filter(e -> !e.isDirectory())
                .filter(e -> e.length() > 50*1000)
                .collect(Collectors.toList());
        File[] returnFiles = new File[lis.size()];
        for(int i = 0; i < returnFiles.length;i++)
            returnFiles[i] = (File)lis.get(i);

        return returnFiles;


    }

    private static File validateDir(String path) throws FileNotFoundException {
        File returnFile = new File(path);
        if(!returnFile.exists())
            throw new FileNotFoundException("File doesn't exist");
        if(!returnFile.isDirectory())
            throw new FileNotFoundException("File is not a directory!");
        return returnFile;
    }
}
