package FilesIOTutorial.proba;

import java.io.*;

public class StoringAndRetrievingData {
    public static void dataWrite(String path) throws IOException {
        DataOutputStream dos = null;

        try{
            dos = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\FilesIOTutorial\\proba\\datawriting.txt")));
            dos.writeDouble(3.14);
            dos.writeBoolean(false);
            dos.writeChars("idemo niiish");
            dos.writeInt(152);
            dos.writeUTF("jas sum najjakiot covek na celata planeta");

        }
        finally{
            if(dos != null){
                dos.flush();
                dos.close();
            }
        }
    }

}

