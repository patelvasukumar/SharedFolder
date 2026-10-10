package week6_midterm;

public class StudentDriver {
    static Student[] students = {
        new Student(101, "Vasu", 4.0),
        new Student(202, "Kavya", 3.8),
        new Student(303, "Chaitanya", 3.9),
        new Student(404, "David", 3.7),
        new Student(505, "Emma", 4.0)
    };

    public static void main(String[] args) {
        double totalGrade = 0;

        for (Student s : students) {
            totalGrade += s.grade;
        }

        double averageGrade = totalGrade / students.length;
        System.out.println("Average grade: " + averageGrade);

        System.out.println("Students scoring above average:");
        for (Student s : students) {
            if (s.grade > averageGrade) {
                System.out.println(s.name);
            }
        }
    }
}