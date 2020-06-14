package vezbiLab.io;

import java.io.File;

public class FileExistsException extends Exception{
    public FileExistsException(String f){
        super(String.format("File %s already exists!",f));
    }
}
