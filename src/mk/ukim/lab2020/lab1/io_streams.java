package mk.ukim.lab2020.lab1;

import java.io.*;

/**
 * Да се напише Java програма која со користење на I/O стримови
 * ќе ја прочита содржината на датотеката izvor.txt,  линија по
 * линија, а потоа за секоја линија ќе го запише бројот на
 * повторувања на самогласки во истата. Резултатот го
 * запишува во празната датотека destinacija.txt.
 */

public class io_streams {
    public static void samoglaski(String from, String to) throws IOException {
        File fromFile = validateFile(from);
        File toFile = validateFile(to);

        try(BufferedReader br = new BufferedReader(new FileReader(fromFile));
        PrintWriter pw = new PrintWriter(new FileWriter(toFile));) {

            String line;
            while ((line = br.readLine()) != null) {
                int brSamoglaski = getSamoglaski(line);
                pw.println(brSamoglaski);
            }
        }
    }

    private static int getSamoglaski(String line) {
        int samoglaskiNum = 0;
        char[] samoglaski = {'a','e','i','o','u'};
        line = line.toLowerCase();
        for (int i = 0; i < line.length(); i++) {
            for(char c : samoglaski){
                if(c == line.charAt(i))
                    samoglaskiNum++;
            }
        }
        return samoglaskiNum;
    }

    private static File validateFile(String path) throws FileNotFoundException {
        File test = new File(path);
        if(test.isDirectory())
            throw new FileNotFoundException("The file is directory!");
        if(!test.getAbsolutePath().contains(".txt"))
            throw new FileNotFoundException("The file is not txt, cant read from it");

        return test;
    }

    public static void main(String[] args) throws IOException {
        samoglaski("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\lab2020\\lab1\\izvor.txt", "C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\lab2020\\lab1\\sodrzina.txt");
    }
}
