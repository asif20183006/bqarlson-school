package com.bqarlson.school.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MYSQLConfig {
    @Value("${spring.datasource.url:jdbc:mysql://localhost:3306/tests}")
    private String DATASOURCE_URL;

    @Value("${spring.datasource.username:Asif}")
    private String USER_NAME;

    @Value("${spring.datasource.password:Asif@123}")
    private String PASSWORD;

    @Value("${spring.datasource.driver-class-name:com.mysql.cj.jdbc.Driver}")
    private String DRIVER_CLASS_NAME;

    @Bean
    HikariDataSource getMYSQLConnection() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DATASOURCE_URL);
        config.setUsername(USER_NAME);
        config.setPassword(PASSWORD);
        config.setDriverClassName(DRIVER_CLASS_NAME);

        return new HikariDataSource(config);
    }
}
