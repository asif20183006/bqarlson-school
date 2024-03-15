package com.bqarlson.school.services;

import com.bqarlson.school.beans.LoginBean;
import com.bqarlson.school.businesslogic.UserLoginBL;
import com.bqarlson.school.entities.UserDetails;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserLoginBL userLoginBL;
    public ResponseEntity<UserDetails> userLogin(HttpServletRequest request) {
        try {

            LoginBean loginBean = userLoginBL.clientValidation(request);
            UserDetails userDetails = userLoginBL.serverValidation(loginBean);

            return ResponseEntity.ok(userDetails);

        } catch (Exception e) {
            log.error("Error occurred while login user. Details - ", e);
            return ResponseEntity.badRequest().body(null);
        }

    }
}
