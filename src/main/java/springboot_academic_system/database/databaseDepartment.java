package springboot_academic_system.database;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
@NamedNativeQuery(name = "department.returnAll",
        query = "select * from databaseDepartment",
        resultClass = databaseDepartment.class)
@NamedNativeQuery(name = "department.returnOne",
        query = "select * from databaseDepartment where dept_ID = ?",
        resultClass = databaseDepartment.class)

public class databaseDepartment {

    @Id
    @Column(name = "dept_ID", nullable = false)
    private String department_id;

    @Column(name = "dept_name", unique = true)
    private String department_name;

    @Column(name = "HOD_name", unique = true)
    private String hod_name;

    @Column(name = "establishment_date")
    private LocalDate DOE;

    @OneToMany(mappedBy = "course_department")
    private List<databaseCourse> course_list = new ArrayList<>();

    @OneToMany(mappedBy = "faculty_department")
    private List<databaseFaculty> faculty_list = new ArrayList<>();

    public databaseDepartment(String department_name, String hod_name, LocalDate DOE,
                              List<databaseFaculty> faculty_list) {
        this.department_name = department_name;
        this.hod_name = hod_name;
        this.DOE = DOE;
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

    public List<databaseFaculty> getFaculty_list() {
        return faculty_list;
    }

    public void setFaculty_list(List<databaseFaculty> faculty_list) {
        this.faculty_list = faculty_list;
    }


}
