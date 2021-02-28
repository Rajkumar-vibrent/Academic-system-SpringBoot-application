package springboot_academic_system.department;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import springboot_academic_system.IdGenerator;
import org.hibernate.annotations.*;
import springboot_academic_system.course.databaseCourse;
import springboot_academic_system.faculty.databaseFaculty;
import springboot_academic_system.student.databaseStudent;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "department")
public class databaseDepartment{

    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int dept_id;

    @Column(name = "department_name", nullable = false)
    private String dept_name;

    @Column(name = "establishment_date")
    private LocalDate establishment_date;

    @Column(name = "head_of_department")
    private String hod_name;


    //mapping department and faculty

    @OneToMany(mappedBy = "dept")
    private List<databaseFaculty> facultyList = new ArrayList<>();

    @Transient
    private List<String> facultyNameList = new ArrayList<>();


    //mapping department and course

    @OneToMany(mappedBy =  "dept")
    private List<databaseCourse> courseList = new ArrayList<>();

    @Transient
    private List<String> courseNameList = new ArrayList<>();



    // mapping department and student

    @OneToMany(mappedBy = "dept")
    private List<databaseStudent> studentList = new ArrayList<>();

    @Transient
    private List<String> studentNameList = new ArrayList<>();




    public databaseDepartment(){

    }

    public databaseDepartment(String dept_name, LocalDate establishment_date,
                              String hod_name, List<String> facultyNameList,
                              List<String> courseNameList) {
        this.dept_name = dept_name;
        this.establishment_date = establishment_date;
        this.hod_name = hod_name;
        this.facultyNameList = facultyNameList;
        this.courseNameList = courseNameList;
    }


    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public LocalDate getEstablishment_date() {
        return establishment_date;
    }

    public void setEstablishment_date(LocalDate establishment_date) {
        this.establishment_date = establishment_date;
    }

    public String getHod_name() {
        return hod_name;
    }

    public void setHod_name(String hod_name) {
        this.hod_name = hod_name;
    }



    // getter and setter for department - faculty relationship

    public void setFacultyList(databaseFaculty faculty) {       //add faculty name to facultyList
        this.facultyList.add(faculty);
    }

    public List<String> getFacultyNameList() {          //get faculty name list for each department
        for(databaseFaculty f: this.facultyList){
            facultyNameList.add(f.getFaculty_name());
        }
        return facultyNameList;
    }


    // getter and setter for department - course relationship

    public void setCourseList(databaseCourse course) {      // add course name to courseList
        this.courseList.add(course);
    }

    public List<String> getCourseNameList() {           // get course name list for each course
        for(databaseCourse c: this.courseList){
            courseNameList.add(c.getCourse_name());
        }
        return courseNameList;
    }


    // getter and setter for department - student relationship

    public void setStudentList(databaseStudent student){        // add student full name to studentList
        this.studentList.add(student);
    }

    public List<String> getStudentNameList(){               // get student name list for each student
        for(databaseStudent s: this.studentList){
            studentNameList.add(s.getStudent_first_name() + " " + s.getStudent_last_name());
        }
        return studentNameList;
    }
}
