package vezbiLab.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Date;

public class FileManImpl implements FileMan{
    @Override
    public File getFileFromString(String file) {
        return new File(file);
    }

    @Override
    public File[] getFilesInFolder(File file) throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException();
        return file.listFiles();
    }

    @Override
    public void printFileNames(File file, PrintStream writer) throws FileNotFoundException {
        if(!file.exists())
            throw new FileNotFoundException();
        if(!file.isDirectory())
            writer.println(file.getAbsolutePath());
        else{
            Arrays.stream(file.listFiles()).forEach(f -> writer.println(file.getAbsolutePath()));
        }
    }

    @Override
    public File[] getFilesFromString(String filePath) throws FileNotFoundException {
        if(!(new File(filePath).exists())){
            throw new FileNotFoundException();
        }

        return new File(filePath).listFiles();
    }

    @Override
    public String getAbsPath(String relPath) throws FileNotFoundException {
        File f = new File(relPath);
        if(!f.exists()){
            throw new FileNotFoundException();
        }
        else
            return f.getAbsolutePath();
    }

    @Override
    public long getFileSize(String file) throws FileNotFoundException {
        File f = new File(file);
        if(!f.exists())
            throw new FileNotFoundException();
        return f.length();
    }

    @Override
    public void printFilePermissions(File f, PrintStream writer) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append("Permissions: read, write, execute: ");
        sb.append(f.canRead()).append(", ").append(f.canRead()).append(", ").append(f.canExecute());
        writer.println(sb.toString());
    }

    @Override
    public void createNewFile(String file) throws FileExistsException, IOException {
        File f = new File(file);
        if(f.exists())
            throw new FileExistsException(file);
        f.createNewFile();
    }

    @Override
    public void createFolder(String folder) throws FileExistsException {
        File f = new File(folder);
        if(f.exists())
            throw new FileExistsException(folder);
        f.mkdir();
    }

    @Override
    public File[] filterImagesFilesInDir(String dirPath) throws FileNotFoundException {
        File f = new File(dirPath);
        if(!f.exists() || !f.isDirectory())
            throw new FileNotFoundException();
        return f.listFiles(fl -> fl.getAbsolutePath().endsWith(".jpg") || fl.getAbsolutePath().endsWith(".png"));
    }

    @Override
    public void renameFile(File src, File dest) throws FileNotFoundException, FileExistsException {
        File newFile = new File(dest.getAbsolutePath(),src.getName());
        if(newFile.exists())
            throw new FileExistsException(newFile.getAbsolutePath());
        if(!src.exists() || !dest.exists())
            throw new FileNotFoundException();
        src.renameTo(newFile);
    }

    @Override
    public Date getLastModified(String file) throws FileNotFoundException {
        File f = new File(file);
        return new Date(f.lastModified());

    }

    @Override
    public void filterImagesFilesInDirRec(File file, PrintStream out) throws FileNotFoundException {
        File[] files = file.listFiles();
        for (File f: files) {
            if (f.isDirectory()) {
                filterImagesFilesInDirRec(f,out);
            }
            if (f.isFile() && (f.getName().endsWith(".png") ||
                    f.getName().endsWith("jpg"))) {
                out.println(f.getAbsolutePath());
            }
        }


    }

    @Override
    public boolean deleteFolder(File folder) throws FileNotFoundException {
        if(!folder.exists())
            throw new FileNotFoundException();
        File[] filesInDir = folder.listFiles();
        for(File f : filesInDir){
            if(f.isDirectory()){
                deleteFolder(f);
            }
            else{
                f.delete();
            }
        }
        return folder.delete();
    }
}
