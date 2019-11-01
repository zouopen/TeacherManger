package com.evlisay.schoolservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SchoolservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolservicesApplication.class, args);
    }

}
