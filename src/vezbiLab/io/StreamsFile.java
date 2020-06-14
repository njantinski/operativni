package vezbiLab.io;

import java.io.*;

public interface StreamsFile {

    void copyFileByteByByte(File from, File to) throws IOException;

    void copyFileByUsingBuffer(String from, String to) throws IOException ;

    void printContentOfTxtFile(File f, PrintStream printer) throws IOException;

    void readContentFromStdInput(OutputStream to) throws IOException;

    void writeToTextFile(File to, String text, Boolean append) throws IOException;


    void memorySafeTextFileCopy(File from, File to) throws FileNotFoundException, IOException;

    void readFileWithLineNumber(File from, OutputStream is) throws IOException;

    void writeBinaryDataToBFile(File to, Object... objects) throws IOException ;

    void readBinaryDataFromBFile(File from, Object... objects) throws FileNotFoundException,IOException;

    void writeToRandomAccessFile(File from) throws IOException;

    void readFromRandomAccessFile(File from, PrintStream out) throws IOException;

    void rewriteInReverseFile(File from, File to) throws IOException;


}
