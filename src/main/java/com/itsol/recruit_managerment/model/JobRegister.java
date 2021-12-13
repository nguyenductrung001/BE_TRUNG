package com.itsol.recruit_managerment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "job_register")
public class JobRegister implements  Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JOBREG_SEQ")
    @SequenceGenerator(name = "JOBREG_SEQ", sequenceName = "JOBREG_SEQ", allocationSize = 1, initialValue = 1)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date_register")
    private Date dateRegister;

    @ManyToOne
    @JoinColumn(name = "profile_status_id")
    private ProfileStatus profileStatus;

    @Column(name = "date_interview")
    private Date dateInterview;

    @Column(name = "method_interview")
    private String methodInterview;

    @Column(name = "cv_file")
    private String cv;

    @Column(name = "IS_DELETE")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isDelete;


}
