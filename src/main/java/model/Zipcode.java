package model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "zipcode")
@Data
public class Zipcode {

    @Column(name = "ZIPCODE")
    private String zip;

    @Column(name = "SIDO")
    private String sido;

    @Column(name = "GUGUN")
    private String gugun;

    @Column(name = "DONG")
    private String dong;

    @Column(name = "RI")
    private String ri;

    @Column(name = "BUNJI")
    private String bunji;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private String seq;
}
