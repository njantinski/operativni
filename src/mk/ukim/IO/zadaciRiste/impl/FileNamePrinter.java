package mk.ukim.IO.zadaciRiste.impl;

import mk.ukim.IO.zadaciRiste.FileInfoPrinter;

import java.io.File;
import java.io.PrintStream;

public class FileNamePrinter implements FileInfoPrinter {
    @Override
    public void printInf(PrintStream info, File file) {
        info.println(file.getAbsolutePath());
    }
}
