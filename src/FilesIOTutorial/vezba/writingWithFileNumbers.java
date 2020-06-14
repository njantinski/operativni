package FilesIOTutorial.vezba;

import java.io.*;

public class writingWithFileNumbers {
    public static void fileOutputWithLineNum(File f) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                System.in));
        String line = br.readLine();

        PrintWriter pw = new PrintWriter(new BufferedWriter(
                new FileWriter(f)
        ));

        int lines = 1;
        while(!line.equals("STOP")){
            pw.println(lines + " " + line);
            line = br.readLine();
            lines++;
        }
        br.close();
        pw.close();
    }
    public static void main(String[] args) throws IOException {
        File writting = new File("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\FilesIOTutorial\\vezba\\lines.txt");

        fileOutputWithLineNum(writting);

    }
}
