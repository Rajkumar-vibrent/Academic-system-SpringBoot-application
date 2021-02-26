package springboot_academic_system.database;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import springboot_academic_system.IdGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "faculty")
@NamedNativeQuery(name = "faculty.returnAll",
        query = "select * from databaseFaculty",
        resultClass = databaseFaculty.class)
@NamedNativeQuery(name = "faculty.returnOne",
        query = "select * from databaseFaculty where faculty_ID = ?",
        resultClass = databaseFaculty.class)

public class databaseFaculty implements Serializable {

    @Id
    @Column(name = "faculty_ID")
    @SequenceGenerator(name = "faculty_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "faculty_sequence")
    @GenericGenerator(
            name = "faculty_sequence",
            strategy = "springboot_academic_system.IdGenerator",
            parameters = {
                    @Parameter(name = IdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = IdGenerator.PREFIX_VALUE_PARAMETER, value = "P_"),
                    @Parameter(name = IdGenerator.NUMBER_FORMAT_PARAMETER, value = "%d") })
    private String faculty_id;

    @Column(name = "faculty_name", length = 50, nullable = true)
    private String faculty_name;

//    @Column(name = "DOB", nullable = true)
//    private LocalDate date_of_birth;

    @Column(name = "DOJ", nullable = false)
    private LocalDate date_of_joining;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(department entity ka dept_ID foreign key add karna hai)
//

//    @Column(name = "domain_of_work", nullable = true, length = 30)
//    private String DOW;

    @Column(name = "experience", nullable = true)
    private int experience;

    @Column(name = "email_ID", length = 100)
    private String email_id;

    @ManyToOne
    @JoinColumn(name = "dept_ID")
    private databaseDepartment faculty_department;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "faculty_course",
            joinColumns = { @JoinColumn(name = "fk_faculty") },
            inverseJoinColumns = { @JoinColumn(name = "fk_course")})
    private Set<databaseCourse> courses = new HashSet<databaseCourse>();

//    @Column(name = "gender")
//    private gender Gender;
//
//    @Column(name = "Level")
//    private professor Professor_level;

    public databaseFaculty(){

    }

    public databaseFaculty(String faculty_name, LocalDate date_of_joining, int experience, String email_id,
                           databaseDepartment faculty_department, Set<databaseCourse> courses) {
        this.faculty_name = faculty_name;
        this.date_of_joining = date_of_joining;
        this.experience = experience;
        this.email_id = email_id;
        this.faculty_department = faculty_department;
        this.courses = courses;
    }

    public String getFaculty_id() {
        return faculty_id;
    }

    public void setFaculty_id(String faculty_id) {
        this.faculty_id = faculty_id;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public void setFaculty_name(String faculty_name) {
        this.faculty_name = faculty_name;
    }

    public LocalDate getDate_of_joining() {
        return date_of_joining;
    }

    public void setDate_of_joining(LocalDate date_of_joining) {
        this.date_of_joining = date_of_joining;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public databaseDepartment getFaculty_department() {
        return faculty_department;
    }

    public void setFaculty_department(databaseDepartment faculty_department) {
        this.faculty_department = faculty_department;
    }

    public Set<databaseCourse> getCourses() {
        return courses;
    }

    public void setCourses(Set<databaseCourse> courses) {
        this.courses = courses;
    }

    //helper functions to update associated entities
    public void addCourse(Optional<databaseCourse> c){
        this.courses.add(c.get());
        c.get().getFaculties().add(this);
    }

    public void removeCourse(Optional<databaseCourse> c){
        this.courses.remove(c.get());
        c.get().getFaculties().remove(c);
    }

    public void addDepartment(databaseDepartment d){
        this.setFaculty_department(d);
        d.getFaculty_list().add(this);
    }
}
