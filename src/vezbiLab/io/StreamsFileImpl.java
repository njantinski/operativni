package vezbiLab.io;

import org.w3c.dom.ls.LSOutput;

import java.io.*;

public class StreamsFileImpl implements StreamsFile{
    @Override
    public void copyFileByteByByte(File from, File to) throws IOException {
        try(FileInputStream fin = new FileInputStream(from);
            FileOutputStream fout = new FileOutputStream(to)){
            int b;
            while((b = fin.read()) != -1){
                fout.write(b);
            }
        }
    }

    @Override
    public void copyFileByUsingBuffer(String from, String to) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(from));
            BufferedWriter bw = new BufferedWriter(new FileWriter(to))){
            String line;
            while( (line = br.readLine()) != null){
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
        }
    }

    @Override
    public void printContentOfTxtFile(File f, PrintStream printer) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            String line;
            while(( line = br.readLine()) != null){
                printer.println(line);
            }
        }
    }

    @Override
    public void readContentFromStdInput(OutputStream to) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(to))){
                String line;
                while((line = br.readLine()) != null){
                    bw.write(line);
                    bw.newLine();
                    bw.flush();
                }
            }
    }

    @Override
    public void writeToTextFile(File to, String text, Boolean append) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(to,append))){
            bw.write(text);
        }
    }



    @Override
    public void memorySafeTextFileCopy(File from, File to) throws FileNotFoundException, IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(to));
            BufferedReader br = new BufferedReader(new FileReader(from))){
            String line;
            while((line = br.readLine()) != null){
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
        }

    }

    @Override
    public void readFileWithLineNumber(File from, OutputStream is) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(is));
            BufferedReader br = new BufferedReader(new FileReader(from))){
            String line;
            int lineNum = 1;
            while((line = br.readLine() ) != null){
                StringBuilder sb = new StringBuilder();
                sb.append(lineNum).append(". ").append(line);
                bw.write(sb.toString());
                bw.flush();
            }
        }
    }

    @Override
    public void writeBinaryDataToBFile(File to, Object... objects) throws IOException {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(to))){
            for(Object o : objects){
                if(o instanceof String){
                    dos.writeUTF((String) o);
                }
                else if(o instanceof Integer){
                    dos.writeInt((Integer) o);
                }
                else if(o instanceof Double){
                    dos.writeDouble((Double) o);
                }
            }
        }
    }

    @Override
    public void readBinaryDataFromBFile(File from, Object... objects) throws FileNotFoundException, IOException {

    }

    @Override
    public void writeToRandomAccessFile(File from) throws IOException {

    }

    @Override
    public void readFromRandomAccessFile(File from, PrintStream out) throws IOException {

    }

    @Override
    public void rewriteInReverseFile(File from, File to) throws IOException {

    }
}
