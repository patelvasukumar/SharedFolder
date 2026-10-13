package Week2_FileIO;

import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class BufferedOutputStreamExample {

	public static void main(String[] args) {
        String text = "Hello, BufferedOutputStream!";
        
        // When we write in the brackets (). It means we do not have to close. It is automatic.
        // But when we write outside or inside we need to close it.
        
        try (FileOutputStream fos = new FileOutputStream("BufferedOutputStream.txt");
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            bos.write(text.getBytes());
            System.out.println("Data written successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
