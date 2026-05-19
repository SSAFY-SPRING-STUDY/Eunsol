package org.scoula.practice.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String hi(){
        return "Hello Spring Boot!";
    }
}
