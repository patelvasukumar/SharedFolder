package ArrayList_Final;

import java.util.ArrayList;
import java.util.Iterator;

public class PersonName {

    public static void main(String[] args) {

        ArrayList<String> names = new ArrayList<>();

        names.add("Alice");
        names.add("Jonathan");
        names.add("Bob");
        names.add("Catherine");
        names.add("Eve");
        names.add("Daniel");

        ArrayList<String> removeNames = new ArrayList<>();

        removeNames.add("Jonathan");
        removeNames.add("Catherine");
        removeNames.add("Daniel");

        Iterator<String> iterator = names.iterator();

        while (iterator.hasNext()) {
           
        	String currentName = iterator.next();
            
        	if (removeNames.contains(currentName)) {
                iterator.remove();
            } else {
                System.out.println(currentName);
            }
       
        }

        System.out.println("The total element is: " + names.size());
   
    }

}
