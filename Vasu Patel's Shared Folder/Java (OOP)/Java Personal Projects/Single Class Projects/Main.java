
import java.util.Arrays;

class PhoneDirectory {

    private static class PhoneEntry {
        String name;
        String number;
    }

    private PhoneEntry[] data;
    private int dataCount;

    public PhoneDirectory() {
        data = new PhoneEntry[1];
        dataCount = 0;
    }

    private int find(String name) {
        for (int i = 0; i < dataCount; i++) {
            if (data[i].name.equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public String getNumber(String name) {
        int position = find(name);
        return position == -1 ? null : data[position].number;
    }

    public void putNumber(String name, String number) {
        if (name == null || number == null) {
            throw new IllegalArgumentException("name and number cannot be null");
        }

        int i = find(name);
        if (i >= 0) {
            data[i].number = number;
        } else {
            if (dataCount == data.length) {
                data = Arrays.copyOf(data, 2 * data.length);
            }
            PhoneEntry newEntry = new PhoneEntry();
            newEntry.name = name;
            newEntry.number = number;
            data[dataCount] = newEntry;
            dataCount++;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        PhoneDirectory directory = new PhoneDirectory();

        directory.putNumber("Alice", "123-456-7890");
        directory.putNumber("Bob", "987-654-3210");
        directory.putNumber("Charlie", "555-123-4567");

        System.out.println("Alice's Number: " + directory.getNumber("Alice"));
        System.out.println("Bob's Number: " + directory.getNumber("Bob"));
        System.out.println("Charlie's Number: " + directory.getNumber("Charlie"));

        directory.putNumber("Bob", "111-222-3333");
        System.out.println("Updated Bob's Number: " + directory.getNumber("Bob"));

        System.out.println("David's Number: " + directory.getNumber("David")); 
    }
}
