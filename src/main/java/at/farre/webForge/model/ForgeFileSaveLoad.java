package at.farre.webForge.model;

public class ForgeFileSaveLoad implements java.io.Serializable{
    private static final long serialVersionUID = 99451L;
    public static final String SAVE_MANAGERFILE_PATH = "src/main/resources/forge_files/forge_save.dat";

    public static void saveForgeFileManager(ForgeFileManager ffm) {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(SAVE_MANAGERFILE_PATH))) {
            oos.writeObject(ffm);
        } catch (Exception e) {
            System.err.println("Error saving ForgeFileManager: " + e.getMessage());
        }
    }
    public static ForgeFileManager loadForgeFileManager() {
        ForgeFileManager ffm = null;
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream(SAVE_MANAGERFILE_PATH))) {
            ffm = (ForgeFileManager) ois.readObject();
        } catch (Exception e) {
            System.err.println("Error loading ForgeFileManager: " + e.getMessage());
        }
        return ffm;
    }

}
