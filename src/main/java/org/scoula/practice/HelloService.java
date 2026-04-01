package org.scoula.practice;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String hi(){
        return "Hello Spring Boot!";
    }
}
