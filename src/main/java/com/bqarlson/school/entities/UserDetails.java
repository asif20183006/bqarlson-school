package com.bqarlson.school.entities;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class UserDetails {
    private String fullName;
    private String userName;
    private String userDetailsId;
    private int roleId;
    private String credential;
    private String contactNumber;
    private String emailId;
    private String createdTime;
    private String updatedTime;
    private String lastLoginTime;
    private int status;
}
