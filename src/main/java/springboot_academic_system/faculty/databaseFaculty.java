package springboot_academic_system.faculty;


import org.hibernate.annotations.GenericGenerator;
import springboot_academic_system.IdGenerator;
import springboot_academic_system.course.databaseCourse;
import springboot_academic_system.department.databaseDepartment;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "faculty")
public class databaseFaculty{

    @Id
    @Column(name = "faculty_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "faculty_sequence")
    @GenericGenerator(
            name = "faculty_sequence",
            strategy = "springboot_academic_system.IdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = IdGenerator.VALUE_PREFIX_PARAMETER, value = "P_"),
                    @org.hibernate.annotations.Parameter(name = IdGenerator.NUMBER_FORMAT_PARAMETER, value = "%02d") })
    private String faculty_id;

    @Column(name = "faculty_name")
    private String faculty_name;

    @Column(name = "experience")
    private int experience;

    @Column(name = "joining_date")
    private LocalDate joining_date;

    @Column(name = "work_domain")
    private String work_domain;


    //mapping faculty and department    - owner

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private databaseDepartment dept;

    @Column(name = "dept_name")
    private String dept_name;




    // mapping faculty and course   - owner

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "faculty_course",
                joinColumns = @JoinColumn(name = "course_id"),
                inverseJoinColumns = @JoinColumn(name = "faculty_id"))
    private Set<databaseCourse> courseSet = new HashSet<>();

    @Transient
    private Set<String> courseNameSet = new HashSet<>();



    public databaseFaculty(){

    }

    public databaseFaculty(String faculty_name, int experience, LocalDate joining_date,
                           String work_domain) {
        this.faculty_name = faculty_name;
        this.experience = experience;
        this.joining_date = joining_date;
        this.work_domain = work_domain;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public void setFaculty_name(String faculty_name) {
        this.faculty_name = faculty_name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public LocalDate getJoining_date() {
        return joining_date;
    }

    public void setJoining_date(LocalDate joining_date) {
        this.joining_date = joining_date;
    }

    public String getWork_domain() {
        return work_domain;
    }

    public void setWork_domain(String work_domain) {
        this.work_domain = work_domain;
    }




    // getter and setter for faculty - department relationship

    public void setDept(databaseDepartment dept) {
        this.dept = dept;
    }

    public String getDept_name() {
        dept_name = this.dept.getDept_name();
        return dept_name;
    }



    // getter and setter for faculty - course relationship

    public void setCourseSet(databaseCourse course){
        this.courseSet.add(course);
    }

    public Set<String> getCourseNameSet(){
        for(databaseCourse c: courseSet){
            courseNameSet.add(c.getCourse_name());
        }
        return courseNameSet;
    }

}