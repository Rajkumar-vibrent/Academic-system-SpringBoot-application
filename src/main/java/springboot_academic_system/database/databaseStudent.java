package springboot_academic_system.database;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "students")
public class databaseStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int student_ID;

    @Column(name = "student_first_name")
    private String first_name;

    @Column(name = "student_last_name")
    private String last_name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private gender gender;

    @Column(name = "Date_of_birth")
    private LocalDate DOB;

    @Column(name = "email_ID")
    private String email_ID;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_course",
            joinColumns = { @JoinColumn(name = "fk_student") },
            inverseJoinColumns = { @JoinColumn(name = "fk_course")})
    private Set<databaseCourse> courses = new HashSet<databaseCourse>();
//
//    @OneToMany(cascade = CascadeType.ALL)
//    List<databaseRESULT> results = new ArrayList<>();

    public databaseStudent(){

    }

    public databaseStudent(String first_name, String last_name, gender gender, LocalDate DOB, String email_ID) {
        super();
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.DOB = DOB;
        this.email_ID = email_ID;
    }


    public int getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(int student_ID) {
        this.student_ID = student_ID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public gender getGender() {
        return gender;
    }

    public void setGender(gender gender) {
        this.gender = gender;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public String getEmail_ID() {
        return email_ID;
    }

    public void setEmail_ID(String email_ID) {
        this.email_ID = email_ID;
    }
}
