package com.itsol.recruit_managerment.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity(name = "Method_work")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class MethodWork {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "METHOD_WORK_SEQ")
    @SequenceGenerator(name = "METHOD_WORK_SEQ", sequenceName = "METHOD_WORK_SEQ", allocationSize = 1, initialValue = 1)
    Long id;
    @Column(name = "method_name")
    String method_name;
    @Column(name="description")
    String description;
    @Column(name = "is_delete ")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isDelete ;
}
