package com.itsol.recruit_managerment.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyDTO {
    Long id;
    String name;
    String email;
    String hot_line;
    Date date_incoporation;
    String tax_code;
    Date tax_date;
    String head_office;
    String number_staff ;
    String  link_web ;
    String  description ;
    String  avatar ;
    String  backdrop_img ;
    Integer   is_delete ;
}
