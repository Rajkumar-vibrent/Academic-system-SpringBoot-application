package springboot_academic_system.course;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import springboot_academic_system.IdGenerator;
import springboot_academic_system.department.databaseDepartment;
import springboot_academic_system.faculty.databaseFaculty;
import springboot_academic_system.student.databaseStudent;

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
public class databaseCourse implements Serializable {

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
    private String course_id;

    @Column(name = "course_name")
    private String course_name;

    @Column(name = "max_grades")
    private int max_grades;

    @Column(name = "Preferred_year")
    private int year;       // limit its range from 1-4



    // mapping course and department    - owner

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private databaseDepartment dept;

    @Column(name = "dept_name")
    private String dept_name;



    // mapping course and student

    @ManyToMany(mappedBy = "courseList")
    private Set<databaseStudent> studentList = new HashSet<>();

    @Transient
    private List<String> studentNameList = new ArrayList<>();



    // mapping course and faculty

    @ManyToMany(mappedBy = "courseSet")
    private Set<databaseFaculty> facultySet = new HashSet<databaseFaculty>();

    @Transient
    private List<String> facultyNameSet = new ArrayList<>();



    public databaseCourse(){

    }

    public databaseCourse(String course_name, int max_grades, int year) {
        this.course_name = course_name;
        this.max_grades = max_grades;
        this.year = year;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getMax_grades() {
        return max_grades;
    }

    public void setMax_grades(int max_grades) {
        this.max_grades = max_grades;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }




    // getter and setter for course - department relationship

    public void setDept(databaseDepartment dept) {
        this.dept = dept;
        this.dept_name = dept.getDept_name();
    }

    public String getDept_name() {
        return dept_name;
    }



    // getter and setter for course - student relationship

    public void setStudentList(databaseStudent student){
        this.studentList.add(student);
    }

    public List<String> getStudentNameList(){
        for(databaseStudent s: studentList){
            studentNameList.add(s.getStudent_first_name() + " " + s.getStudent_last_name());
        }
        return studentNameList;
    }


    // getter and setter for course - faculty relationship

    public void setFacultySet(databaseFaculty faculty){
        this.facultySet.add(faculty);
    }

    public List<String> getFacultyNameSet(){
        for(databaseFaculty f: facultySet){
            facultyNameSet.add(f.getFaculty_name());
        }
        return facultyNameSet;
    }
}