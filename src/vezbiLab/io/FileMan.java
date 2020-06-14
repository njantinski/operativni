package vezbiLab.io;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

public interface FileMan {
    File getFileFromString(String file);
    File[] getFilesInFolder(File file) throws FileNotFoundException;
    void printFileNames(File file, PrintStream writer) throws FileNotFoundException;
    File[] getFilesFromString(String filePath) throws FileNotFoundException;
    String getAbsPath(String relPath) throws FileNotFoundException;
    long getFileSize(String file) throws FileNotFoundException;
    void printFilePermissions(File f, PrintStream writer) throws FileNotFoundException;
    void createNewFile(String file) throws FileExistsException, IOException;
    void createFolder(String folder) throws FileExistsException;
    File[] filterImagesFilesInDir(String dirPath) throws FileNotFoundException;
    void renameFile(File src, File dest) throws FileNotFoundException,FileExistsException;
    Date getLastModified(String file) throws FileNotFoundException;
    void filterImagesFilesInDirRec(File file, PrintStream out) throws FileNotFoundException;
    boolean deleteFolder(File folder) throws FileNotFoundException;

}
