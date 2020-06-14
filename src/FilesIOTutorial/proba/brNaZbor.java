package FilesIOTutorial.proba;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class brNaZbor {
    public static int brojZbor(File f, String str) throws IOException {
        ArrayList<String> zborovi = new ArrayList<>();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(f),"UTF-8"));
        String line;
        while((line = br.readLine()) != null){
            String[] linija = line.split("\\s+");

            for(String l : linija)
                zborovi.add(l);
        }

        int brString = 0;
        for(String strg : zborovi){
            if(strg.equals(str))
                brString++;
        }
        return brString;
    }

    public static void main(String[] args) {
        try {
            System.out.println(brojZbor(
                    new File("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\finki\\CitanjeOdFajl"),"Java"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
