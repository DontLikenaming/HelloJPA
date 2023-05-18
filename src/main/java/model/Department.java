package model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "departments")
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID", nullable = false)
    private Long deptid;

    @Column(name = "DEPARTMENT_NAME", nullable = true)
    private String dname;

    @Column(name = "MANAGER_ID", nullable = true)
    private String mgrid;

    @Column(name = "LOCATION_ID", nullable = true)
    private String locid;





}
