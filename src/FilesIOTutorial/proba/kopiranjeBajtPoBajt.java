package FilesIOTutorial.proba;

import java.io.*;

import static java.lang.System.*;

public class kopiranjeBajtPoBajt {
    public static final int CAPACITY = 4096;

    public static void copyData(File f, File out) throws IOException {
        try(
                FileInputStream fis = new FileInputStream(f);
                ){
            int c;
            byte[] buffer = new byte[CAPACITY];
            int i = 0;
            int leftToBeRead = CAPACITY;
            while((c =  fis.read(buffer, i, leftToBeRead)) != -1){
                if(i == CAPACITY){
                    break;
                }
                i+= c;
                leftToBeRead -=c;
            }
            copyDataToOtherFile(buffer, i, out);
            printBinaryData(buffer, i);
        }

    }
    private static void copyDataToOtherFile(byte[] buffer, int offset, File out) throws IOException {
        try(
            FileOutputStream fos = new FileOutputStream(out);
                ){
            fos.write(buffer, 0,  offset);
        }
    }
    private static void printBinaryData(byte[] buffer, int offset) throws IOException{
        try(
              DataOutputStream dos = new DataOutputStream(System.out);
                ){
                    dos.write(buffer, 0, offset);
                    dos.flush();
                    dos.close();

        }
    }

    public static void printDataWithLinesNum(File f) throws IOException {
        try(
                BufferedWriter bos = new BufferedWriter(new OutputStreamWriter(System.out));
                BufferedReader br = new BufferedReader(new FileReader(f));
                ){
                    int i = 1;
                    String read;
                    while((read = br.readLine()) != null ){
                        bos.write(i + read + "\n");
                        i++;
                    }
        }
    }

    public static void main(String[] args) throws IOException {
        File in = new File("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\FilesIOTutorial\\proba\\kopiranjeOdTest.txt");
        File out = new File("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\FilesIOTutorial\\proba\\kopiranjeVoTest.txt");

        copyData(in,out);
        // printDataWithLinesNum(in);
    }


}
