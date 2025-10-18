package at.farre.webForge.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
@Getter
@Setter
@Component
public class ForgeFileManager {
    private HashMap<String, String> files;
    private int selectedFileIndex =  0;
    public ForgeFileManager() {
        this.files = new HashMap<>();
    }
    public boolean loadFiles() {
        File fr;
        try {
            fr = new File("src/main/resources/forge_files");
            File[] fileList = fr.listFiles();
            if (fileList != null) {
                for (File file : fileList) {
                    if (file.isFile()) {
                        files.put(file.getName(), file.getName().substring(5,12));
                    }
                }
            }
        }catch(Exception e){
            System.err.println("Error loading files: " + e.getMessage());
            return false;
        }
        return true;

    }
    public boolean refreshFiles() {
        files.clear();
        return loadFiles();
    }
    public int getFileCount() {
        return files.size();
    }
    public String getFileByName(String name) {
        return files.get(name);
    }
    public File deleteFileByName(String name) {
        File fr = new File("src/main/resources/forge_files/"+name);
        if(fr.delete()){
            files.remove(name);
            return fr;
        }
        return null;
    }
    public boolean addFile(File forgeFile) {
        if(forgeFile.exists() && forgeFile.isFile()){
            File newFile = new File("src/main/resources/forge_files/"+forgeFile.getName());
            if(!forgeFile.renameTo(newFile)) throw new RuntimeException("[addFile] Could not move file");
            files.put(newFile.getName(), newFile.getName().substring(5,12));
            return true;
        }
        return false;

    }
    public boolean setSelectedFileIndex(int index) {
        if(index >= 0 && index < files.size()){
            this.selectedFileIndex = index;
            return true;
        }
        return false;
    }
    public boolean setSelectedFileIndex(String name) {
        if (!files.containsKey(name)){
            throw new RuntimeException("[setSelectedFileIndex] No such file: "+name);
        }
        int index = 0;
        for (String key : files.keySet()) {
            if(key.equals(name)){
                this.selectedFileIndex = index;
                return true;
            }
            index++;
        }

        return false;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ForgeFileManager:\n");
        sb.append("Total files: ").append(files.size()).append("\n");
        sb.append("selected File Index: ").append(selectedFileIndex).append("\n");
        for (String key : files.keySet()) {
            sb.append("filename"+key).append(" | uiname: ").append(files.get(key)).append("\n");
        }
        return sb.toString();
    }

}
