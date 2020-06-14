package mk.ukim.IO.zadaciRiste;

import java.io.File;
import java.util.Arrays;

public class prosecnaGolemina {

    public static void prosecnaGolemina(String doc){
        File f = new File(doc);
        int dolz = 0;
        File[] fils = f.listFiles(fis -> fis.getName().endsWith(".txt"));
        for(int i= 0; i < fils.length;i++ ){
            dolz += f.length();
        }
        System.out.println("The length of txt files is: " + dolz);
        System.out.println("Average length of files is : " + (double)dolz/fils.length);
    }

    public static void main(String[] args) {
        File f = new File(args[0]);
        if(args.length == 0){
            System.out.println("ne e vnesen arg");
        }
        else if(!f.exists()){
            System.out.println("Imenikot ne postoi");
        }
        else if(!f.isDirectory()){
            System.out.println("Vneseniot fajl ne e imenik");
        }
        else
            prosecnaGolemina(f.getAbsolutePath());


    }
}
