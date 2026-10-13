public class Folder {
    private String folderID;
    private String name;
    private String location;
    private int numberOfFiles;

    public Folder () {
        this.folderID = "";
        this.name = "";
        this.location = "";
        this.numberOfFiles = 0;
    }

    public Folder (String folderID, String name, String location, int numberOfFiles) {
        this.folderID = folderID;
        this.name = name;
        this.location = location;
        this.numberOfFiles = numberOfFiles;
       
    }
    public String getFolderID() {
        return folderID;
    }

    public void setFolderID(String folderID) {
        this.folderID = folderID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfFiles() {
        return numberOfFiles;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    }

    public void increaseNumberOfFiles() {
        int numberOfFiles = numberOfFiles++;
        return;
    }

    public void decreaseNumberOfFiles() {
    	int numberOfFiles = numberOfFiles--;
    	return;
    }
}