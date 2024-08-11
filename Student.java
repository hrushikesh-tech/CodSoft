import java.util.Scanner;

public class Student{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Student Grade Calculator");
        System.out.print("Enter number of students: ");
        int ns = sc.nextInt();
        int total = 0;

        for (int i = 1; i <= ns; i++) {
            System.out.print("Enter marks obtained for student " + i + ": ");
            int marks = sc.nextInt();
            total += marks;
        }

        double averagePercentage = (double) total / ns;
        char grade;

        if (averagePercentage >= 95) {
            grade = 'O';
        } else if (averagePercentage >= 85) {
            grade = 'A';
        } else if (averagePercentage >= 75) {
            grade = 'B';
        } else if (averagePercentage >= 65) {
            grade = 'C';
        } else if (averagePercentage >= 55) {
            grade = 'D';
        } else {
            grade = 'F'; 
        }

        System.out.println("\nTotal marks: " + total);
        System.out.printf("Average percentage gained: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);

        sc.close();
    }
}