package com.telusko.demo.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String firstName;
    private String gender;
    private String race;
    private String isSouthAfrican;
    private String idNumber;
    private String passportNumber;
    private String countryOfIssue;
    private String expiryDate;
    private String workPermint;
    private String workPermitDetails;
    private String disability;
    private String email;
    private String contactNumber;
    private String resAddress;
    private String postAddress;
    private String highestGradePassed;


    @OneToMany(
            cascade = CascadeType.ALL
    )
    private Collection<Qualification> qualifications;
    private String workExperience;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    private Collection<ProfesionalBody> profBodies;

}
