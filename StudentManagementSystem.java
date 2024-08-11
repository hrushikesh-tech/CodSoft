import java.io.*;
import java.util.*;

public class StudentManagementSystem {

  
    public static class Student {
        private String name;
        private String rollNumber;
        private String grade;

        public Student(String name, String rollNumber, String grade) {
            this.name = name;
            this.rollNumber = rollNumber;
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRollNumber() {
            return rollNumber;
        }

        public void setRollNumber(String rollNumber) {
            this.rollNumber = rollNumber;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
        }
    }

    public static class ManagementSystem {
        private Map<String, Student> students = new HashMap<>();
        private static final String FILE_NAME = "students.txt";

  
        public void addStudent(Student student) {
            students.put(student.getRollNumber(), student);
        }


        public void removeStudent(String rollNumber) {
            students.remove(rollNumber);
        }

        public Student searchStudent(String rollNumber) {
            return students.get(rollNumber);
        }

        public void displayAllStudents() {
            if (students.isEmpty()) {
                System.out.println("No students found.");
            } else {
                for (Student student : students.values()) {
                    System.out.println(student);
                }
            }
        }

        public void saveToFile() throws IOException {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                for (Student student : students.values()) {
                    writer.write(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
                    writer.newLine();
                }
            }
        }


        public void loadFromFile() throws IOException {
            students.clear();
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        Student student = new Student(parts[0], parts[1], parts[2]);
                        addStudent(student);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ManagementSystem sms = new ManagementSystem();

   
        try {
            sms.loadFromFile();
        } catch (IOException e) {
            System.out.println("Error loading student data: " + e.getMessage());
        }

        while (true) {
            System.out.println("Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save and Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter roll number: ");
                    String rollNumber = scanner.nextLine();
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();

                    if (name.isEmpty() || rollNumber.isEmpty() || grade.isEmpty()) {
                        System.out.println("All fields must be filled.");
                        break;
                    }

                    Student student = new Student(name, rollNumber, grade);
                    sms.addStudent(student);
                    System.out.println("Student added.");
                    break;

                case 2:
                    System.out.print("Enter roll number to remove: ");
                    String rollNumToRemove = scanner.nextLine();
                    sms.removeStudent(rollNumToRemove);
                    System.out.println("Student removed.");
                    break;

                case 3:
                    System.out.print("Enter roll number to search: ");
                    String rollNumToSearch = scanner.nextLine();
                    Student searchedStudent = sms.searchStudent(rollNumToSearch);
                    if (searchedStudent != null) {
                        System.out.println("Student found: " + searchedStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    sms.displayAllStudents();
                    break;

                case 5:
                    try {
                        sms.saveToFile();
                        System.out.println("Data saved. Exiting...");
                    } catch (IOException e) {
                        System.out.println("Error saving student data: " + e.getMessage());
                    }
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
