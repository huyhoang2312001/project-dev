import java.util.ArrayList;
import java.util.Scanner;



class StudentManagementSystem{
    private ArrayList<studentDefinition> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void start () {

        System.out.println("WELCOME TO STUDENT MANAGEMENT");

        boolean isContinue = true;

        while(isContinue){
            showMenu();
            int selectedMenu = chooseMenu();

            switch (selectedMenu) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    findAndSortStudent();
                    break;
                case 3:
                    updateOrDeleteStudent();
                    break;
                case 4:
                    reportStudent();
                    break;
                case 5:
                    isContinue = false;
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
            System.out.println("Continue ? (Y/N)");
            String answer = scanner.nextLine().toUpperCase();
            isContinue = answer.equals("Y");
        }
    }

    private static void showMenu(){

        System.out.println("1. Create Student");
        System.out.println("2. Find and Sort Student");
        System.out.println("3. Update/Delete Student");
        System.out.println("4. Report Student");
        System.out.println("5. Exit");
        System.out.println("**(Please select 1 to Create, 2 to Find and Sort, 3 to Update/Delete, 4 to Report and 5 to Exit)**: ");
    }

    public int chooseMenu(){
        System.out.println("Choose Menu: ");
        int number = scanner.nextInt();
        return number;
    }


    public void createStudent() {

        int count = 0;
        Boolean isContinue = true;

        while(count < 10 || isContinue) {
            createNewStudent();
            count++;

            if(count >= 10) {
                System.out.println("Do you want to continue (Y/N)?");
                String answer = scanner.nextLine().toUpperCase();
                isContinue = answer.equals("Y");
            }
        }
    }

    public studentDefinition createNewStudent(){
        System.out.println("Enter student ID: ");
        String id = scanner.nextLine();

        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        System.out.println("Enter a semester");

        int semester = Integer.parseInt(scanner.nextLine());

        studentDefinition student = new studentDefinition(id, name, semester);
        students.add(student);
        return student;
    }


    private void findAndSortStudent() {
        System.out.println("Enter student name: ");
        String searchStudent = scanner.nextLine();

        ArrayList <studentDefinition> matchingStudent = new ArrayList<>();
        for(studentDefinition student : students){
            if(student.getName().toLowerCase().startsWith(searchStudent.toLowerCase())){
                matchingStudent.add(student);
            }
        }
        matchingStudent.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
        printStudents(matchingStudent);
    }

    private void updateOrDeleteStudent(){
        System.out.println("Enter student ID: ");
        String id = scanner.nextLine();

        studentDefinition student = findStudentById(id);
        if(student == null) {
            System.out.println("Not found Student ID");
            return;
        }

        System.out.println("Do you want to update (U) or delete (D) student");
        String choice = scanner.nextLine().toUpperCase();

        switch (choice) {

            case "U" :
                updateStudent(student);
                break;

            case "D":
                deleteStudent(student);
                break;

            default:
                System.out.println("invalid choice");

        }
    }
    public void updateStudent(studentDefinition student) {
        System.out.println("**Update student information:**");
        System.out.print("Enter student name: ");
        student.setName(scanner.nextLine());

        System.out.print("Enter student semester: ");
        int semester = Integer.parseInt(scanner.nextLine());
        student.setSemester(semester);

        System.out.println("Student infosrmation updated successfully.");
    }

    private void deleteStudent(studentDefinition student) {
        student.getCourses().clear();
        student.getScores().clear();
        students.remove(student);
        System.out.println("Student deleted successfully.");
    }
    private void reportStudent(){

        System.out.println("| Student name | Courses | Score | Grade |");
        System.out.println("| --- | --- | --- | --- |");

        for(studentDefinition student : students){
            System.out.println("| " + student.getName() + " |");

            for(int i = 0; i < student.getCourses().size(); i++) {

                Course course = student.getCourses().get(i);
                int score = student.getScores().get(i);
                String grade = student.getGrade(course, score);
                System.out.print(" " + course.name() + " | " + score + " | " + grade + " |");
            }
            System.out.println();
        }
    }

    public studentDefinition findStudentById(String id) {
        for(studentDefinition student : students) {
            if(student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void printStudents(ArrayList <studentDefinition> students){
        if(students.isEmpty()) {
            System.out.println("Not student found");
            return;
        }
        System.out.println("Match Student");
        for(studentDefinition student: students) {
            System.out.println("Student ID: " + student.getId());
            System.out.println("Student Name: " + student.getName());
            System.out.println("Semester: " + student.getSemester());
            System.out.println("------");
        }
    }

    public static void main(String[] args) {

        StudentManagementSystem studentManagementSystem = new StudentManagementSystem();

        studentManagementSystem.start();

    }
}


