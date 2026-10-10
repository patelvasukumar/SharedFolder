package Serialization;

import java.io.Serializable;


public class P1_Student implements Serializable  {

	private int id;
	private String name;
	private double grade;
	
	public P1_Student(int id, String name, double grade) {
	
		this.id = id; 
		this.name = name; 
		this.grade = grade;
		
	}
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public double getGrade() { return grade; }
	public void setGrade(double grade) { this.grade = grade; }
	
	
}
