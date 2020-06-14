package mk.ukim.IO.ispitni;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class charCount {

    private static List<byte[]> read(String input) throws IOException {
        File from = validateFile(input);
        List<byte[]> readBytes = new ArrayList<>();
        try(
                InputStream is = new FileInputStream(from)
                ) {
            int readByte;
            while ((readByte = is.read()) != -1) {

                byte[] arrayLength = new byte[readByte - '0'];
                for (int i = 0; i < arrayLength.length; i++) {
                    arrayLength[i] = (byte) (is.read() - '0');
                }
                readBytes.add(arrayLength);
            }
        }
        return readBytes;
    }

    public static File validateFile(String input) throws FileNotFoundException {
        File f = new File(input);
        if(!f.exists())
            throw new FileNotFoundException("File does not exists");
        if(f.isDirectory())
            throw new FileNotFoundException("The file is not a reading file!");
        return f;
    }

    public static void main(String[] args) throws IOException {
       List<byte[]> readList = read("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\IO\\ispitni\\arrayBytes.txt");
        for(byte[] arr : readList){
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for(int i = 0; i < arr.length;i++){
                sb.append(arr[i]);
            }
            sb.append("]");
            System.out.print(sb.toString() + " ");
        }

    }
}
