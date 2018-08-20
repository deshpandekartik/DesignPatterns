package studentCoursesBackup.other;

public class Student
{
    public enum operation { INSERT , DELETE };;

    private int bNumber;
    private String course;
    private operation status;

    public Student(int a_bNo, String a_course, operation a_status)
    {
        this.bNumber = a_bNo;
        this.course = a_course;
        this.status = a_status;
    }

    public int getbNumber()
    {
        return this.bNumber;
    }

    public String getCourse()
    {
        return this.course;
    }

    public operation getStatus()
    {
        return this.status;
    }
};
