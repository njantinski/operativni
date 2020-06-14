package mk.ukim.IO.zadaciRiste.impl;

import mk.ukim.IO.zadaciRiste.FileInfoPrinter;

import java.io.File;
import java.io.PrintStream;

public class FileNameWithoutDirectoryPrint implements FileInfoPrinter {
    @Override
    public void printInf(PrintStream info, File file) {
        if(file.isFile())
            info.println(file.getAbsolutePath());
    }
}
