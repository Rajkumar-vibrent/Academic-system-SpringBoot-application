package springboot_academic_system.student;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import springboot_academic_system.Gender;
import springboot_academic_system.IdGenerator;
import springboot_academic_system.course.databaseCourse;
import springboot_academic_system.department.databaseDepartment;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student")
public class databaseStudent {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    @GenericGenerator(
            name = "student_sequence",
            strategy = "springboot_academic_system.IdGenerator",
            parameters = {
                    @Parameter(name = IdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "B_"),
                    @Parameter(name = IdGenerator.NUMBER_FORMAT_PARAMETER, value = "%02d") })
    private String student_id;

    @Column(name = "first_name")
    private String student_first_name;

    @Column(name = "last_name")
    private String student_last_name;

    @Column(name = "email_id")
    private String email_id;


    // mapping ManyToMany from student to course

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<databaseCourse> courseList = new HashSet<>();

    @Transient
    private Set<String> courseNameList = new HashSet<>();



    // mapping ManyToOne from student to department

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dept_id")
    private databaseDepartment dept;

    @Column(name = "department_name")
    private String department_name;



    @Enumerated
    @Column(name = "gender")
    private Gender gender;

    public databaseStudent() {

    }

    public databaseStudent(String student_first_name, String student_last_name, Gender gender, String email_id) {
        this.student_first_name = student_first_name;
        this.student_last_name = student_last_name;
        this.gender = gender;
        this.email_id = email_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_first_name() {
        return student_first_name;
    }

    public void setStudent_first_name(String student_first_name) {
        this.student_first_name = student_first_name;
    }

    public String getStudent_last_name() {
        return student_last_name;
    }

    public void setStudent_last_name(String student_last_name) {
        this.student_last_name = student_last_name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }



    // getter and setters for student - course relationship

    public void setCourseList(databaseCourse course) {
        this.courseList.add(course);
    }

    public Set<String> getCourseNameList() {
        for(databaseCourse c: courseList){
            courseNameList.add(c.getCourse_name());
        }
        return courseNameList;
    }



    // getter and setter for student - department relationship

    public void setDept(databaseDepartment dept) {
        this.dept = dept;
    }

    public String getDepartment_name() {
        department_name = dept.getDept_name();
        return department_name;
    }

}