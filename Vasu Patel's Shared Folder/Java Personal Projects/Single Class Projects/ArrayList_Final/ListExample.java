package ArrayList_Final;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import java.util.LinkedList;

public class ListExample {

    public static void main(String[] args) {
    
        List<Integer> list = new ArrayList<>();
        
        list.add(10);
        list.add(20);
        list.add(50);
        list.add(70);
        list.add(0);
        list.add(50);
        
        System.out.println(list);
        
        Collections.sort(list);
        
        System.out.println(list);
        
        Iterator<Integer> myLoop = list.iterator();
        
        while (myLoop.hasNext()) {
            int num = myLoop.next();
            if (num < 5) {
                myLoop.remove();
            }
        }
        
        System.out.println(list);
    }
}







