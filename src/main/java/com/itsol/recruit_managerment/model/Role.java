package com.itsol.recruit_managerment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ROLE_ID")
    @SequenceGenerator(name = "GEN_ROLE_ID", sequenceName = "SEQ_ROLE", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "role_code", nullable = false)
    private String roleCode;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
