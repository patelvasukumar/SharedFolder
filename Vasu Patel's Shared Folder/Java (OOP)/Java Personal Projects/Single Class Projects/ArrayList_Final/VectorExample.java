package ArrayList_Final;

import java.util.Vector;

public class VectorExample {
   
	public static void main(String[] args) {
 
        Vector<String> mammals = new Vector<>();
        mammals.add("Dog");      
        mammals.add("Horse");      
        mammals.add(1,"Cat");    

        System.out.println("Vector: " + mammals);

        Vector<String> animals = new Vector<>();
        animals.add("Crocodile");  

        animals.addAll(mammals);

        System.out.println("New Vector: " + animals);

    }

}

/*
 * When we say that a Vector in Java is synchronized, it means that Vector is thread-safe. 
 * In other words, multiple threads can safely access and modify the elements of a Vector 
 * at the same time without causing data corruption or inconsistencies.
 * This synchronization happens because Vector methods (like add(), get(), remove(), etc.) 
 * are internally synchronized, meaning that only one thread can access any of these methods 
 * at a time. When a thread accesses a synchronized method, it acquires a lock, and 
 * no other thread can access that method until the first thread releases the lock. 
 * However, this comes at a cost: synchronization can reduce performance, especially 
 * when there are many threads trying to access the Vector concurrently. 
 * For this reason, modern alternatives like ArrayList are often preferred 
 * when thread safety is not a concern.
 * 
 * When we say that Vector contains many legacy methods that are not part of the Collections
 * Framework, we are referring to methods that were designed before the Collections Framework 
 * was introduced in Java 2 (Java 1.2), 
 * which brought a more standardized and powerful way of handling collections.
 * 
 * The Collections Framework introduced a set of interfaces and 
 * classes (such as List, Set, Map, and Queue) that provide a unified way of working 
 * with collections. These newer classes like ArrayList, HashSet, LinkedList, and 
 * others follow the Collection interface and implement its methods like add(), remove(),
 * size(), etc.
 * 
 * On the other hand, Vector was part of the earlier versions of Java and had its own set 
 * of methods that were not aligned with the modern Collections Framework. These methods 
 * are considered "legacy" because they existed before the more modern, standardized 
 * framework was introduced.
 * 
 * Some examples of these legacy methods in Vector are:
 * 
 * addElement() – Adds an element to the Vector. This method was used in the Vector class 
 * before the List interface (which includes the add() method) was introduced.
 * 
 * removeElement() – Removes an element by value, a method specific to Vector, 
 * but not part of the List interface.
 * 
 * elementAt() – Retrieves the element at a specified index, a method that was used in 
 * Vector but was later replaced by the get() method in modern List implementations.
 * 
 * setSize() – Sets the size of the Vector, which is not part of the List interface 
 * but was available in the old Vector API.
 * 
 * The Collections Framework has standardized methods and interfaces for handling collections,
 * making it easier to work with them. The older Vector methods are now considered less 
 * efficient and outdated. For instance, Vector is synchronized, which makes it thread-safe 
 * but also adds unnecessary overhead in scenarios where thread safety isn't required.
 * 
 * In short:
 * 
 * The Vector class is part of Java's older collection API and includes methods that are not 
 * part of the newer Collections Framework (introduced later).
 * 
 * These methods were replaced by more modern and standardized methods in classes like 
 * ArrayList, LinkedList, etc., which are more commonly used in current Java development.
 * 
 */
