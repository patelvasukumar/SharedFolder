package AarayList;
import java.util.ArrayList;

public class ListOne {

	public static void main(String[] args) {
	
	ArrayList<Integer> myObj = new ArrayList<>();
	
	myObj.add(1);
	myObj.add(2);
	myObj.add(3);
	myObj.add(4);
	myObj.add(5);
	
	System.out.println(myObj.get(0)+myObj.get(1) + myObj.get(2) + myObj.get(3)+myObj.get(4));
	
	int total = 0;
	
	for(int values : myObj) {
		total = total + values;
	}
	
	System.out.println(total);

	ArrayList<String> names = new ArrayList<>();
    names.add("Vasu");
    names.add("Jay");
    names.add("jay");
    names.add("Gyani");
    names.add("Patel");

    names.removeIf(name -> name.startsWith("J"));

    System.out.println(names);
	
    ArrayList<Double> doubleObj = new ArrayList<>();
    
    doubleObj.add(1.0);
    doubleObj.add(1.0);
    doubleObj.add(1.0);
    doubleObj.add(1.0);
    doubleObj.add(1.0);
    doubleObj.add(1.0);
    doubleObj.add(1.0);
    doubleObj.add(1.0);
    doubleObj.add(1.0);
    doubleObj.add(1.0);
    
    double sum = 0;
    
    for(double num : doubleObj) {
    	sum += num;
    }
    
    double average = sum/doubleObj.size();
    System.out.println(average);
    
    ArrayList<String> hiObj = new ArrayList<>();
    
    hiObj.add("hiVasu");
    
    hiObj.replaceAll(hi -> hi.replace("hi", "hello"));
    
    System.out.println(hiObj);
    
    ArrayList<Integer> list1 = new ArrayList<>();
    ArrayList<Integer> list2 = new ArrayList<>();
    
    list1.add(0);
    list1.add(1);
    list2.add(1);
    list2.add(0);
   // list2.add(null); // remove, it will print true
    boolean value = list1.equals(list2);
    System.out.println(value); // false when size and element are different 
    
   System.out.println(names.reversed()); // string is just reversed in output, not in the stored variable
    
   // after this, the string is permanent
    for(int i = 0, j = names.size() - 1; i < j; i++, j--) {
    	
    	String name = names.get(i);
    	names.set(i, names.get(j));
    	names.set(j,name);
    	
    }
    
    System.out.println(names);
    
    //7️. Given an ArrayList of integers, find the second largest number.
    
    ArrayList<String> superHeros = new ArrayList<>();
    
    superHeros.add("Spider-Man");
    superHeros.add("Iron-Man");
    superHeros.add("Super-Man");
    superHeros.add("Captain-America");
    superHeros.add("Thanos");
    superHeros.add("Dr.Doom");
    superHeros.add("Ash");
    superHeros.add("Dr.Doom");
    superHeros.add("Pikachu");


    
    String remove1 = "Thanos";
    String remove2 = "Dr.Doom";
    String remove3 = "Ash";
    String remove4 = "Pikachu";
    
    System.out.println(superHeros);
    
    for(int i = 0; i < superHeros.size(); i++) {
    	
    	if(superHeros.get(i) == remove1) {
    		superHeros.remove(i);
    		
    	}
    	
    	if(superHeros.get(i) == remove2) {
    		superHeros.remove(i);
    		
    	}
    	
    }
    System.out.println(superHeros);
    
    superHeros.removeIf(hero -> hero.equals(remove3) || hero.equals(remove4));
    
    System.out.println(superHeros);
    
	}
}
