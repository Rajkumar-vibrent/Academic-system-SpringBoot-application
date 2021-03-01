package springboot_academic_system.student;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import springboot_academic_system.Gender;
import springboot_academic_system.IdGenerator;
import springboot_academic_system.course.DatabaseCourse;
import springboot_academic_system.department.DatabaseDepartment;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class DatabaseStudent implements Serializable {

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
    private String studentId;

    @Column(name = "first_name")
    private String studentFirstName;

    @Column(name = "last_name")
    private String studentLastName;

    @Column(name = "email_id")
    private String emailId;


    // mapping ManyToMany from student to course

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<DatabaseCourse> courseSet = new HashSet<>();

    @Transient
    private Set<String> courseNameSet = new HashSet<>();



    // mapping ManyToOne from student to department

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dept_id")
    private DatabaseDepartment dept;

    @Transient
    private String departmentName;



    @Enumerated
    @Column(name = "gender")
    private Gender gender;

    public DatabaseStudent() {

    }

    public DatabaseStudent(String studentFirstName, String studentLastName, Gender gender, String emailId) {
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.gender = gender;
        this.emailId = emailId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getemailId() {
        return emailId;
    }

    public void setemailId(String emailId) {
        this.emailId = emailId;
    }



    // getter and setters for student - course relationship

    public void setCourseList(DatabaseCourse course) {
        this.courseSet.add(course);
    }

    public Set<String> getCourseNameList() {
        for(DatabaseCourse c: courseSet){
            courseNameSet.add(c.getCourseName());
        }
        return courseNameSet;
    }



    // getter and setter for student - department relationship

    public void setDept(springboot_academic_system.department.DatabaseDepartment dept) {
        this.dept = dept;
    }

    public String getdepartmentName() {
        departmentName = dept.getDeptName();
        return departmentName;
    }

}