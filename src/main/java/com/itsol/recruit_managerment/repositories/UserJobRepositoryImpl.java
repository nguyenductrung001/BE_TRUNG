package com.itsol.recruit_managerment.repositories;

import com.itsol.recruit_managerment.model.Job;
import com.itsol.recruit_managerment.utils.SqlReader;
import com.itsol.recruit_managerment.vm.JobVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class UserJobRepositoryImpl extends BaseRepository implements UserJobRepository {

    @Override
    public List<Job> searchJobs(JobVM jobVM) {
        try {
            String query = SqlReader.getSqlQueryById(SqlReader.USER_HOME_MODULE, "list_job_home");
            Map<String, Object> parameters = new HashMap<>();
            if (!ObjectUtils.isEmpty(jobVM.getJob_name())) {
                query += "and name like :p_name";
                parameters.put("p_name", jobVM.getJob_name());
            }
            if (!ObjectUtils.isEmpty(jobVM.getQty_person())) {
                query += "and qty_person > :p_quantity_person";
                parameters.put("p_quantity_person", jobVM.getQty_person());
            }

            return getNamedParameterJdbcTemplate().query(query, parameters, new JobMapper());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return null;
    }


    class JobMapper implements RowMapper<Job> {
        public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
            Job dto = new Job();

            dto.setJobName(rs.getString("name"));
            dto.setCreateDate(rs.getDate("create_date"));
            // tương tự với các trường khác
            return dto;
        }
    }

}
