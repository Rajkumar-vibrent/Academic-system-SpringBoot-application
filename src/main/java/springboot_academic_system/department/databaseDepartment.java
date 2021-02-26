package springboot_academic_system.department;

import org.hibernate.annotations.GenericGenerator;
import springboot_academic_system.IdGenerator;
import springboot_academic_system.course.databaseCourse;
import springboot_academic_system.faculty.databaseFaculty;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
//@NamedNativeQuery(name = "department.returnAll",
//        query = "select * from databaseDepartment",
//        resultClass = databaseDepartment.class)
//@NamedNativeQuery(name = "department.returnOne",
//        query = "select * from databaseDepartment where dept_ID = ?",
//        resultClass = databaseDepartment.class)

public class databaseDepartment {

    @Id
    @Column(name = "dept_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_sequence")
    @GenericGenerator(
            name = "department_sequence",
            strategy = "springboot_academic_system.IdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = IdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = IdGenerator.PREFIX_VALUE_PARAMETER, value = "DEPT_"),
                    @org.hibernate.annotations.Parameter(name = IdGenerator.NUMBER_FORMAT_PARAMETER, value = "%d") })
    private String department_id;

    @Column(name = "dept_name", unique = true)
    private String department_name;

    @Column(name = "HOD_name", unique = true)
    private String hod_name;

    @Column(name = "establishment_date")
    private LocalDate DOE;

    @OneToMany(mappedBy = "course_department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<databaseCourse> course_list = new ArrayList<>();

    @OneToMany(mappedBy = "faculty_department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<databaseFaculty> faculty_list = new ArrayList<>();

    public databaseDepartment(){

    }

    public databaseDepartment(String department_name, String hod_name, LocalDate DOE,
                              List<databaseCourse> course_list, List<databaseFaculty> faculty_list) {
        this.department_name = department_name;
        this.hod_name = hod_name;
        this.DOE = DOE;
        this.course_list = course_list;
        this.faculty_list = faculty_list;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getHod_name() {
        return hod_name;
    }

    public void setHod_name(String hod_name) {
        this.hod_name = hod_name;
    }

    public LocalDate getDOE() {
        return DOE;
    }

    public void setDOE(LocalDate DOE) {
        this.DOE = DOE;
    }

    public List<databaseCourse> getCourse_list() {
        return course_list;
    }

    public void setCourse_list(List<databaseCourse> course_list) {
        this.course_list = course_list;
    }

    public List<databaseFaculty> getFaculty_list() {
        return faculty_list;
    }

    public void setFaculty_list(List<databaseFaculty> faculty_list) {
        this.faculty_list = faculty_list;
    }
}
