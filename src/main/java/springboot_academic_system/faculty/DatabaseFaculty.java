package springboot_academic_system.faculty;


import org.hibernate.annotations.GenericGenerator;
import springboot_academic_system.IdGenerator;
import springboot_academic_system.course.DatabaseCourse;
import springboot_academic_system.department.DatabaseDepartment;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "faculty")
public class DatabaseFaculty{

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
    private String facultyId;

    @Column(name = "faculty_name")
    private String facultyName;

    @Column(name = "experience")
    private int experience;

    @Column(name = "joining_date")
    private LocalDate joiningDate;

    @Column(name = "work_domain")
    private String workDomain;


    //mapping faculty and department    - owner

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private DatabaseDepartment dept;

    @Column(name = "dept_name")
    private String deptName;




    // mapping faculty and course   - owner

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "faculty_course",
                joinColumns = @JoinColumn(name = "course_id"),
                inverseJoinColumns = @JoinColumn(name = "faculty_id"))
    private Set<DatabaseCourse> courseSet = new HashSet<>();

    @Transient
    private Set<String> courseNameSet = new HashSet<>();



    public DatabaseFaculty(){

    }

    public DatabaseFaculty(String facultyName, int experience, LocalDate joiningDate,
                           String workDomain) {
        this.facultyName = facultyName;
        this.experience = experience;
        this.joiningDate = joiningDate;
        this.workDomain = workDomain;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getWorkDomain() {
        return workDomain;
    }

    public void setWorkDomain(String workDomain) {
        this.workDomain = workDomain;
    }




    // getter and setter for faculty - department relationship

    public void setDept(DatabaseDepartment dept) {
        this.dept = dept;
    }

    public String getDeptName() {
        deptName = this.dept.getDeptName();
        return deptName;
    }



    // getter and setter for faculty - course relationship

    public void setCourseSet(DatabaseCourse course){
        this.courseSet.add(course);
    }

    public Set<String> getCourseNameSet(){
        for(DatabaseCourse c: courseSet){
            courseNameSet.add(c.getCourseName());
        }
        return courseNameSet;
    }

}