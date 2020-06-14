package mk.ukim.IO.zadaciRiste;

import java.io.*;

public class BajtPoBajt {
    public static void byteByByte(InputStream in) throws IOException {
        int offset = 0;
        int leftToBeRead = 100;
        byte[] buffer= new byte[100];
        int readLen = 0;

        while((readLen = in.read(buffer,offset,leftToBeRead)) != -1){
            offset += readLen;
            leftToBeRead -= readLen;
        }
        printData(buffer,offset);
        in.close();
    }

    private static void printData(byte[] buffer, int offset) {
        OutputStream out = null;
        try{
            out = System.out;
            out.write(buffer);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        File f = new File("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\IO\\zadaciRiste\\prob.txt");
        InputStream in = new FileInputStream(f);

        byteByByte(in);



    }
}
