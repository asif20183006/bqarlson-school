package com.bqarlson.school.utilities;

import com.bqarlson.school.dao.UserDao;
import com.bqarlson.school.entities.UserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@Component
public class CacheWrapper {

    @Value("${mysql.cache.mode:0}")
    public int mysqlCacheMode;

    @Autowired
    UserDao userDao;

    @Cacheable(value = Constants.USER_CACHE, key = "'/userDetails' + #userName", condition = "#root.target.mysqlCacheMode == 0",
    unless = "#result == null")
    public UserDetails getUserByUserName(String userName) {
        log.info("userDetails are being fetched from database for the first time and will be stored in cache before it expires for username: [{}]", userName);
        return userDao.getUserByUserName(userName);

    }
}
