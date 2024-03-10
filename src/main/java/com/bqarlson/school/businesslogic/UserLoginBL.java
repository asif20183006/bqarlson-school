package com.bqarlson.school.businesslogic;

import com.bqarlson.school.beans.LoginBean;
import com.bqarlson.school.commons.RequestObject;
import com.bqarlson.school.entities.UserDetails;
import com.bqarlson.school.exceptions.ClientException;
import com.bqarlson.school.exceptions.ServerException;
import com.bqarlson.school.repostories.UserRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class UserLoginBL {
    @Autowired
    private RequestObject requestObject;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepo userRepo;
    public LoginBean clientValidation(HttpServletRequest request) throws ClientException {
        try {
            String body = requestObject.getRequestBody(request);
            LoginBean loginBean = objectMapper.readValue(body, new TypeReference<LoginBean>() {
            });

            String userName = loginBean.getUserName();
            String password = loginBean.getPassword();

            if (userName == null || userName.trim().isEmpty()) {
                log.error("Invalid user name : [{}]", userName);
                throw new ClientException("Invalid user name");
            }

            if (password == null || password.isEmpty()) {
                log.error("Invalid password for userName: [{}], password: [{}]", userName, password);
                throw new ClientException("Invalid password");
            }
            return loginBean;
        } catch (Exception e) {
            log.error("Error occurred while converting RequestBody to Bean. Details - ", e);
            throw new ClientException(e.getMessage());
        }
    }

    public UserDetails serverValidation(LoginBean loginBean) throws ServerException {
        try {

            UserDetails userDetails = userRepo.getUserByUserName(loginBean.getUserName());

            if (userDetails.getStatus() == 0) {
                log.error("User with user name: [{}], is inactive.", userDetails.getUserName());
                throw new ServerException("Inactive user contact to administrator");
            }

            String passWord = loginBean.getPassword();
            String credential = userDetails.getCredential();

            if (!Objects.equals(passWord, credential)) {
                log.error("Incorrect password for userName: [{}]", userDetails.getUserName());
                throw new ServerException("Incorrect password");
            }

            return userDetails;

        } catch (Exception e) {
            throw new ServerException("Exception occurred while validating the user");
        }
    }
}
