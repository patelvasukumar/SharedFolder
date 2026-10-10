/*
 Class Name: Lab02
 Student Name: Vasukumar Patel
 Professor Name: James Mwangi
 Date: 24-9-2024
 Description: to get an average price.
 */

//Program corrected by: ________Vasukumar Patel________________

import java.util.Scanner;

public class Lab02 {
    public static void main(String[] args) {

    	double mangoPrice;
    	double orangePrice;
    	double bananaPrice;
    	double averagePrice;
    	   	
        Scanner input = new Scanner(System.in); 

        // Input prices for three fruit items
        System.out.print("Enter the price for Mango:");
        mangoPrice = input.nextDouble();

        System.out.print("Enter the price for Orange:");
        orangePrice = input.nextDouble();

        System.out.print("Enter the price for Banana:"); 
        bananaPrice = input.nextDouble();

        // Calculate average price
        averagePrice = (mangoPrice + orangePrice + bananaPrice)/ 3;

        // Output results
        System.out.println(averagePrice);    
        
        System.out.println("Program by: Vasukumar Patel");
        
        // Close the scanner
        input.close();

       }
}