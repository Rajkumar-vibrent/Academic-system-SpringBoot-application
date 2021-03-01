package springboot_academic_system.department;

import springboot_academic_system.faculty.DatabaseFaculty;
import springboot_academic_system.student.DatabaseStudent;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "department")
public class DatabaseDepartment {

    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int deptId;

    @Column(name = "department_name", nullable = false)
    private String deptName;

    @Column(name = "establishment_date")
    private LocalDate establishmentDate;

    @Column(name = "head_of_department")
    private String hodName;


    //mapping department and faculty

    @OneToMany(mappedBy = "dept")
    private List<DatabaseFaculty> facultyList = new ArrayList<>();

    @Transient
    private Set<String> facultyNameSet = new HashSet<>();


    //mapping department and course

    @OneToMany(mappedBy =  "dept")
    private List<springboot_academic_system.course.DatabaseCourse> courseList = new ArrayList<>();

    @Transient
    private Set<String> courseNameSet = new HashSet<>();



    // mapping department and student

    @OneToMany(mappedBy = "dept")
    private List<DatabaseStudent> studentList = new ArrayList<>();

    @Transient
    private Set<String> studentNameSet = new HashSet<>();




    public DatabaseDepartment(){

    }

    public DatabaseDepartment(String deptName, LocalDate establishmentDate,
                    String hodName, Set<String> facultyNameSet,
                    Set<String> courseNameSet) {
        this.deptName = deptName;
        this.establishmentDate = establishmentDate;
        this.hodName = hodName;
        this.facultyNameSet = facultyNameSet;
        this.courseNameSet = courseNameSet;
    }


    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public LocalDate getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(LocalDate establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public String getHodName() {
        return hodName;
    }

    public void setHodName(String hodName) {
        this.hodName = hodName;
    }



    // getter and setter for department - faculty relationship

    public void setFacultyList(DatabaseFaculty faculty) {       //add faculty name to facultyList
        this.facultyList.add(faculty);
    }

    public Set<String> getFacultyNameSet() {          //get faculty name list for each department
        for(DatabaseFaculty f: this.facultyList){
            facultyNameSet.add(f.getFacultyName());
        }
        return facultyNameSet;
    }


    // getter and setter for department - course relationship

    public void setCourseList(springboot_academic_system.course.DatabaseCourse course) {      // add course name to courseList
        this.courseList.add(course);
    }

    public Set<String> getCourseNameSet() {           // get course name list for each course
        for(springboot_academic_system.course.DatabaseCourse c: this.courseList){
            courseNameSet.add(c.getCourseName());
        }
        return courseNameSet;
    }


    // getter and setter for department - student relationship

    public void setStudentList(DatabaseStudent student){        // add student full name to studentList
        this.studentList.add(student);
    }

    public Set<String> getStudentNameSet(){               // get student name list for each student
        for(DatabaseStudent s: this.studentList){
            studentNameSet.add(s.getStudentFirstName() + " " + s.getStudentLastName());
        }
        return studentNameSet;
    }
}
