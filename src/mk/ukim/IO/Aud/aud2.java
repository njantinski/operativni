package mk.ukim.IO.Aud;

import java.io.*;

public class aud2 {
   public static void copyFileByteByByte(File from, File to) throws IOException {
       InputStream is = null;
       OutputStream out = null;

       long startTime = System.currentTimeMillis();
       long readCounter = 0;


       try{
           is = new FileInputStream(from);
           out = new FileOutputStream(to);
           int br = -1;

           while((br = is.read()) != -1) {
               out.write(br);
               out.flush();
               readCounter++;
           }
       }
       finally{
           if(is != null)
               is.close();
           if(out != null){
               out.flush();
               out.close();
           }
       }
       long duration = System.currentTimeMillis() - startTime;
       System.out.println("Copy duration: " + duration);
       System.out.println("Number of times read is invoked: " + readCounter);
      
   }

   public void copyFileUsingBuffer(String from, String to) throws IOException {
        try(
                BufferedReader reader = new BufferedReader(new FileReader(from));
                BufferedWriter bw = new BufferedWriter(new FileWriter(to))
                ){
                String line = null;

                while((line = reader.readLine()) != null) {
                    bw.write(line);
                    bw.newLine();
                    bw.flush();
                }
        }
   }

   public void copyFileUsingCustomBuffer(File from,File to,boolean append){
       System.out.println("Copy file with custom buffer");
       try(
               InputStream is = new FileInputStream(from);
               OutputStream os = new FileOutputStream(to,append);
               ){

           int read;
           int offset = 0;
           int length = 512;


           byte[] buffer = new byte[512];
           while((read = is.read(buffer,offset,length)) != -1){
               os.write(buffer,offset,read);
           }

       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }


   }
    public void copyFileWithConvertingByteStreamToCharStreamWithCharset(File from, File to, boolean append, String charset) throws IOException {
       long startTime = System.currentTimeMillis();
        long readCounter = 0;

       try(
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(from),charset));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(to),charset))
                ){
                int read;
                while((read = br.read() ) != -1){
                    bw.write(read);
                    readCounter++;
                }
        }
       long duration = System.currentTimeMillis() - startTime;
        System.out.println("Copy duration: " + duration);
    }

    public void copyFileCharByChar(File from, File to) throws IOException {
       try(
               FileReader fr = new FileReader(from);
               FileWriter fw = new FileWriter(to)
               ){
           int readChar = 0;
           while((readChar = fr.read()) != -1){
               fw.write(readChar);
           }

       }
    }

    public void copyFileLineByLine(File from, File to, boolean append) throws FileNotFoundException {
       long startTime = System.currentTimeMillis();
       try(
               BufferedReader br = new BufferedReader(new FileReader(from));
               BufferedWriter bw = new BufferedWriter(new FileWriter(from,append));
               ) {
           long readingCounter = 0;
           String line;
           while((line = br.readLine()) != null){
               bw.write(line);
               bw.newLine();
               readingCounter++;
           }

       } catch (IOException e) {
           e.printStackTrace();
       }

       long duration = System.currentTimeMillis() - startTime;
        System.out.println("Copy duration: " + duration);
    }

    public static void copyFileContentReversed(File from, File to) throws FileNotFoundException {
       long startTime = System.currentTimeMillis();
       long readCounter = 0;
       try(
           RandomAccessFile raf = new RandomAccessFile(from,"r");
           BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(to)))
       {
           int read;
           for(long i = raf.length() - 1; i >= 0; i--){
               raf.seek(i);
               read = raf.read();
               bos.write(read);
               readCounter++;
           }

       } catch (IOException e) {
           e.printStackTrace();
       }

    }
}
