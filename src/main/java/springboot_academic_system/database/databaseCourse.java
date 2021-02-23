package springboot_academic_system.database;

import org.hibernate.annotations.BatchSize;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "courses")
@NamedNativeQuery(name = "courses.findFacultiesByCourseId",
                query = "select * from ")
public class databaseCourse {

    @Id
    @Column(name = "course_id", length = 6)
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
//    @OneToMany(cascade = CascadeType.ALL)
//    List<databaseRESULT> results = new ArrayList<>();

    public databaseCourse(){

    }

    public databaseCourse(String name, int max_grades, int lectures,
                          databaseDepartment course_department, Set<databaseStudent> students,
                          Set<databaseFaculty> faculties) {
        this.name = name;
        this.max_grades = max_grades;
        this.lectures = lectures;
        this.course_department = course_department;
        this.students = students;
        this.faculties = faculties;
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
}
