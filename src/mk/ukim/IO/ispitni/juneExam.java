package mk.ukim.IO.ispitni;

import mk.ukim.IO.zadaciRiste.Exceptions.FileExistException;

import java.io.*;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public class juneExam {

    public static void manage(String in, String out) throws IOException {
        File from = validateFile(in);
        File to = validateFile(out);
        File resources = validateTxtFile("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\IO\\ispitni\\resources\\writable-content.txt");


        Consumer<File> printaj = f -> System.out.println(f.getAbsolutePath());
        Consumer<File> pomesti = f -> {
            try {
                pomesti(f,to);
            } catch (FileExistException e) {
                e.printStackTrace();
            }
        };
        Consumer<File> dopisuvam = f -> System.out.println("dopisuvam " +f.getAbsolutePath());
        Consumer<File> dodadi = f -> printBuffered(f,resources);
        File[] canWriteFiles = from.listFiles(File::canWrite);
        applyActions(canWriteFiles,printaj,pomesti);

        File[] canNotWriteFiles = from.listFiles(f -> !f.canWrite());
        applyActions(canNotWriteFiles,dopisuvam,dodadi);

        File[] hiddenFiles = from.listFiles(File::isHidden);
        Consumer<File> zbunet =  f -> System.out.println("zbunet sum " + f.getAbsolutePath());
        Consumer<File> brisi = f -> brisi(f);

        applyActions(hiddenFiles,zbunet,brisi);


    }

    private static void brisi(File f) {
        if(!f.isDirectory())
            f.delete();
        else{
            File[] filesInDir = f.listFiles();
            if(filesInDir == null){
                f.delete();
            }
            else{
                Arrays.stream(filesInDir).filter(fi -> !fi.isDirectory()).forEach(File::delete);
                Arrays.stream(filesInDir).filter(fi -> fi.isDirectory()).forEach(fi -> brisi(fi));
            }

        }
    }

    private static void printBuffered(File f, File resources) {
        try(
                BufferedReader br = new BufferedReader(new FileReader(f));
                BufferedWriter bw = new BufferedWriter(new FileWriter(resources,true))
                ) {
            String line;
            while((line = br.readLine()) != null){
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void applyActions(File[] canWriteFiles,  Consumer<File> action1, Consumer<File> action2) {
        Arrays.stream(canWriteFiles).forEach(f -> action1.andThen(action2).accept(f));
    }

    private static void pomesti(File f,File to) throws FileExistException {
        moveAndRenameFile(f,to,f.getName());
    }

    private static void moveAndRenameFile(File f, File to, String name) throws FileExistException {
        File renamedFile = new File(to,name);
        if(renamedFile.exists())
            throw new FileExistException(renamedFile);
        f.renameTo(renamedFile);
    }

    public static File validateTxtFile(String out) throws IOException {
        File f = new File(out);
        if(!f.exists())
            throw new FileNotFoundException("The directory does not exist");
        if(f.isDirectory())
            throw new FileNotFoundException("The file is a directory!");
        if(!f.canWrite())
            throw new IOException("Cant write to file");
        return f;
    }

    public static File validateFile(String in) throws FileNotFoundException {
        File f = new File(in);
        if(!f.exists())
            throw new FileNotFoundException("The directory does not exist");

        if(!f.isDirectory())
            throw new FileNotFoundException("The file is not a directory!");

        return f;
    }

    public static void main(String[] args) throws IOException {
        File f1 = new File("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\IO\\ispitni\\resources\\f1.txt");
        File f2 = new File("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\IO\\ispitni\\resources\\f2.txt");
        File f3 = new File("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\IO\\ispitni\\resources\\f3.txt");
        File f4 = new File("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\IO\\ispitni\\resources\\f4.txt");
        File f5 = new File("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\IO\\ispitni\\resources\\f5.txt");
        f1.setReadOnly();
        f2.setReadOnly();
        manage("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\IO\\ispitni\\resources","C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\mk\\ukim\\IO\\ispitni\\moveDestination");
    }
}
