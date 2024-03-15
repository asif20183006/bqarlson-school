package com.bqarlson.school.repostories;

import com.bqarlson.school.dao.UserDao;
import com.bqarlson.school.entities.UserDetails;
import com.bqarlson.school.exceptions.ServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRepo {
    @Autowired
    private UserDao userDao;

    public UserDetails getUserByUserName(String userName) throws ServerException {
        UserDetails userByUserName = userDao.getUserByUserName(userName);

        if (userByUserName == null) {
            throw new ServerException("Not a valid user");
        }

        return userByUserName;
    }
}
