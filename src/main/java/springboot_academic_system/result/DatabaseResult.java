package springboot_academic_system.result;


import springboot_academic_system.course.DatabaseCourse;
import springboot_academic_system.student.DatabaseStudent;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "result")
public class DatabaseResult implements Serializable {

    @Id
    @Column(name = "result_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int resultId;



    // ManyToOne mapping from result to course

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_name", referencedColumnName = "course_name")
    private DatabaseCourse course;

    @Transient
    private String courseName;



    // ManyToMany mapping from result to student

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "first_name", referencedColumnName = "first_name"),
            @JoinColumn(name = "last_name", referencedColumnName = "last_name")
    })
    private DatabaseStudent student;

    @Transient
    private String studentName;


    @Column(name = "score")
    private int score;

    @Column(name = "max_grades")
    private int maxGrades;



    public DatabaseResult(){

    }

    public DatabaseResult(int score, int maxGrades) {
        this.score = score;
        this.maxGrades = maxGrades;
    }


    public int getresultId() {
        return resultId;
    }

    public void setresultId(int resultId) {
        this.resultId = resultId;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMaxGrades() {
        return maxGrades;
    }




    // getter and setter for result - course relationship

    public void setCourse(DatabaseCourse course) {
        this.course = course;
        courseName = course.getCourseName();
        maxGrades = course.getMaxGrades();
    }

    public String getCourseName() {
        return courseName;
    }


    // getter and setter for result - student relationship

    public void setStudent(DatabaseStudent student) {
        this.student = student;
        studentName = student.getStudentFirstName() + " " + student.getStudentLastName();
    }

    public String getStudentName() {
        return studentName;
    }

}