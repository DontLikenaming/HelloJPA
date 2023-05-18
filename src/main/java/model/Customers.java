package model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
@Data
public class Customers {

    @Id
    private String userid;

    private String name;

    private Integer age;

    private String grade;

    private String job;

    private Integer milege;
}
