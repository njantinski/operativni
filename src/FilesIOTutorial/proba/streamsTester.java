package FilesIOTutorial.proba;

import java.io.*;

public class streamsTester {
    public static void copyStream(InputStream in, OutputStream out) throws IOException {
        try{
            int c;
            while((c = in.read()) != -1){
                out.write(c);
            }
        }
        finally{
            if(in != null)
                in.close();
            if(out != null)
                out.close();
        }
    }


    // citanje bajt po bajt vo baffer so 100 bajti
    // prasaj dali ova se upotrebuva koga ima zadaca
    // primer da se procita fajl do nekoja golemina
    public static void correctReading(InputStream in) throws IOException{
        try{
            byte[] buffer = new byte[100];
            // kolku bajti se procitani vo tekovnata metoda
            int readLen = 0;

            // uste kolku bajti moze da se procitaat
            int leftToBeRead = 100;

            // do kade e popolnet bufferot so procitani bajti
            int offSet = 0;

            while((readLen = in.read(buffer, offSet, leftToBeRead)) != -1){
                offSet += readLen;
                leftToBeRead -= readLen;
            }
           // doSomethingWithThatData(buffer, offSet);
        }finally {
            if(in != null)
                in.close();
        }
        ;
    }

    public static String readTextFile(String path) throws IOException{
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path), "UTF-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine()) != null){
            sb.append(line).append("\n");
        }
        br.close();
        return sb.toString();

    }

    public static void main(String[] args) throws IOException {
        // reading from the standard input

    }
}





















