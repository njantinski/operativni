package mk.ukim.IO.ispitni;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class partialExamG2 {

    public void copyLargeTxtFiles(String from, String to, long size) throws IOException {
        File fromFile = validateFile(from);
        File toFile= createNonExistingToDir(to);

        moveLargeTxtFiles(fromFile,toFile,size);
    }
    /*
    The list of data in data is written into the destination file,
     without delimiters (as a continuous stream of bytes). All elements
      from data have the same length (same number of bytes).
     */
    public void serializeData(String destination, List<byte[]> data) throws IOException {
        File destinationFile = validateFile(destination);
        int length = data.get(0).length;

        try(FileOutputStream bw = new FileOutputStream(destinationFile)){
            data.stream().forEach(e -> {
                try {
                    bw.write(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }


    private void moveLargeTxtFiles(File fromDir, File toDir, long size){
        File[] largeTxtFiles = fromDir.listFiles(file -> file.isFile() && file.getAbsolutePath().endsWith(".txt") && file.length() > size);

        Arrays.stream(largeTxtFiles).forEach(f -> {
            try {
                copyFileContent(f,toDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }




    private File validateFile(String from) throws IOException {

        File f = new File(from);
        if(!f.exists())
            throw new IOException(String.format("File %s doesnt exist!",f.getAbsolutePath()));
        if(!f.isDirectory())
            throw new IOException(String.format("File %s is not a directory!" , f.getAbsolutePath()));

        return f;

    }

    private File createNonExistingToDir(String to) throws IOException {
        File f = new File(to);
        if(!f.isDirectory())
            throw new IOException(String.format("File %s is not a directory!" , f.getAbsolutePath()));
        if(!f.exists())
            f.mkdirs();
        return f;
    }
    // ne treba vaka posto ke crash ne programata za golemi fajlovi
   /* private void copyFileContent(File f, File toDir) throws IOException {
        try(  FileInputStream fis = new FileInputStream(f);
              FileOutputStream fos = new FileOutputStream(new File(toDir,f.getName()))) {
                  int b;
                  while( (b = fis.read()) != -1){
                      fos.write(b);
                  }
              }
        catch(IOException e){
                  e.printStackTrace();
        }
    }*/

   private void copyFileContent(File f, File toDir) throws IOException {
       BufferedReader br = null;
       BufferedWriter bw = null;
       try{
           br = new BufferedReader(new FileReader(f));
           File copyFile = new File(toDir, f.getName());
           bw= new BufferedWriter(new FileWriter(copyFile));

           String line;

           while((line = br.readLine()) != null){
               bw.write(line  + "\n");
           }

       }
       finally{
           if(br != null)
               br.close();
           if(bw != null){
               bw.flush();
               bw.close();
           }

       }
   }
}
