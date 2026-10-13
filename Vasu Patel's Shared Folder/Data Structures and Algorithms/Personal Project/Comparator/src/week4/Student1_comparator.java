package week4;

//working of Comparator interface on more than one field

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

class Student1_comparator {

  // instance member variables
  String Name;
  int Age;

  // parameterized constructor
  public Student1_comparator (String Name, Integer Age)
  {
      this.Name = Name;
      this.Age = Age;
  }

  public String getName() { return Name; }

  public void setName(String Name) { this.Name = Name; }

  public Integer getAge() { return Age; }

  public void setAge(Integer Age) { this.Age = Age; }

  // overriding toString() method
  @Override public String toString()
  {
      return "student{"
          + "Name=" + Name + ", Age=" + Age + '}';
  }

  static class studentSortingComparator implements Comparator<Student1_comparator> {

      @Override
      public int compare(Student1_comparator student1, Student1_comparator Student1_comparator)
      {

          // for comparison
          int NameCompare = student1.getName().compareTo(
              Student1_comparator.getName());
          int AgeCompare = student1.getAge().compareTo(
              Student1_comparator.getAge());

          // 2-level comparison
          return (NameCompare == 0) ? AgeCompare : NameCompare;
      }
  }

  public static void main(String[] args){

      // create ArrayList to store Student1_comparator
      List<Student1_comparator> al = new ArrayList<>();

      // create student objects using constructor initialization
      Student1_comparator obj1 = new Student1_comparator("Ajay", 27);
      Student1_comparator obj2 = new Student1_comparator("Sneha", 23);
      Student1_comparator obj3 = new Student1_comparator("Simran", 37);
      Student1_comparator obj4 = new Student1_comparator("Ajay", 22);
      Student1_comparator obj5 = new Student1_comparator("Ajay", 29);
      Student1_comparator obj6 = new Student1_comparator("Sneha", 22);

      // add student objects to ArrayList
      al.add(obj1);
      al.add(obj2);
      al.add(obj3);
      al.add(obj4);
      al.add(obj5);
      al.add(obj6);

      // before Sorting arraylist: iterate using Iterator
      Iterator<Student1_comparator> custIterator = al.iterator();

      System.out.println("Before Sorting:\n");
      while (custIterator.hasNext()) {
          System.out.println(custIterator.next());
      }

      // sort the object by passing the data structure object and comparator to  Collections.sort
      Collections.sort(al,new studentSortingComparator());

      // after Sorting arraylist: iterate using enhanced for-loop
      System.out.println("\n\nAfter Sorting:\n");
      for (Student1_comparator student : al) {
          System.out.println(student);
      }
  }
}