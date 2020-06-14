package mk.ukim.lab2020.lab1.obicni;

import java.io.*;
import java.util.function.Consumer;

public class numLines {
    public static void copyFilesStartingWithNum(String from, String to) throws IOException {
        File fromFile = validateTxtFile(from);
        File toFile = validateTxtFile(to);

        try(
                BufferedReader br = new BufferedReader(
                        new FileReader(fromFile));
                PrintWriter pw = new PrintWriter(new FileWriter(toFile))
        ) {
            String line;

          /*while((line = br.readLine()) != null){
              try{
                c = Integer.parseInt(line.charAt(0) + "");
                pw.println(line);
              }
              catch(NumberFormatException e){
                  continue;
              }
          }*/

            Consumer<String> printWtihLines = l -> {
                try {
                    int c = Integer.parseInt(l.charAt(0) + "");
                    pw.println(l);
                } catch (NumberFormatException e) {
                }
            };

          br.lines().forEach(printWtihLines);
        }
    }



    private static File validateTxtFile(String from) {
        File f = new File(from);
        if(!f.isFile() || !f.getAbsolutePath().endsWith(".txt"))
            throw new RuntimeException("Invalid txt file");
        return f;
    }


    public static void main(String[] args) throws IOException {
       copyFilesStartingWithNum("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\lab2020\\lab1\\obicni\\fromWithSomeNums.txt"
               ,"C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\lab2020\\lab1\\obicni\\toStartingWithNums.txt");
    }
}
