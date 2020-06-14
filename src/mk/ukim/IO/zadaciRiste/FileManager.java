package mk.ukim.IO.zadaciRiste;

import mk.ukim.IO.zadaciRiste.Exceptions.NotDirectoryException;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.FileAlreadyExistsException;

public interface FileManager {

    String workingDirectoryAbsolutePath();

    File workingDirectoryAsFile();

    File parentDirectory(File file);

    boolean deleteDirectoryRecursively(File directory) throws NotDirectoryException;

    boolean createDirectoryOnlyIfParentExists(String path);

    boolean createDirectoryWithItsParents(String path);

    boolean createFile(String path) throws IOException;

    boolean renameFile(File file, String newName) throws NotDirectoryException, FileAlreadyExistsException;

    boolean moveFile(File file, String newParent) throws NotDirectoryException, FileAlreadyExistsException;

    boolean moveAndRenameFile(File file, String newParent, String newName) throws NotDirectoryException, FileAlreadyExistsException;

    void printFilteredDirectoryContentRecursively(
            File directory,
            FilenameFilter filter,
            FileInfoPrinter printer,
            PrintStream out
    );

}