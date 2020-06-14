package mk.ukim.IO.zadaciRiste.Exceptions;

import java.io.File;

public class MissingFilePermisionException extends Exception {
    MissingFilePermisionException(File f){
        super(String.format("Missing permission exception for file: %s", f.getAbsolutePath()));
    }
}
