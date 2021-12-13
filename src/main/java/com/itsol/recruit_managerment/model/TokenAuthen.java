package com.itsol.recruit_managerment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "token")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenAuthen {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String token;

    @ManyToOne
    private User user;

    @Column(name="create_date")
    private Date createDate;
}