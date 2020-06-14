package FilesIOTutorial.proba;

import java.io.*;

public class writingFromFile {
    public static void writtingFromFIle(File from, BufferedWriter wr) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(from))
        );
        String line = null;
        while((line = br.readLine()) != null){
            wr.write(line);
            wr.newLine();
        }
        br.close();
    }

    public static void main(String[] args) throws IOException {
        File f = new File("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\finki\\CitanjeOdFajl");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        writtingFromFIle(f, bw);
        bw.close();
    }
}
