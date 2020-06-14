package mk.ukim.IO.zadaciRiste.impl;

import mk.ukim.IO.zadaciRiste.Exceptions.NotDirectoryException;
import mk.ukim.IO.zadaciRiste.FileInfoPrinter;
import mk.ukim.IO.zadaciRiste.FileManager;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.FileAlreadyExistsException;
import java.util.Arrays;

public class FileManagerImplementation implements FileManager {
    @Override
    public String workingDirectoryAbsolutePath() {
        return new File(".").getAbsolutePath();
    }

    @Override
    public File workingDirectoryAsFile() {
        return new File(".");
    }

    @Override
    public File parentDirectory(File file) {
        File tmp = new File(file.getAbsolutePath());
        return tmp.getParentFile();

    }

    @Override
    public boolean deleteDirectoryRecursively(File directory) throws NotDirectoryException {
        if(!directory.isDirectory())
            throw new NotDirectoryException(directory);

        File[] filesInDir = directory.listFiles();
        Arrays.stream(filesInDir).filter(f -> !f.canWrite())
                .forEach(f -> System.out.println(f.getAbsolutePath() + " can not be deleted"));
        Arrays.stream(filesInDir).filter(f -> !f.isDirectory()).filter(f -> f.canWrite())
                .forEach(f ->{
                    System.out.println("Deleting " + f.getAbsolutePath());
                    f.delete();
                });

        Arrays.stream(filesInDir).filter(f -> f.isDirectory()).forEach(f -> {
            try {
                deleteDirectoryRecursively(f);
            } catch (NotDirectoryException e) {
                e.printStackTrace();
            }
        });

        return directory.delete();
    }

    @Override
    public boolean createDirectoryOnlyIfParentExists(String path) {
        return new File(path).mkdir();
    }

    @Override
    public boolean createDirectoryWithItsParents(String path) {
        return new File(path).mkdirs();
    }

    @Override
    public boolean createFile(String path) throws IOException {
        return new File(path).createNewFile();
    }

    @Override
    public boolean renameFile(File file, String newName) throws NotDirectoryException, FileAlreadyExistsException {
        return moveAndRenameFile(file, file.getParent(),newName);
    }

    @Override
    public boolean moveFile(File file, String newParent) throws NotDirectoryException, FileAlreadyExistsException {
        return moveAndRenameFile(file,newParent,file.getName());
    }

    @Override
    public boolean moveAndRenameFile(File file, String newParent, String newName) throws NotDirectoryException, FileAlreadyExistsException {
        File parent = new File(newParent);
        if(!parent.isDirectory())
            throw new NotDirectoryException(parent);
        File renamedFile = new File(parent,newName);
        if(renamedFile.exists())
            throw new FileAlreadyExistsException(renamedFile.getAbsolutePath());
        return file.renameTo(renamedFile);

    }

    @Override
    public void printFilteredDirectoryContentRecursively(
            File directory,
            FilenameFilter filter,
            FileInfoPrinter printer,
            PrintStream out) {


    }
}
