package mk.ukim.lab2020.lab1.obicni;

import java.io.*;

/**
 * Да се напише Java програма која со користење на I/O стримови
 * ќе ја прочита содржината на датотеката izvor.txt,  линија по
 * линија, а потоа за секоја линија ќе го запише бројот на
 * повторувања на самогласки во истата. Резултатот го
 * запишува во празната датотека destinacija.txt.
 */
public class ioStreams {
    public static void read(String from, String to) throws IOException {
        File fromFile = new File(from);
        File toFile = new File(to);

        try(
                BufferedReader br = new BufferedReader(new FileReader(fromFile));
                PrintWriter pw = new PrintWriter(new FileWriter(toFile))
                ){
            String line = null;
            while((line = br.readLine()) != null){
                pw.println(getSamoglaski(line));
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
}
