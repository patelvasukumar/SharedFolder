/**
 * 
 * Program: Computer Engineering Technology - Computing Science [Academic Level: 2 ]
 * Course: CST8132 Object Oriented Programming 
 * Student Name: Vasukumar Patel
 * Student Number: 041153940
 * Section #: 312
 * Professor: Dr. James Mwangi
 * L6
 */

import java.util.ArrayList;
import java.util.Iterator;

public class CityManager {
    
    private String[] allCities = {
        "New York", "Los Angeles", "Chicago", "Houston", "Phoenix",
        "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose"
    };
    
    private String[] commonCities = {
        "New York", "Chicago", "Houston", "Phoenix"
    };
    
    // created two ArrayList
    private ArrayList<String> cityList;
    private ArrayList<String> commonCityList;
    
    // constructor 
    public CityManager() {
        this.cityList = new ArrayList<>();
        this.commonCityList = new ArrayList<>();
        addCitiesToList();
        addCommonCitiesToList();
    }

    // adding array city into the list array
    public void addCitiesToList() {
        for (String city : allCities) {
            cityList.add(city);
        }
    }
    
 // adding array common city into the list array
    public void addCommonCitiesToList() {
        for (String city : commonCities) {
            commonCityList.add(city);
        }
    }

    // removing common cities from the first list
    
    public void removeCommonCities() {
        Iterator<String> myLoop = cityList.iterator();
       
        //using hasNext() to check the next element: is it in the list or not.
        //using next(): same as hasNext()
        
        while (myLoop.hasNext()) {
            if (commonCityList.contains(myLoop.next())) {
                myLoop.remove();
            }
       
        }
    }

    //calling the method and displaying the method.
    public void displayUpdatedList() {
        System.out.println("=== All Cities in the first citylist ===");
        for (String city : cityList) {
            System.out.println(city);
        }

        System.out.println();
        System.out.println("=== Cities in the commonCities list ===");
        for (String city : commonCityList) {
            System.out.println(city);
        }

        System.out.println();
        System.out.println("=== Removing Common Cities from First CityList ===");
        removeCommonCities();
        for (String city : commonCityList) {
            System.out.println("Removed city: " + city);
        }
        
        System.out.println();
        System.out.println("=== Updated First List ===");
        for (String city : cityList) {
            System.out.println(city);
        }
        
        System.out.println();
        System.out.println("=== Updated Second List ===");
        for (String city : commonCityList) {
            System.out.println(city);
        }
        
    }

    
}
