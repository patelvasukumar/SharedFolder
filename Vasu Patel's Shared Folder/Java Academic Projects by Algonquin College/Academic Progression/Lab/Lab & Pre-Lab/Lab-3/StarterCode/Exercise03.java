import java.util.Scanner;

public class Exercise03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number for folder 1: ");
        System.out.print("Folder ID 1 is that ");
        
        String folderID1 = scanner.nextLine();
        System.out.print(" the name 1 of the folder is that ");
        
        String name1 = scanner.nextLine();
        System.out.print("the location 1 of the folder is that ");
        
        String location1 = scanner.nextLine();
        System.out.print("the total number of the files 1 is that ");
        
        int numberFilesforfirst = scanner.nextInt();
        scanner.nextLine();  

        FolderFile1 = new Folder(folderID1, name1, location1, numberOfFiles1);

        System.out.println("\nEnter the number for the folder 2");
        System.out.print("the folder ID 2 is that ": ");
        
        String folderID2 = scanner.nextLine();
        System.out.print("the name of the folder 2 is that");
        
        String name2 = scanner.nextLine();
        System.out.print("the location of the folder 2 is that ");
        
        String location2 = scanner.nextLine();
        System.out.print("the total number of the files 2 is that");
        
        int numberOfFiles2 = scanner.nextInt();
        scanner.nextLine();

        FolderFile2 = new Folder();

        
        folder2.setFolderID(folderID2);
        folder2.setName(name2);
        folder2.setLocation(location2);
        folder2.setNumberOfFiles(numberOfFiles2);

        System.out.println(" the folder 1");
        System.out.println("Folder ID: " + folder1.getFolderID() + ", Name: " + folder1.getName() + ", Location: " + folder1.getLocation() +     ", Number of Files: " + folder1.getNumberOfFiles());

        System.out.println("\the folder 2");
        System.out.println(folder2.toString());

        folder1.increaseNumberOfFiles();
        System.out.println("\n the total number of Files in Folder 1 after increasing: " + folder1.getNumberOfFiles());

        folder1.decreaseNumberOfFiles();
        System.out.println("\n the total number of Files in Folder 1 after decreasing: " + folder1.getNumberOfFiles());

        scanner.close();
}