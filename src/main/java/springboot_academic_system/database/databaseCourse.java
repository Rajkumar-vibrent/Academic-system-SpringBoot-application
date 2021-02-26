package springboot_academic_system.database;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;
import springboot_academic_system.IdGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "courses")
public class databaseCourse {

    @Id
    @Column(name = "course_id", length = 6)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
    @GenericGenerator(
            name = "course_sequence",
            strategy = "springboot_academic_system.IdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = IdGenerator.PREFIX_VALUE_PARAMETER, value = "CN_"),
                    @org.hibernate.annotations.Parameter(name = IdGenerator.NUMBER_FORMAT_PARAMETER, value = "%d") })
    private String course_ID;

    @Column(name = "course_name")
    private String name;

    @Column(name = "max_grades")
    private int max_grades;

    @Column(name = "no_of_lectures")
    private int lectures;

    @ManyToOne
    @JoinColumn(name = "dept_ID")
    private databaseDepartment course_department;

    @ManyToMany(mappedBy = "courses")
    private Set<databaseStudent> students = new HashSet<>();

    @ManyToMany(mappedBy = "courses")
    private Set<databaseFaculty> faculties = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    List<databaseResult> results = new ArrayList<>();

    public databaseCourse(){

    }

    public databaseCourse(String name, int max_grades, int lectures,
                          databaseDepartment course_department, Set<databaseStudent> students,
                          Set<databaseFaculty> faculties, List<databaseResult> results) {
        this.name = name;
        this.max_grades = max_grades;
        this.lectures = lectures;
        this.course_department = course_department;
        this.students = students;
        this.faculties = faculties;
        this.results = results;
    }

    public String getCourse_ID() {
        return course_ID;
    }

    public void setCourse_ID(String course_ID) {
        this.course_ID = course_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMax_grades() {
        return max_grades;
    }

    public void setMax_grades(int max_grades) {
        this.max_grades = max_grades;
    }

    public int getLectures() {
        return lectures;
    }

    public void setLectures(int lectures) {
        this.lectures = lectures;
    }

    public databaseDepartment getCourse_department() {
        return course_department;
    }

    public void setCourse_department(databaseDepartment course_department) {
        this.course_department = course_department;
    }

    public Set<databaseStudent> getStudents() {
        return students;
    }

    public void setStudents(Set<databaseStudent> students) {
        this.students = students;
    }

    public Set<databaseFaculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(Set<databaseFaculty> faculties) {
        this.faculties = faculties;
    }

    public List<databaseResult> getResults() {
        return results;
    }

    public void setResults(List<databaseResult> results) {
        this.results = results;
    }

    public void setDepartment(databaseDepartment dept){
        this.setCourse_department(dept);
        dept.getCourse_list().add(this);
    }
}
