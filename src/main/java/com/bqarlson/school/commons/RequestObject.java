package com.bqarlson.school.commons;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
@Slf4j
public class RequestObject {

    public String getRequestBody(HttpServletRequest request) throws Exception{

        try {
            InputStream inputStream = request.getInputStream();
            StringBuilder requestBody = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
            return requestBody.toString();
        } catch (Exception e) {
            log.error("Exception while parsing request body. Details:- ", e);
            throw new Exception(e);
        }
    }
}
