package com.bqarlson.school.dao;

import com.bqarlson.school.entities.UserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Slf4j
public class UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserDetails getUserByUserName(String userName) {
        try {
            String query = "select full_name,user_name,user_details_id,role_id,credential,contact_number,email_id," +
                    "created_time,updated_time, last_login_time,status from user_details where user_name = :userName";

            Map<String, Object> param = new HashMap<>();
            param.put("userName", userName);

            return namedParameterJdbcTemplate.queryForObject(query, param, new BeanPropertyRowMapper<>(UserDetails.class));
        } catch (Exception e) {
            log.error("Error occurred while getting the userDetails by userName: [{}] from DB", userName, e);
            return null;
        }
    }
}
