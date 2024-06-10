package streamsExamples;

import static java.lang.Integer.parseInt;

public class CourseGrade
{
    private int courseID;
    private String studentID;
    private int grade;

    public CourseGrade(String str)
    {
        String[] strings=str.split(" ");
        setCourseID(parseInt(strings[0]));
        setStudentID(strings[1]);
        setGrade(parseInt(strings[2]));
    }
    public CourseGrade(int courseID, String studentID, int grade)
    {
        this.courseID = courseID;
        this.studentID = studentID;
        this.grade = grade;
    }

    public int getCourseID()
    {
        return courseID;
    }

    public void setCourseID(int courseID)
    {
        this.courseID = courseID;
    }

    public String getStudentID()
    {
        return studentID;
    }

    public void setStudentID(String studentID)
    {
        this.studentID = studentID;
    }

    public int getGrade()
    {
        return grade;
    }

    public void setGrade(int grade)
    {
        this.grade = grade;
    }

    @Override
    public String toString()
    {
        return "CourseGrade{" +
                "courseID=" + courseID +
                ", studentID='" + studentID + '\'' +
                ", grade=" + grade +
                '}';
    }
}
