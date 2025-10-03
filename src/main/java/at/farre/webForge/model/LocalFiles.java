package at.farre.webForge.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
@Getter
@Setter
@Component
public class LocalFiles {
    private HashMap<String, String> files;
    public LocalFiles() {
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

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (String key : files.keySet()) {
            sb.append("filename"+key).append(" | uiname: ").append(files.get(key)).append("\n");
        }
        return sb.toString();
    }

}
