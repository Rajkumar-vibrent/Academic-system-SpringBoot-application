package springboot_academic_system.result;


import springboot_academic_system.course.databaseCourse;
import springboot_academic_system.student.databaseStudent;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "result")
public class databaseResult implements Serializable {

    @Id
    @Column(name = "result_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int result_id;



    // ManyToOne mapping from result to course

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_name", referencedColumnName = "course_name")
    private databaseCourse course;

    @Transient
    private String course_name;



    // ManyToMany mapping from result to student

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "first_name", referencedColumnName = "first_name"),
            @JoinColumn(name = "last_name", referencedColumnName = "last_name")
    })
    private databaseStudent student;

    @Transient
    private String student_name;


    @Column(name = "score")
    private int score;

    @Column(name = "max_grades")
    private int max_grades;



    public databaseResult(){

    }

    public databaseResult(int score, int max_grades) {
        this.score = score;
        this.max_grades = max_grades;
    }


    public int getResult_id() {
        return result_id;
    }

    public void setResult_id(int result_id) {
        this.result_id = result_id;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMax_grades() {
        return max_grades;
    }




    // getter and setter for result - course relationship

    public void setCourse(databaseCourse course) {
        this.course = course;
        course_name = course.getCourse_name();
        max_grades = course.getMax_grades();
    }

    public String getCourse_name() {
        return course_name;
    }


    // getter and setter for result - student relationship

    public void setStudent(databaseStudent student) {
        this.student = student;
        student_name = student.getStudent_first_name() + " " + student.getStudent_last_name();
    }

    public String getStudent_name() {
        return student_name;
    }

}