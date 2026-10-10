package multiAaray;
public class StudentSubject {

    public static void main(String[] args) {
        
        int[][] marks = {{85, 72, 89}, {90, 88, 92}, {78, 95, 84}};
        int[] subjects = new int[3]; // Holds total marks for each subject
        int[] names = new int[3];    // Holds total marks for each student
        
        for (int i = 0; i < marks.length; i++) { // Iterating over rows (students)
            
            for (int j = 0; j < marks[i].length; j++) { // Iterating over columns (subjects)
                
                names[i] += marks[i][j];      // Total marks for each student
                subjects[j] += marks[i][j];  // Total marks for each subject
                
            }
            
        }

        // Printing the results
        System.out.println("Total marks for each student:");
        for (int i = 0; i < names.length; i++) {
            System.out.println("Student " + (i + 1) + ": " + names[i]);
        }

        System.out.println("\nTotal marks for each subject:");
        for (int j = 0; j < subjects.length; j++) {
            System.out.println("Subject " + (j + 1) + ": " + subjects[j]);
        }
    }
}
