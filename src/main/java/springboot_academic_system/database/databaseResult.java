package springboot_academic_system.database;

import javax.persistence.*;
import java.time.LocalDate;



@Entity
@Table(name = "result")
public class databaseResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", nullable = false)
    private databaseCourse course;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", nullable = false)
    private databaseStudent student;

    @Column(name = "date_of_completion")
    private LocalDate DOC;

    @Column(name = "grades")
    private int grades;


    public databaseResult(){

    }

    public databaseResult(LocalDate DOC, int grades) {
        this.DOC = DOC;
        this.grades = grades;
    }

    public LocalDate getDOC() {
        return DOC;
    }

    public void setDOC(LocalDate DOC) {
        this.DOC = DOC;
    }

    public int getGrades() {
        return grades;
    }

    public void setGrades(int grades) {
        this.grades = grades;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
