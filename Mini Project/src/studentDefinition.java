import java.util.ArrayList;

enum Course {
    JAVA,
    NET,
    C
}

public class studentDefinition extends StudentManagementSystem {
    public String id;
    public String name;
    public int semester;
    public ArrayList<Course> courses = new ArrayList<>();
    public ArrayList<Integer> scores = new ArrayList<>();

    public studentDefinition(String id, String name, int semester) {
        this.id = id;
        this.name = name;
        this.semester = semester;
    }

    public void addCourse(Course course, int score) {
        courses.add(course);
        scores.add(score);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public int getSemester() {
        return semester;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
    public String getGrade(Course course, int score) {
        if (course == Course.JAVA) {
            if (score >= 85) return "A";
            else if (score >= 70) return "B";
            else if (score >= 50) return "C";
            else return "D";
        } else if (course == Course.NET) {
            if (score >= 80) return "A";
            else if (score >= 60) return "B";
            else if (score >= 40) return "C";
            else return "D";
        } else if (course == Course.C) {
            if (score >= 75) return "A";
            else if (score >= 55) return "B";
            else if (score >= 35) return "C";
            else return "D";
        } else {
            System.out.println("Invalid course");
            return "";
        }
    }
}