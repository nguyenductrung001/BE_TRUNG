package com.itsol.recruit_managerment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity(name ="Company")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Company_SEQ")
    @SequenceGenerator(name = "Company_SEQ", sequenceName = "Company_SEQ", allocationSize = 1, initialValue = 1)
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "email", nullable = false)
    String email;

    @Column(name = "hot_line", nullable = false)
    String hotLine;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_incoporation", nullable = false)
    Date dateIncoporation;

    @Column(name = "tax_code", nullable = false)
    String taxCode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tax_date", nullable = false)
    Date taxDate;

    @Column(name = "head_office", nullable = false)
    String headOffice;

    @Column(name = "number_staff ", nullable = false)
    String numberStaff ;

    @Column(name = " link_web ", nullable = false)
    String  linkWeb ;

    @Column(name = " description ", nullable = false)
    String  description ;

    @Column(name = " avatar ", nullable = false)
    String  avatar ;

    @Column(name = " backdrop_img ", nullable = false)
    String  backdropImg ;

    @Column(name = "is_delete ")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isDelete;


}
