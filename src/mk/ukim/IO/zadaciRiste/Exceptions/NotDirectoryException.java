package mk.ukim.IO.zadaciRiste.Exceptions;

import java.io.File;

public class NotDirectoryException extends Exception {
    public NotDirectoryException(File f){
        super(String.format("The file %s is not a directory!", f.getAbsolutePath()));
    }
}
