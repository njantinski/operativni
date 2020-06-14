package mk.ukim.IO.zadaciRiste.Exceptions;

import java.io.File;

public class FileExistException extends Exception {
    public FileExistException(File f){
        super(String.format("The file %s already exist!" , f.getAbsolutePath()));
    }
}
