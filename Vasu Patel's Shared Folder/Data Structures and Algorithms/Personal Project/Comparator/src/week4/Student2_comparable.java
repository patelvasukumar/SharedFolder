package week4;


//working of Comparator interface on more than one field

import java.util.ArrayList;
import java.util.Collections;
import java.lang.Comparable;
import java.util.Iterator;
import java.util.List;

class Student2_comparable implements Comparable<Student2_comparable> {

  // instance member variables
  String Name;
  int Age;

  // parameterized constructor
  public Student2_comparable(String Name, Integer Age) {
      this.Name = Name;
      this.Age = Age;
  }

  public String getName() {
      return Name;
  }

  public void setName(String Name) {
      this.Name = Name;
  }

  public Integer getAge() {
      return Age;
  }

  public void setAge(Integer Age) {
      this.Age = Age;
  }

  // overriding toString() method
  @Override
  public String toString() {
      return "student{" + "Name=" + Name + ", Age=" + Age + '}';
  }

  @Override
  public int compareTo(Student2_comparable s) {

      return this.Age - s.Age;
  }

  public static void main(String[] args) {

      // create ArrayList to store Student2_comparable
      List<Student2_comparable> al = new ArrayList<>();

      // create student objects using constructor initialization
      Student2_comparable obj1 = new Student2_comparable("Ajay", 27);
      Student2_comparable obj2 = new Student2_comparable("Sneha", 23);
      Student2_comparable obj3 = new Student2_comparable("Simran", 37);
      Student2_comparable obj4 = new Student2_comparable("Ajay", 22);
      Student2_comparable obj5 = new Student2_comparable("Ajay", 29);
      Student2_comparable obj6 = new Student2_comparable("Sneha", 22);

      // add student objects to ArrayList
      al.add(obj1);
      al.add(obj2);
      al.add(obj3);
      al.add(obj4);
      al.add(obj5);
      al.add(obj6);

      // before Sorting arraylist: iterate using Iterator
      Iterator<Student2_comparable> custIterator = al.iterator();

      System.out.println("Before Sorting:\n");
      while (custIterator.hasNext()) {
          System.out.println(custIterator.next());
      }

      // sort the object by passing the data structure object and comparator to
      // Collections.sort
      Collections.sort(al);

      // after Sorting arraylist: iterate using enhanced for-loop
      System.out.println("\n\nAfter Sorting:\n");
      for (Student2_comparable student : al) {
          System.out.println(student);
      }

  }
}
