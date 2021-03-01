package springboot_academic_system.course;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import springboot_academic_system.IdGenerator;
import springboot_academic_system.department.DatabaseDepartment;
import springboot_academic_system.faculty.DatabaseFaculty;
import springboot_academic_system.student.DatabaseStudent;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course")
public class DatabaseCourse implements Serializable {

    @Id
    @Column(name = "course_code", length = 6)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
    @GenericGenerator(
            name = "course_sequence",
            strategy = "springboot_academic_system.IdGenerator",
            parameters = {
                    @Parameter(name = IdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "C_"),
                    @Parameter(name = IdGenerator.NUMBER_FORMAT_PARAMETER, value = "%02d") })
    private String courseId;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "max_grades")
    private int maxGrades;

    @Column(name = "Preferred_year")
    private int year;       // limit its range from 1-4



    // mapping course and department    - owner

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private DatabaseDepartment dept;

    @Column(name = "dept_name")
    private String deptName;



    // mapping course and student

    @ManyToMany(mappedBy = "courseSet")
    private Set<DatabaseStudent> studentSet = new HashSet<>();

    @Transient
    private Set<String> studentNameSet = new HashSet<>();



    // mapping course and faculty

    @ManyToMany(mappedBy = "courseSet")
    private Set<DatabaseFaculty> facultySet = new HashSet<DatabaseFaculty>();

    @Transient
    private Set<String> facultyNameSet = new HashSet<>();



    public DatabaseCourse(){

    }

    public DatabaseCourse(String courseName, int maxGrades, int year) {
        this.courseName = courseName;
        this.maxGrades = maxGrades;
        this.year = year;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getMaxGrades() {
        return maxGrades;
    }

    public void setMaxGrades(int maxGrades) {
        this.maxGrades = maxGrades;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }




    // getter and setter for course - department relationship

    public void setDept(springboot_academic_system.department.DatabaseDepartment dept) {
        this.dept = dept;
        this.deptName = dept.getDeptName();
    }

    public String getDeptName() {
        return deptName;
    }



    // getter and setter for course - student relationship

    public void setStudentList(DatabaseStudent student){
        this.studentSet.add(student);
    }

    public Set<String> getStudentNameSet(){
        for(DatabaseStudent s: studentSet){
            studentNameSet.add(s.getStudentFirstName() + " " + s.getStudentLastName());
        }
        return studentNameSet;
    }


    // getter and setter for course - faculty relationship

    public void setFacultySet(DatabaseFaculty faculty){
        this.facultySet.add(faculty);
    }

    public Set<String> getFacultyNameSet(){
        for(DatabaseFaculty f: facultySet){
            facultyNameSet.add(f.getFacultyName());
        }
        return facultyNameSet;
    }
}