package mk.ukim.IO.zadaciRiste.impl;

import mk.ukim.IO.zadaciRiste.Exceptions.NotDirectoryException;

import java.io.File;

public class tester {
    public static void main(String[] args) throws NotDirectoryException {
        FileManagerImplementation fl = new FileManagerImplementation();
        fl.deleteDirectoryRecursively(new File("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\VezbiJavaUcenjeKurs\\JavaFunctionalCourse\\src\\main\\java\\FolderToDelete"));
    }
}
