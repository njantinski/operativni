package FilesIOTutorial.vezba;
import java.io.*;

public class IOTester   {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter outputStreamName;
        outputStreamName = new PrintWriter(new FileOutputStream("src\\FilesIOTutorial\\vezba\\proba1.txt"));
        outputStreamName.println("Kreten");
        outputStreamName.flush();
    }

}
