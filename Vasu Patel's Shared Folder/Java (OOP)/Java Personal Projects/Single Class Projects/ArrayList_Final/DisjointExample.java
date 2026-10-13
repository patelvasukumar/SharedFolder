package ArrayList_Final;

import java.util.ArrayList;
import java.util.Collections;

public class DisjointExample {

  public static void main(String[] args) {
       
	  ArrayList<String> list1 = new ArrayList<>();
      ArrayList<String> list2 = new ArrayList<>();
     
      list1.add("D");
      list1.add("E");
      list1.add("F");
      
      list2.add("D");
      list2.add("E");
      list2.add("F");
      
      
      boolean result = Collections.disjoint(list1, list2);
      System.out.println(result);
    
  }

}
