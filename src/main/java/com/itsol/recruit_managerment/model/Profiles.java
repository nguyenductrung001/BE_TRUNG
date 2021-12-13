package com.itsol.recruit_managerment.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Profiles implements Serializable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILES_SEQ")
    @SequenceGenerator(name = "PROFILES_SEQ", sequenceName = "PROFILES_SEQ", allocationSize = 1, initialValue = 1)
    Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    // truongbb - nullable là true thì không cần viết ra, đó là default ==> tự rà soát các class khác và bỏ đi
    @Column(name = "skill", nullable = false)
    private String Skill;

    @Column(name = "years_experience", nullable = false)
    private Integer yearsExperience;

    @OneToOne
    @JoinColumn(name ="academic_level_id")
    private AcademicLevel academicLevel;

    @Column(name = "desired_salary ", nullable = false)
    String desiredSalary;

    @Column(name = "desired_working_address  ", nullable = false)
    String desiredWorkingAddress;

    @Column(name = "is_delete ")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isDelete;

}
