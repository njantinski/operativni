package mk.ukim.IO.ispitni;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class partialExamG1 {

    public void moveWritableTxtFiles(String from, String to,String extension) throws IOException {
        File fromFile = validateDir(from);
        File toFile = createNonExistingDir(to);

        File[] movableFiles = fromFile.listFiles(f -> f.getAbsolutePath().endsWith(extension) && f.canWrite());
        Arrays.stream(movableFiles).forEach(f -> {
            try {
                moveAndRenameFile(f,to,f.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



    private void moveAndRenameFile(File f, String newParent, String newName) throws IOException {
        File parent = validateDir(newParent);

        File renamedFile= new File(parent, newName);
       f.renameTo( validateFile(renamedFile.getAbsolutePath()));

    }
    private File validateDir(String path) throws IOException {
        File val = new File(path);
        if(!val.exists())
            throw new IOException(String.format("File doesn't exist! %s", path));
        if(!val.isDirectory())
            throw new IOException(String.format("The file is not directory! %s", path ));
        return val;

    }
    private File createNonExistingDir(String dir) throws IOException {
        File dire = validateDir(dir);
        if(!dire.exists())
            dire.mkdirs();
        return dire;
    }

    private File validateFile(String path) throws IOException {
        File f = new File(path);
        if(f.exists())
            throw new IOException(String.format("File already exist! %s", path));
        if(f.isDirectory())
            throw new IOException(String.format("The file is directory,not a file! %s", path ));

        return f;
    }

    public void deserializeData(String source, List<byte[]> data, long elementLength) throws IOException {
        File f = validateFile(source);
        try(
                FileInputStream in = new FileInputStream(f);
                ){
            byte[] readData;
            while((readData = in.readNBytes((int) elementLength)) != null){
                data.add(readData);
            }
        }
    }


    public void invertLargeFiles(String source, String destination) throws IOException {
        File fromFile = validateFile(source);
        File toFile = validateFile(destination);

        try(RandomAccessFile raf = new RandomAccessFile(fromFile,"r");
            OutputStream fis = new FileOutputStream(toFile);
        ){

        for(int i =(int) raf.length() - 1; i <= 0;i --){
            raf.seek(i);
            int b = raf.read();
            fis.write(b);
        }
        }


    }
}
