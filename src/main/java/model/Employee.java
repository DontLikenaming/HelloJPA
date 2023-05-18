package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "employees")
@Setter
@Getter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID", nullable = false)
    private Long empid;

    @Column(name = "FIRST_NAME", nullable = true)
    private String fname;

    @Column(name = "LAST_NAME", nullable = true)
    private String lname;

    @Column(name = "EMAIL", nullable = true)
    private String email;

    @Column(name = "PHONE_NUMBER", nullable = true)
    private String phone;

    @Column(name = "HIRE_DATE", nullable = true)
    private Date hdate;

    @Column(name = "JOB_ID", nullable = true)
    private String jobid;

    @Column(name = "SALARY", nullable = true)
    private Integer sal;

    @Column(name = "COMMISSION_PCT", precision = 3, scale = 2, nullable = true)
    private BigDecimal comm;

    @Column(name = "MANAGER_ID", nullable = true)
    private Integer mgrid;

    @Column(name = "DEPARTMENT_ID", nullable = true)
    private Long deptid;

    @ManyToOne  // 테이블 연관 관계 : 다 : 1
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    // departments의 DEPARTMENT_ID 컬럼과 조인
    private Department department;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Employee{");
        sb.append("empid=").append(empid);
        sb.append(", fname='").append(fname).append('\'');
        sb.append(", lname='").append(lname).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", hdate=").append(hdate);
        sb.append(", jobid='").append(jobid).append('\'');
        sb.append(", sal=").append(sal);
        sb.append(", comm=").append(comm);
        sb.append(", mgrid=").append(mgrid);
        sb.append(", deptid=").append(deptid);
        sb.append('}');
        return sb.toString();
    }
}
